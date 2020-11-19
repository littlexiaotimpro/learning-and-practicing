package com.practice.mode.template;

import com.practice.mode.template.method.AbstractTemplateMethod;
import com.practice.mode.template.method.TemplateMethod;

/**
 * @author XiaoSi
 * @className Main
 * @description 模板方法主程序
 * @date 2020/11/19
 */
public class Main {
    public static void main(String[] args) {
        AbstractTemplateMethod templateMethod = new TemplateMethod();
        templateMethod.process();
    }
}
