package com.practice.base.staticblock;

public class StaticClass {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public static class InnerClass {
        private int id;

        public InnerClass() {

        }

        public String showMessage() {
            return "StaticClass.InnerClass";
        }

    }

    @Override
    public int hashCode() {
        return String.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

class StaticClazz {
    public String showMessage() {
        return "StaticClazz";
    }
}
