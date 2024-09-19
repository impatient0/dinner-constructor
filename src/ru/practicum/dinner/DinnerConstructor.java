package ru.practicum.dinner;

import java.util.*;
import java.util.stream.Collectors;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> dishList = new HashMap<>();
    private final Random random = new Random();

    public void addDish(String dishType, String dishName) {
        if (dishList.containsKey(dishType)) {
            if (!dishList.get(dishType).contains(dishName)) {
                dishList.get(dishType).add(dishName);
            }
        } else {
            dishList.put(dishType, new ArrayList<>(Collections.singleton(dishName)));
        }
    }

    public boolean hasDishType(String dishType) {
        return dishList.containsKey(dishType);
    }

    private ArrayList<String> createRandomDinner(ArrayList<String> dishTypes) {
        return dishTypes.stream().map(dt -> dishList.get(dt).get(random.nextInt(dishList.get(dt).size())))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<ArrayList<String>> createDinners(int numOfDinners, ArrayList<String> dishTypes) {
        ArrayList<ArrayList<String>> dinners = new ArrayList<>();
        while (dinners.size() < numOfDinners) {
            dinners.add(createRandomDinner(dishTypes));
        }
        return dinners;
    }
}
