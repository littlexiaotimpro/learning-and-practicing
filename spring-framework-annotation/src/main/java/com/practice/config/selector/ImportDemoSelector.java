package com.practice.config.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * @author XiaoSi
 * @className ImportDemoSelector
 * @description 自定义的导入选择器
 * @date 2020/9/10
 */
public class ImportDemoSelector implements ImportSelector {

   private static final Logger logger = LoggerFactory.getLogger(ImportDemoSelector.class);

    /**
     * 选择需要导入的类进容器
     * @param importingClassMetadata 当前类的注解信息
     * @return 需要导入的类的全限定类名数组，不能返回null
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
        logger.info(annotationTypes.toString());
        return new String[]{"com.practice.entity.DemoBeanD"};
    }
}
