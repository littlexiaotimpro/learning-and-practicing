package com.practice.base.datatype;

@SuppressWarnings({"UnnecessaryBoxing",
        "ConstantConditions",
        "NewObjectEquality",
        "NumberEquality",
        "WrapperTypeMayBePrimitive"})
public class BaseInteger {

    private static void integer() {
        Integer a = 1;
        Integer b = 1;
        int c = 1;
        int c1 = 2;
        Integer d = new Integer(1);
        Integer e = 200;
        Integer f = 200;
        System.out.println("a == b: " + (a == b));
        System.out.println("a == c: " + (a == c));
        System.out.println("a == d: " + (a == d));
        System.out.println("c1 == a + b: " + (c1 == a + b));
        System.out.println("c1 == a + c: " + (c1 == a + c));
        System.out.println("e == f: " + (e == f));


        Byte b1 = 1;
        System.out.println(1 == b1);
    }

    private static void _float_double() {
        Double a = Double.valueOf(1);
        Double b = Double.valueOf(1);
        System.out.println("a == b: " + (a == b));
        System.out.println("1 == 1.0: " + (1 == 1.0));

        Float c = Float.valueOf(1);
        Float d = Float.valueOf(1);
        System.out.println("c == d: " + (c == d));
    }

    public static void main(String[] args) {
        // 1.整型
        System.out.println("----------------------------------");
        System.out.println("-------1.整型");
        integer();

        // 2.浮点型
        System.out.println("----------------------------------");
        System.out.println("-------1.浮点型");
        _float_double();
    }

}
