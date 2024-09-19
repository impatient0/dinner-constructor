package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Вы ввели что-то не то...");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();
        dc.addDish(dishType, dishName);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();

        ArrayList<String> dishTypes = new ArrayList<>();
        while (!nextItem.isEmpty()) {
            if (!dc.hasDishType(nextItem)) {
                System.out.println("Категории «" + nextItem + "» пока нет в меню!\n");
                return;
            }
            dishTypes.add(nextItem);
            nextItem = scanner.nextLine();
        }
        ArrayList<ArrayList<String>> dinners = dc.createDinners(numberOfCombos, dishTypes);
        for (int i = 0; i < dinners.size(); i++) {
            System.out.println("Комбо " + (i + 1));
            System.out.println(dinners.get(i));
            }
        System.out.println();
    }
}
