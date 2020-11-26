package com.practice.spel;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Collections;
import java.util.Map;

/**
 * @author XiaoSi
 * @className SimpleSpELParser
 * @description spring解析EL表达式
 * @date 2020/11/26
 */
public final class SimpleSpELParser {
    private SimpleSpELParser() {
    }

    public static String parser(String expression) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        return (String) exp.getValue();
    }

    public static byte[] parserBytes(String expression) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        return (byte[]) exp.getValue();
    }

    public static Integer parserLength(String expression) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        return (Integer) exp.getValue();
    }

    public static boolean ParserBoolean(String expression, Object vars) {
        if (expression == null || expression.isEmpty()) {
            return false;
        }
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        Boolean res = exp.getValue(vars, Boolean.class);
        return res == null ? false : res;
    }

    public static boolean ParserBoolean(String expression, Variable vars, Object value) {
        if (expression == null || expression.isEmpty()) {
            return false;
        }
        ExpressionParser parser = new SpelExpressionParser();
        // 创建对公有属性只读访问权限的上下文配置
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        /*
         * "false" is passed in here as a String. SpEL and the conversion service
         * will recognize that it needs to be a Boolean and convert it accordingly.
         *
         * SpEL的自动类型转换及赋值
         */
        parser.parseExpression(expression).setValue(context, vars, value);
        return vars.booleanList.get(0);
    }

    public static Object parserList(String expression, Variable vars) {
        /*
         * Turn on:
         * - auto null reference initialization
         * - 自动对空引用初始化
         * - auto collection growing
         * - 集合自动增加
         * 配置对象控制某些表达式组件的行为。
         * 例如，如果您索引到数组或集合中并且指定索引处的元素为null，
         * 则SpEL可以自动创建该元素
         */
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);
        ExpressionParser parser = new SpelExpressionParser(config);
        Expression exp = parser.parseExpression(expression);
        // PS：操作什么就返回什么，如：操作list[3]，就返回list[3]的值
        Object value = exp.getValue(vars);

        // 创建对公有属性具有读写访问权限的上下文配置，对于未初始化属性，能够正常写入属性值
        // 对于只读权限，未初始化的属性无法访问
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        exp.setValue(context, vars, "1");
        return value;
    }

    public static Object parserConfig(String expression, Variable vars) {
        SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
                SimpleSpELParser.class.getClassLoader());
        SpelExpressionParser parser = new SpelExpressionParser(config);
        Expression expr = parser.parseExpression(expression);
        return expr.getValue(vars);
    }


    public static <T> T templateParser(String expression, Map<String, Object> map, Class<T> clazz) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("The expression is empty and cannot be parsed");
        }
        // 上下文及参数定义
        StandardEvaluationContext context = new StandardEvaluationContext();
        MapAccessor mapAccessor = new MapAccessor();
        context.setVariables(map);
        context.setPropertyAccessors(Collections.singletonList(mapAccessor));

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp;
        if (expression.startsWith(ParserContext.TEMPLATE_EXPRESSION.getExpressionPrefix())) {
            // 默认的表达式：#{}
            exp = parser.parseExpression(expression, ParserContext.TEMPLATE_EXPRESSION);
        } else if (expression.startsWith(TEMPLATE_EXPRESSION.getExpressionPrefix())) {
            // 自定义的表达式：${}
            exp = parser.parseExpression(expression, TEMPLATE_EXPRESSION);
        } else {
            exp = parser.parseExpression(expression);
        }
        return exp.getValue(context, map, clazz);
    }

    /**
     * 自定义表达式模板
     */
    private static final ParserContext TEMPLATE_EXPRESSION = new ParserContext() {
        @Override
        public boolean isTemplate() {
            return true;
        }

        @Override
        public String getExpressionPrefix() {
            return "${";
        }

        @Override
        public String getExpressionSuffix() {
            return "}";
        }
    };

}
