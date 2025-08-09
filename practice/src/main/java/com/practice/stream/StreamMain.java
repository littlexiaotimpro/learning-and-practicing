package com.practice.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamMain {

    public static void main(String[] args) {
        Integer integer = Stream.iterate(1, x -> x + 1).limit(50).peek(x -> {
                    System.out.println(Thread.currentThread().getName());
                }
        ).max(Integer::compareTo).orElse(null);
        System.out.println("默认顺序流：" + integer);

        integer = Stream.iterate(1, x -> x + 1).limit(50).peek(x -> {
                    System.out.println(Thread.currentThread().getName());
                }
        ).parallel().max(Integer::compareTo).orElse(null);
        System.out.println("并行流：" + integer);

        integer = Stream.iterate(1, x -> x + 1).limit(50).peek(x -> {
                    System.out.println(Thread.currentThread().getName());
                }
        ).sequential().max(Integer::compareTo).orElse(null);
        System.out.println("顺序流：" + integer);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        list.parallelStream().forEach(i->{
            System.out.printf("%d, ", i);
        });
    }

}
