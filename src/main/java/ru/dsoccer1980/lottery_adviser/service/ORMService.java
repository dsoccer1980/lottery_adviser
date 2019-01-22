package ru.dsoccer1980.lottery_adviser.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.dsoccer1980.lottery_adviser.model.Numbers;


@Repository
@Transactional(readOnly = true)
public class ORMService {

    @PersistenceContext
    private EntityManager entityManager;

    public Map<Integer, List<Numbers>> queryFindAllNumbers() {
        System.out.println("ORMService queryFindAllNumbers is called");
        String query = "from Numbers order by drawnumber";
        TypedQuery<Numbers> typedQuery = entityManager.createQuery(query, Numbers.class);

        List<Numbers> resultList = typedQuery.getResultList();
        Map<Integer, List<Numbers>> collect = resultList.stream().collect(Collectors.groupingBy(Numbers::getDrawNumber));
        return collect;
    }

    @Transactional
    public void addNumbers(List<Integer> list) {
        System.out.println("ORMService addNumbers is called");
        Numbers numbers = new Numbers(list.get(0), list.get(1), list.get(2));
        entityManager.persist(numbers);
    }

    @Transactional
    public void drawDelete(int id) {
        System.out.println("ORMService drawDelete is called");
        String query = "DELETE FROM Numbers n WHERE n.id=:id";
        entityManager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }
}


