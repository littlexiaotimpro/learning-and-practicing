package com.practice.config.register;

import com.practice.entity.DemoBeanC;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * 通过{@link org.springframework.context.annotation.Import}导入的类会自动加入容器的管理行列中
 * 不需要在手动添加 @Component 或通过 @Bean 的方式注入
 * @author XiaoSi
 * @className ImportDemoBeanDefinitionRegistrar
 * @description 自定义的Bean定义注册器
 * @date 2020/9/10
 */
public class ImportDemoBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 自定义注册Bean
     * @param importingClassMetadata 导入的类的注解信息
     * @param registry Bean定义的注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
        System.out.println(annotationTypes);
        // 定义注入的实例的Bean定义
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(DemoBeanC.class);
        // 设置其作用域：单例
        rootBeanDefinition.setScope("singleton");
        // @Import 注入的实例默认是类的全限定类名，自定义注入的可以指定注入的实例名
        registry.registerBeanDefinition("demoBeanC",rootBeanDefinition);
    }
}
