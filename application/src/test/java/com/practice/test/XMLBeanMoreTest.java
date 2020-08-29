package com.practice.test;

import com.practice.entity.BeanDemoXML;
import com.practice.service.DocumentHandlerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 测试Spring的基于XML配置的Bean创建与属性注入
 */
public class XMLBeanMoreTest {

    @Test
    public void testBean() {
        // 验证简单的对象创建
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config-more.xml");
        // 2.使用xml的自动装配功能，实现属性注入
        BeanDemoXML bean = context.getBean("demo", BeanDemoXML.class);
        System.out.println(bean);

        /*
         * 配置bean标签的 autowire 属性实现自动注入
         * 1.byName:
         * 依据属性名进行注入，会匹配与属性名称相同的对象
         * 属性名相同，则无法注入对象如下，定义 id="connect"，BeanDemoConnect的实例不为null
         * class com.practice.entity.BeanDemoAnnotation {connect=class com.practice.entity.BeanDemoConnect{connectCode=null, connectString=null}}
         *
         * 属性名不同，则无法注入对象如下，定义 id="demoConnect"
         * class com.practice.entity.BeanDemoAnnotation {connect=null}
         *
         * 2. byType:
         * 依据类型注入，匹配与属性值类型相同的对象进行注入
         * 无论id定义如何，结果都为：
         * class com.practice.entity.BeanDemoAnnotation {connect=class com.practice.entity.BeanDemoConnect{connectCode=null, connectString=null}}
         *
         * 但是，如果定义两个属性相同类型的对象，通过类型匹配是会报错：UnsatisfiedDependencyException，NoUniqueBeanDefinitionException
         * No qualifying bean of type 'com.practice.entity.BeanDemoConnect'
         * available: expected single matching bean but found 2: connect,demoConnect
         * 提示本应该是一个匹配的对象实例，但是却找到了两个
         */
    }

    @Test
    public void testXMLComponentScan() {
        // 验证简单的对象创建
        // 1.通过资源文件获取实现，解析 XML 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config-more.xml");

        // 组件扫描过程中，日志中打印了实现类的实例创建信息
        // DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory
        // - Creating shared instance of singleton bean 'documentHandlerServiceImpl'

        // 2.获取组件实例
        DocumentHandlerService bean = context.getBean("documentInstance", DocumentHandlerService.class);
        System.out.println(bean);
        bean.uploadExcel();

        /*
         * 组件扫描的过程会将所有添加了注解@Component的类都加载进容器
         * 常见注解有：@Controller、@RestController、@Service、@Configuration、@Repository、@Component ...
         * 这些注解内部都添加了@Component注解，在执行Component-scan的过程中，会被加载
         *
         * 默认的注解如果不设置具体的value值，那么创建的实例就是当前类名的首字母小写, 如, DocumentHandlerServiceImpl
         *
         * @Service
         * 不指定实例名,则创建的实例名就是 documentHandlerServiceImpl
         * 日志: Creating shared instance of singleton bean 'documentHandlerServiceImpl'
         *
         * @Service("documentInstance")
         * 指定实例名,则可以通过此名称获取当前类型的实例对象
         * 日志: Creating shared instance of singleton bean 'documentInstance'
         */
    }
}
