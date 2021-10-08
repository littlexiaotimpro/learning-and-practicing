package com.practice.config.selector;

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public class BeanPackageScanImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] basePackages = new String[]{"com.practice"};
        ClassPathScanningCandidateComponentProvider scanner
                = new ClassPathScanningCandidateComponentProvider(false);
        TypeFilter typeFilter = new AnnotationTypeFilter(Component.class);
        scanner.addIncludeFilter(typeFilter);
        Set<String> classes = new HashSet<>();
        for (String basePackage : basePackages) {
            scanner.findCandidateComponents(basePackage)
                    .forEach(beanDefinition -> classes.add(beanDefinition.getBeanClassName()));
        }
        System.out.println(classes);
        return classes.toArray(new String[0]);
    }
}
