package com.practice.test;

import com.practice.entity.BeanDemo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试Spring的基于XML配置的Bean创建与属性注入
 */
public class XMLBeanTest {

    @Test
    public void testBean() {
        // 验证简单的对象创建
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id
        BeanDemo bean = context.getBean("demo", BeanDemo.class);
        System.out.println(bean); // class com.practice.entity.BeanDemo {demoCode=null, demoString=null}
        // 2.1 直接通过类型获取对象实例，也是可以获取到对象
        BeanDemo beanUnId = context.getBean(BeanDemo.class);
        System.out.println(beanUnId);// class com.practice.entity.BeanDemo {demoCode=null, demoString=null}
    }

    @Test
    public void testSetter() {
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id
        BeanDemo bean = context.getBean("demo", BeanDemo.class);
        // 3.验证属性是否注入成功？
        System.out.println(bean);
        /*
         3.1.对于定义了属性的类型，如果不提供setter方法，能否注入属性？
         在配置文件中配置了不提供setter方法的属性，提示如下
         BeanCreationException: Bean property 'demoCode' is not writable or has an invalid setter method.
         对象创建异常：没有具体属性提供setter方法，或setter方法设置有问题，导致无法识别

          3.2.通过 setter 方法实现对象创建及属性注入
          class com.practice.entity.BeanDemo {demoCode=0001, demoString=demoProperty}
          成功创建实例，并且实例中的属性确实附上了值

          需要注意，如果要通过setter方式注入属性，且配置文件配置了该属性，那么必须提供setter，没配置的属性为null或为默认值
          class com.practice.entity.BeanDemo {demoCode=0001, demoString=demoProperty, demoSize=null, demoBool=false}
         */
    }

    @Test
    public void testConstructor() {
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id
        BeanDemo bean = context.getBean("demo", BeanDemo.class);
        // class com.practice.entity.BeanDemo {demoCode=null, demoString=null, demoSize=100, demoBool=false}
        System.out.println(bean);

//        <!-- 定义了带参构造器之后，若按基础的对象创建方式创建对象会报错，提示没有默认的构造器 -->
//        <!-- <bean id="demoError" class="com.practice.entity.BeanDemo"/>-->
//        //  BeanCreationException：No default constructor found;
//        BeanDemo demoError = context.getBean("demoError", BeanDemo.class);
//        System.out.println(demoError);
    }

    @Test
    public void testPNamespace() {
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id
        BeanDemo bean = context.getBean("demo", BeanDemo.class);
        // class com.practice.entity.BeanDemo {demoCode=0001, demoString=code and string, demoSize=null, demoBool=false}
        System.out.println(bean);

        /*
         在配置文件中配置了不提供setter方法的属性，提示如下
         BeanCreationException: Bean property 'demoCode' is not writable or has an invalid setter method.
         对象创建异常：没有具体属性提供setter方法，或setter方法设置有问题，导致无法识别
        */
    }

}