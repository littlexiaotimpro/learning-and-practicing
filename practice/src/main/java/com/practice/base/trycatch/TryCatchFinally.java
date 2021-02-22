package com.practice.base.trycatch;

public class TryCatchFinally {

    private static int onReturn() {
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

    private static void noReturn() {
        try {
            System.out.println("try");
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {
        // 1.验证try-catch等的执行顺序
        int i = onReturn();
        System.out.println(i);

        // 2.验证try-catch等的执行顺序
        noReturn();
    }

}
