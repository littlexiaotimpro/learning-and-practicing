package com.practice.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {

    private static void classProperties(){
        // 类型属性
        Class<Demo> demoClass = Demo.class;
        System.out.println(demoClass.getName());
        System.out.println(demoClass.getSimpleName());
        // 构造器
        Constructor<?>[] constructors = demoClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.getName());
        }
        // 公有属性（含继承）
        Field[] publicFields = demoClass.getFields();
        for (Field publicField : publicFields) {
            System.out.println(publicField.getName());
        }
        // 成员属性（不含继承）
        Field[] fields = demoClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        // 公有方法（含继承）
        Method[] publicMethods = demoClass.getMethods();
        for (Method publicMethod : publicMethods) {
            System.out.println(publicMethod.getName());
        }
        // 成员方法（不含继承）
        Method[] methods = demoClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        // 注解信息
        Annotation[] annotations = demoClass.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getName());
        }
    }

    private static void superClassProperties(){
        // 父类
        Class<? super Demo> superclass = Demo.class.getSuperclass();
        System.out.println(superclass.getName());
        System.out.println(superclass.getSimpleName());
        // 构造器
        Constructor<?>[] constructors = superclass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.getName());
        }
        // 公有属性（含继承）
        Field[] publicFields = superclass.getFields();
        for (Field publicField : publicFields) {
            System.out.println(publicField.getName());
        }
        // 成员属性（不含继承）
        Field[] fields = superclass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        // 公有方法（含继承）
        Method[] publicMethods = superclass.getMethods();
        for (Method publicMethod : publicMethods) {
            System.out.println(publicMethod.getName());
        }
        // 成员方法（不含继承）
        Method[] methods = superclass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        // 注解信息
        Annotation[] annotations = superclass.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getName());
        }

    }

    private static void methodA(){
        Class<Demo> demoClass = Demo.class;
        try {
            // 通过反射的方式调用无参构造器
            Demo demo = demoClass.newInstance();
            demo.setTarget(1);
            demo.setContent("A");
            System.out.println(demo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void methodB(){
        try {
            // 通过反射的方式调用无参构造器
            Class<?> demoClass = Class.forName("com.practice.reflection.Demo");
            Demo demo = (Demo) demoClass.newInstance();
            demo.setTarget(2);
            demo.setContent("B");
            System.out.println(demo);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void methodC(){
        try {
            // 调用带参构造器
            Class<?> demoClass = Class.forName("com.practice.reflection.Demo");
            Constructor<?>[] constructors = demoClass.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount()>0){
                    Object c = constructor.newInstance(3, "C");
                    System.out.println(c);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("--------------classProperties--------------");
        classProperties();
        System.out.println("-----------superClassProperties------------");
        superClassProperties();
        System.out.println("-----------------methodA---- --------------");
        methodA();
        System.out.println("-----------------methodB-------------------");
        methodB();
        System.out.println("-----------------methodC-------------------");
        methodC();
    }

}
