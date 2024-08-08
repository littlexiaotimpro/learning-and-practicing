package com.practice.express;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * TODO
 *
 * @author xiaosi
 * @date 2024/8/8
 * @since 1.0
 */
public class AviatorDemo {

    public static void main(String[] args) {
        AviatorEvaluator.addFunction(new AbstractFunction() {
            @Override
            public String getName() {
                return "add";
            }

            @Override
            public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
                Number left = FunctionUtils.getNumberValue(arg1, env);
                Number right = FunctionUtils.getNumberValue(arg2, env);
                return new AviatorDouble(left.intValue() + right.intValue());
            }
        });

        Object execute = AviatorEvaluator.execute("add(1,2)");
        System.out.println(execute);
    }
}
