package com.practice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author xiaosi
 * @date 2024/8/14
 * @since 1.0
 */
public class Java8StreamDemo {

    private static final List<Integer> LIST = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

    public static void main(String[] args) {
        LIST.stream()
                .map(i -> {
                    System.out.println("map: " + i);
                    return i + 1;
                }).filter(i -> {
                    System.out.println("filter: " + i);
                    return i.compareTo(0) > 0;
                }).map(i -> {
                    System.out.println("map2: " + i);
                    return i + 2;
                }).forEach(i -> {
                    System.out.println("foreach: " + i);
                });
    }

}
