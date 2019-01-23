package ru.dsoccer1980.lottery_adviser.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

import ru.dsoccer1980.lottery_adviser.model.InitialData;
import ru.dsoccer1980.lottery_adviser.model.Numbers;


@Repository
@Transactional(readOnly = true)
public class ORMService {

    @PersistenceContext
    private EntityManager entityManager;

    public Map<Integer, List<Numbers>> queryFindAllNumbers() {
        System.out.println("ORMService queryFindAllNumbers is called");
        String query = "from Numbers order by drawnumber,index";
        TypedQuery<Numbers> typedQuery = entityManager.createQuery(query, Numbers.class);

        List<Numbers> resultList = typedQuery.getResultList();
        Map<Integer, List<Numbers>> collect = resultList.stream().collect(Collectors.groupingBy(Numbers::getDrawNumber));
        return collect;
    }

    @Transactional
    public void addNumbers(List<Integer> list) {
        System.out.println("ORMService addNumbers is called");
        for (int index = 1; index < list.size(); index++) {
            Numbers numbers = new Numbers(list.get(0), index, list.get(index));
            entityManager.persist(numbers);
        }
    }

    @Transactional
    public void drawDelete(int drawNumber) {
        System.out.println("ORMService drawDelete is called");
        String query = "DELETE FROM Numbers n WHERE n.drawNumber=:drawNumber";
        entityManager.createQuery(query)
                .setParameter("drawNumber", drawNumber)
                .executeUpdate();
    }

    @Transactional
    public void populateDb() {
        System.out.println("ORMService populateDb is called");
        for (List<Integer> list : InitialData.data) {
            addNumbers(list);
        }
    }

    public Map<Integer, Integer> numbersNextAppearance() {
        System.out.println("ORMService numbersNextAppearance is called");
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 1; i <= 48; i++) {  //TODO
            result.put(i, numberAverageAppearance(i) + numberLastAppearance(i));
        }
        return result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Integer numberAverageAppearance(Integer number) {
        System.out.println("ORMService numberAverageAppearance is called");
        String query = "Select n from Numbers n WHERE n.number=:number order by drawnumber";
        List<Numbers> list = entityManager.createQuery(query).setParameter("number", number).getResultList();
        List<Integer> drawNumbers = list.stream().map(Numbers::getDrawNumber).collect(Collectors.toList());
        List<Integer> frequency = new ArrayList<>();
        int lastDrawNumber = 0;
        for (int drawnumber : drawNumbers) {
            if (lastDrawNumber != 0) {
                frequency.add(drawnumber - lastDrawNumber);
            }
            lastDrawNumber = drawnumber;
        }
        return (int) (frequency.stream().mapToInt(n -> n).average().orElse(0) + 0.5);
    }

    private int numberLastAppearance(Integer number) {
        String query = "Select n from Numbers n WHERE n.number=:number order by drawnumber DESC";
        int lastDrawNumber = ((Numbers) entityManager.createQuery(query)
                .setParameter("number", number)
                .setMaxResults(1)
                .getSingleResult()).getDrawNumber();
        return lastDrawNumber;
    }
}


