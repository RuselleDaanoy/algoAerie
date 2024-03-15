package com.example;

import java.util.*;

public class StringMatching {
    public static List<Integer> findOccurrences(String customerAddress, String search) {
        customerAddress = customerAddress.toLowerCase();
        search = search.toLowerCase();

        List<Integer> occurrences = new ArrayList<>();
        int index = customerAddress.indexOf(search);
        while(index != -1) {
            occurrences.add(index);
            index = customerAddress.indexOf(search, index + 1);
        }

        return occurrences;
    }
}
