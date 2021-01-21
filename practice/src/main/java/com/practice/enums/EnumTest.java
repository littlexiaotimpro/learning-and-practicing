package com.practice.enums;

import com.practice.enums.bean.Animal;
import com.practice.enums.bean.Cat;
import com.practice.enums.bean.Dog;
import com.practice.enums.bean.Pig;
import com.practice.enums.constant.AnimalEnum;

/**
 * 使用枚举方式优化代码
 */
public class EnumTest {

    public static void main(String[] args) {
        Animal animal = new Cat();
        AnimalEnum animalEnum = AnimalEnum.valueOf(animal.getClass().getSimpleName());
        System.out.println(animalEnum.showDescription(animal));

        animal = new Dog();
        animalEnum = AnimalEnum.valueOf(animal.getClass().getSimpleName());
        System.out.println(animalEnum.showDescription(animal));

        animal = new Pig();
        animalEnum = AnimalEnum.valueOf(animal.getClass().getSimpleName());
        System.out.println(animalEnum.showDescription(animal));


        System.out.println(AnimalEnum.valueOf("Panda").showDescription(null));
    }
}
