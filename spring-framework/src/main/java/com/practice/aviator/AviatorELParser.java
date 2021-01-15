package com.practice.aviator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * Aviator 表达式解析引擎
 */
public final class AviatorELParser {

    public static void main(String[] args) {
        Map<String, Object> env = new HashMap<>();
        env.put("a", 10);
        env.put("b", 3);
        Object execute = AviatorEvaluator.execute("a+b", env);
        System.out.println(execute.toString());
    }

}
