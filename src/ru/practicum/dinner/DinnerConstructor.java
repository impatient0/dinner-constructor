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

    private String[] decodeDinner(int combo, String[] dishTypes) {
        int[] choices = ComboGenerator.readCombo(combo,
                Arrays.stream(dishTypes).map(d -> dishList.get(d).size()).mapToInt(i -> i).toArray());
        String[] dinner = new String[dishTypes.length];
        for (int i = 0; i < dishTypes.length; i++) {
            dinner[i] = dishList.get(dishTypes[i]).get(choices[i]);
        }
        return dinner;
    }

    public ArrayList<String[]> createDinners(int numOfDinners, String[] dishTypes) throws BadDishTypeException,
            TooManyCombosException {
        String badDishType = Arrays.stream(dishTypes).filter(type -> !dishList.containsKey(type)).findAny()
                .orElse(null);
        if (badDishType != null) {
            throw new BadDishTypeException("Типа блюд «" + badDishType + "» пока нет в меню!");
        }
        int maxDinners = Arrays.stream(dishTypes).map(dishType -> dishList.get(dishType).size())
                .reduce(1, (a, b) -> a * b);
        if (maxDinners < numOfDinners) {
            throw new TooManyCombosException("Можно создать не более " + maxDinners + " уникальных наборов!");
        }
        ArrayList<Integer> combos = new ArrayList<>(numOfDinners);
        while (combos.size() < numOfDinners) {
            int r = random.nextInt(maxDinners);
            if (!combos.contains(r)) {
                combos.add(r);
            }
        }
        return combos.stream().map(combo -> decodeDinner(combo, dishTypes))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
