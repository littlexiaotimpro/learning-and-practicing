package com.practice.base.trycatch;

public class TryCatchFinally01 {

    /**
     * 返回结果并不会被修改
     * 原因看字节码文件 ClassCode.txt
     *
     * @return int
     */
    private static int withReturn() {
        int num = 0;
        try {
            return num;
        } finally {
            num++;
        }
    }

    public static void main(String[] args) {
        System.out.println(withReturn());
    }

}
