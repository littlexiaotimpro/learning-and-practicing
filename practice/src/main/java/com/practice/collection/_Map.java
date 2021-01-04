package com.practice.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _Map {

    public static void entrySetTest() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("a", null);
        map.put("b", Arrays.asList("a", "b", "c"));
        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
            List<String> value = stringListEntry.getValue();
            if (value.get(0) == null) {
                System.out.println("Has null");
            }
        }
    }

    public static void main(String[] args) {
        entrySetTest();
    }
}
