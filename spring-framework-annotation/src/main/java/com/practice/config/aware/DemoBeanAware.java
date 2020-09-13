package com.practice.config.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class DemoBeanAware implements ApplicationContextAware, BeanFactoryAware,
        ResourceLoaderAware, EmbeddedValueResolverAware {

    // 容器
    private ApplicationContext applicationContext;
    // Bean工厂
    private BeanFactory beanFactory;
    // 资源加载器
    private ResourceLoader resourceLoader;
    // 表达式解析
    private StringValueResolver stringValueResolver;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        // 解析表达式
        String s = resolver.resolveStringValue("#{${default.d-value}+22}");
        System.out.println(s);
        this.stringValueResolver = resolver;
    }

    public StringValueResolver getStringValueResolver() {
        return this.stringValueResolver;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }
}
