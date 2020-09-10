package com.practice.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author XiaoSi
 * @className BeanCreateCondition
 * @description @Conditional注解的条件实现，不符合条件的Bean不会被自动创建
 * @date 2020/9/10
 */
public class BeanCreateCondition implements Condition {
    /**
     * matches方法，自定义自动注入的bean的注入条件
     * @param context 当前条件的上下文信息，如，beanFactory、environment等
     * @param metadata 当前类的注解信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("os.name");
        return "linux".equals(property);
    }
}
