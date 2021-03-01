package com.practice.reflection;

import java.text.MessageFormat;

public class SuperDemo {
    public String superVariable1;
    public String superVariable2;
    protected String superVariable3;
    protected String superVariable4;
    private String superVariable5;
    private String superVariable6;

    public String getSuperVariable1() {
        return superVariable1;
    }

    public void setSuperVariable1(String superVariable1) {
        this.superVariable1 = superVariable1;
    }

    public String getSuperVariable2() {
        return superVariable2;
    }

    public void setSuperVariable2(String superVariable2) {
        this.superVariable2 = superVariable2;
    }

    public String getSuperVariable3() {
        return superVariable3;
    }

    public void setSuperVariable3(String superVariable3) {
        this.superVariable3 = superVariable3;
    }

    public String getSuperVariable4() {
        return superVariable4;
    }

    public void setSuperVariable4(String superVariable4) {
        this.superVariable4 = superVariable4;
    }

    public String getSuperVariable5() {
        return superVariable5;
    }

    public void setSuperVariable5(String superVariable5) {
        this.superVariable5 = superVariable5;
    }

    public String getSuperVariable6() {
        return superVariable6;
    }

    public void setSuperVariable6(String superVariable6) {
        this.superVariable6 = superVariable6;
    }

    public void displayPublic() {
        String format = MessageFormat.format("var1={0}, var2={1}", superVariable1, superVariable2);
        System.out.println(format);
    }

    public void displayProtected() {
        String format = MessageFormat.format("var3={0}, var4={1}", superVariable3, superVariable4);
        System.out.println(format);
    }

    public void displayPrivate() {
        String format = MessageFormat.format("var5={0}, var6={1}", superVariable5, superVariable6);
        System.out.println(format);
    }
}
