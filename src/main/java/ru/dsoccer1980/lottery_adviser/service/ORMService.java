package ru.dsoccer1980.lottery_adviser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import ru.dsoccer1980.lottery_adviser.model.InitialData;
import ru.dsoccer1980.lottery_adviser.model.Numbers;
import ru.dsoccer1980.lottery_adviser.repository.NumbersRepository;


@Service
@Transactional(readOnly = true)
public class ORMService {

    private final NumbersRepository numbersRepository;

    @Autowired
    public ORMService(NumbersRepository numbersRepository) {
        this.numbersRepository = numbersRepository;
    }

    public Map<Integer, List<Numbers>> queryFindAllNumbers() {
        System.out.println("ORMService queryFindAllNumbers is called");
        List<Numbers> resultList = numbersRepository.findAll();
        Map<Integer, List<Numbers>> collect = resultList.stream().collect(Collectors.groupingBy(Numbers::getDrawNumber));
        return collect;
    }

    @Transactional
    public void addNumbers(List<Integer> list) {
        System.out.println("ORMService addNumbers is called");
        for (int index = 1; index < list.size(); index++) {
            Numbers numbers = new Numbers(list.get(0), index, list.get(index));
            numbersRepository.save(numbers);
        }
    }

    @Transactional
    public void drawDelete(int drawNumber) {
        System.out.println("ORMService drawDelete is called");
        numbersRepository.delete(drawNumber);
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
        List<Numbers> list = numbersRepository.findByNumberOrderByDrawNumber(number);
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
        return numbersRepository.findTopByNumberOrderByDrawNumberDesc(number).getDrawNumber();
    }
}


