package com.practice.base.pkg;

/**
 * 获取包路径
 *
 * @date 2022/2/22
 */
public class PackageDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String name = Class.forName(PDemo.class.getName()).getPackage().getName();
        System.out.println(name);

        String name1 = PDemo.class.getPackage().getName();
        System.out.println(name1);
    }
}
