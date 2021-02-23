package com.practice.base.trycatch;

public class TryCatchFinally {

    /**
     * 带返回值
     *
     * <p> 若 try 块中存在 return，无异常，
     * 执行顺序：try.normal -> finally.normal -> try.return
     *
     * <p> 若 try-finally 块存在 return，无异常
     * 执行顺序：try.normal -> finally.normal -> finally.return
     *
     * <p> 若 try-catch 块存在 return，存在异常
     * 执行顺序：try.normal -> catch.normal -> finally.normal -> catch.return
     *
     * <p> 若 catch-finally 块存在 return，存在异常
     * 执行顺序：try.normal -> catch.normal -> finally.normal -> finally.return
     *
     * @return int
     */
    private static int onReturn() {
        try {
            System.out.println("try.normal");
            int i = 1 / 0;
            return 1;
        } catch (Exception e) {
            System.out.println("catch.normal");
            return 2;
        } finally {
            System.out.println("finally.normal");
            return 3;
        }
    }

    /**
     * 无返回值
     *
     * <p> 执行顺序: try -> catch -> finally
     */
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
        //noReturn();
    }

}
