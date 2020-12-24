package com.practice.test.spel;

import com.practice.spel.SimpleSpELParser;
import com.practice.spel.Variable;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaoSi
 * @className SpELParserTest
 * @description 表达式解析测试
 * @date 2020/11/26
 */
public class SpELParserTest {

    @Test
    public void test01() {
        /*
         * 若传递字符串作为参数，则非赋值部分必须使用单引号引入
         * 默认的字符串表示的是赋值参数
         */
        String parser = SimpleSpELParser.parser("'hello world'.concat('!')");
        System.out.println(parser);

        parser = SimpleSpELParser.parser("new String('hello world').concat('!').toUpperCase()");
        System.out.println(parser);

        // 方法调用：concat(..) & getBytes()
        byte[] bytes = SimpleSpELParser.parserBytes("'hello world'.concat('!').bytes");
        System.out.println(Arrays.toString(bytes));
    }

    @Test
    public void test02() {
        /*
         * 调用的方法必须和类型匹配，如：String.length()，int[].length
         * concat() & length()
         */
        Integer length = SimpleSpELParser.parserLength("'hello world'.concat('!').length()");
        System.out.println(length);
    }

    @Test
    public void test03() {
        /*
         * 对象公有属性，可以直接进行参数赋值并判断
         * 若是私有对象属性，必须提供Getter方法
         */
        Variable var = new Variable(true);
        boolean b = SimpleSpELParser.ParserBoolean("flag == true", var);
        System.out.println(b);

        var = new Variable("target");
        b = SimpleSpELParser.ParserBoolean("prop == 'target'", var);
        System.out.println(b);

        var = new Variable();
        var.booleanList.add(true);
        b = SimpleSpELParser.ParserBoolean("booleanList[0]", var, "false");
        System.out.println(b);
    }

    @Test
    public void test04() {
        /*
         * var中的list属性初始化，并且集合长度增长为4
         * 每一个元素都是空字符串
         */
        Variable var = new Variable();
        Object o = SimpleSpELParser.parserList("list[3]", var);
        System.out.println(var.list);
        System.out.println(o);
    }

    @Test
    public void test05() {
        Variable var = new Variable("hello");
        Object o = SimpleSpELParser.parserConfig("prop", var);
        System.out.println(o.toString());
    }

    @Test
    public void test06() {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        boolean b = SimpleSpELParser.templateParser("${flag == false}", map, Boolean.class);
        System.out.println(b);

        b = SimpleSpELParser.templateParser("${flag == true}", map, Boolean.class);
        System.out.println(b);

        map.put("flag", 100);
        b = SimpleSpELParser.templateParser("#{flag+100 == 200}", map, Boolean.class);
        System.out.println(b);

        map.put("flag", "target");
        b = SimpleSpELParser.templateParser("flag == 'target'", map, Boolean.class);
        System.out.println(b);

        map.put("flag", 15);
        Integer i = SimpleSpELParser.templateParser("flag + 100", map, Integer.class);
        System.out.println(i);

        map.put("flag", 14);
        i = SimpleSpELParser.templateParser("${flag - 10}", map, Integer.class);
        System.out.println(i);

        map.put("flag", 5);
        i = SimpleSpELParser.templateParser("${flag * 10}", map, Integer.class);
        System.out.println(i);

        map.put("flag", 14);
        i = SimpleSpELParser.templateParser("${flag / 2}", map, Integer.class);
        System.out.println(i);
    }

    @Test
    public void test07(){
        Map<String, Object> map = new HashMap<>();
        map.put("flag", 14);
        String o = SimpleSpELParser.templateParser("${flag / 2}+${flag + 'abc'}", map, String.class);
        System.out.println(o);
    }

}
