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

    public void findByNumberBetween() {
        int value1 = 43;
        int value2 = 48;
        List<Numbers> byNumberBetween = numbersRepository.findByNumberBetween(value1, value2);
        byNumberBetween.stream().filter(num -> num.getNumber()>=value1 && num.getNumber()<=value2).forEach(num->System.out.println(num.getDrawNumber() + " " + num.getNumber()));
    }

    public Map<Integer, List<Numbers>> queryFindExactNumbers() {
       // List<Integer> integers = Arrays.asList(1,7,13,19,25,31,37,43);
       // List<Integer> integers = Arrays.asList(2,8,14,20,26,32,38,44);
       // List<Integer> integers = Arrays.asList(3,9,15,21,27,33,39,45);
       // List<Integer> integers = Arrays.asList(4,10,16,22,28,34,40,46);
       // List<Integer> integers = Arrays.asList(5,11,17,23,29,35,41,47);
        List<Integer> integers = Arrays.asList(6,12,18,24,30,36,42,48);
        List<Numbers> resultList = numbersRepository.findAll().stream().filter(num->integers.contains(num.getNumber())).collect(Collectors.toList());
        Map<Integer, List<Numbers>> collect = resultList.stream().sorted(Comparator.comparing(Numbers::getDrawNumber)).collect(Collectors.groupingBy(Numbers::getDrawNumber));
        return collect;
    }


    private int numberLastAppearance(Integer number) {
        return numbersRepository.findTopByNumberOrderByDrawNumberDesc(number).getDrawNumber();
    }
}


