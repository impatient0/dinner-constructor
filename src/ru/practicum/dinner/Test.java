package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();
        al.add("hello");
        al.add("beautiful");
        al.add("uwu");
        String[] a = al.toArray(new String[0]);
        System.out.println(Arrays.toString(a));
    }
}
