package com.practice.base;

public class MainClass {

    private static int exception() {
        try {
            System.out.println("try");
            int i = 1 / 0;
            return 1;
        } catch (Exception e) {
            System.out.println("catch");
            return 2;
        } finally {
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {
        // 1.验证try-catch等的执行顺序
        int i = exception();
        System.out.println(i);
    }

}
