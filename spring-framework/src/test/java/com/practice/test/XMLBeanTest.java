package com.practice.test;

import com.practice.entity.BeanDemo;
import com.practice.entity.BeanDemoScope;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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

    @Test
    public void testSpecialValue() {
        /*
          若配置文件内容为：<property name="demoCode" value="<0001>"></property>
          加载配置文件是会提示异常，提示具体位置的配置存在特殊字符
          XmlBeanDefinitionStoreException：lineNumber: 34; columnNumber: 42; 与元素类型 "property" 相关联的 "value" 属性值不能包含 '<' 字符。
          如果需要注入带特殊字符的值，则需要对字符进行转义，常用的xml转义字符 &lt;&gt;等，表示<>，分号结束表示一个字符实体
         */
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id
        BeanDemo bean = context.getBean("demo", BeanDemo.class);
        // class com.practice.entity.BeanDemo {demoCode=<0001>, demoString="<带特殊字符的串>", demoSize=null, demoBool=false}
        System.out.println(bean);
    }

    @Test
    public void testCollections() {
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id
        BeanDemo bean = context.getBean("demo", BeanDemo.class);

        // 注入数组/集合属性，同基本属性注入，需要实体提供对应属性的setter方法
        // 正确注入的展示结果
        // class com.practice.entity.BeanDemo
        // {demoCode=null, demoString=null, demoSize=null, demoBool=false,
        // demoList=[list_one, list_two],
        // demoMap={key_one=map_one, key_two=map_two},
        // demoArray=[one, two],
        // connect=null, connects=null}
        System.out.println(bean);
    }

    @Test
    public void testBeanWired() {
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id
        BeanDemo bean = context.getBean("demo", BeanDemo.class);

        // 内置 bean 注入
        // class com.practice.entity.BeanDemoConnect{connectCode=00000, connectString=11111}
        System.out.println(bean.getConnect());

        // 需要注意，外部bean注入及级联赋值的方式无法引用内置bean方式创建的关联对象
        // 外部 bean 注入
        // class com.practice.entity.BeanDemoConnect{connectCode=00001, connectString=22222}
        BeanDemo outDemo = context.getBean("outDemo", BeanDemo.class);
        System.out.println(outDemo.getConnect());

        // 级联赋值
        // class com.practice.entity.BeanDemoConnect{connectCode=00002, connectString=33333}
        BeanDemo linkDemo = context.getBean("linkDemo", BeanDemo.class);
        System.out.println(linkDemo.getConnect());

        // 内置对象集合注入
        //[class com.practice.entity.BeanDemoConnect{connectCode=00001, connectString=22222},
        // class com.practice.entity.BeanDemoConnect{connectCode=00002, connectString=33333}]
        BeanDemo listDemo = context.getBean("listDemo", BeanDemo.class);
        System.out.println(listDemo.getConnects());

        // 抽取对象集合，外部注入(倒序输出)
        // [class com.practice.entity.BeanDemoConnect{connectCode=00002, connectString=33333},
        // class com.practice.entity.BeanDemoConnect{connectCode=00001, connectString=22222}]
        List connectList = context.getBean("connectList", List.class);
        System.out.println(connectList);
        BeanDemo listDemoUtil = context.getBean("listDemoUtil", BeanDemo.class);
        System.out.println(listDemoUtil.getConnects());

    }

    @Test
    public void testFactoryBean() {
        // 测试工厂Bean及普通Bean区别
        // 普通的bean定义是XML中配置什么类型就接收什么类型
        // 而通过实现FactoryBean<T> 接口可以达到配置实现类的bean，可以指定其返回值类型

        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id

        // 需要注意，以普通对象创建的方式去接收工厂实现，会报错：BeanNotOfRequiredTypeException
        // BeanFactoryDemo bean = context.getBean("demo", BeanFactoryDemo.class);
        // Bean named 'demo' is expected to be of type 'com.practice.factory.BeanFactoryDemo' but was actually of type 'com.practice.entity.BeanDemo'

        // FactoryBean指定泛型后需要使用指定的类型或其超类去接收
        BeanDemo bean = context.getBean("demo", BeanDemo.class);
        // class com.practice.entity.BeanDemo
        // {demoCode=null, demoString=null, demoSize=null, demoBool=false, demoList=null, demoMap=null, demoArray=null, connect=null, connects=null}
        System.out.println(bean);
    }

    @Test
    public void testScope() {
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id
        BeanDemoScope beanScope = context.getBean("demoScope", BeanDemoScope.class);
        System.out.println(beanScope);
        BeanDemoScope beanScope1 = context.getBean("demoScope", BeanDemoScope.class);
        System.out.println(beanScope1);

         /*
             单例对象：
             <bean id="demoScope" class="com.practice.entity.BeanDemoScope" scope="singleton"/>
             获取的两个实例的地址是同一个：构造器只执行了一次
             调用无参构造器！
             com.practice.entity.BeanDemoScope@770c2e6b
             com.practice.entity.BeanDemoScope@770c2e6b

             多例对象：
             <bean id="demoScope" class="com.practice.entity.BeanDemoScope" scope="prototype"/>
             获取的两个实例的地址不同：构造器执行了多次
             调用无参构造器！
             com.practice.entity.BeanDemoScope@18a70f16
             调用无参构造器！
             com.practice.entity.BeanDemoScope@62e136d3
         */
    }

    @Test
    public void testBeanLife() {
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");
        // 2.调用getBean方法获取实例，demo为配置文件中配置的示例标识id
        BeanDemoScope beanScope = context.getBean("demoScope", BeanDemoScope.class);
        System.out.println("4.获取对象实例：" + beanScope);
        beanScope.useInstance();

        // 关闭容器，销毁实例对象
        context.close();

        /*
            对象的生命周期：
            1.调用无参构造器！
            2.调用setter方法！
            BeanDemoPostProcessor::postProcessBeforeInitialization，初始化前执行的方法：Bean => demoScope
            3.调用实例初始化方法！
            BeanDemoPostProcessor::postProcessAfterInitialization，初始化后执行的方法：Bean => demoScope
            4.获取对象实例：com.practice.entity.BeanDemoScope@12d3a4e9
            4.1.使用实例！code = setter
            scope为singleton会输出以下语句，prototype不输出，容器中确实无法获取该实例
            5.调用实例销毁的方法！

            容器关闭后无法获取实例对象
            java.lang.IllegalStateException: BeanFactory not initialized or already closed
            - call 'refresh' before accessing beans via the ApplicationContext
            想要重新启用容器：先执行刷新方法
            context.refresh();
            context.getBean("demoScope", BeanDemoScope.class).useInstance();
        */
    }

}
