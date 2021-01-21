package com.practice.enums.constant;

import com.practice.enums.bean.Animal;

public enum AnimalEnum implements AnimalInterface {

    Dog {
        @Override
        public String showDescription(Animal animal) {
            System.out.println(animal.getName());
            return animal.getClass().getName();
        }
    },

    Cat {
        @Override
        public String showDescription(Animal animal) {
            System.out.println(animal.getName());
            return animal.getClass().getName();
        }
    },

    Pig {
        @Override
        public String showDescription(Animal animal) {
            System.out.println(animal.getName());
            return animal.getClass().getName();
        }
    },

    Panda {
        @Override
        public String showDescription(Animal animal) {
            if (animal == null) {
                return "no panda";
            }
            return null;
        }
    };
}
