package com.practice.collection;

import java.util.Arrays;
import java.util.Comparator;

public class _Array {

    public static void main(String[] args) {
        int[][] a = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        Arrays.sort(a, Comparator.comparing(b -> b[0]));
        System.out.println(Arrays.deepToString(a));
    }
}
