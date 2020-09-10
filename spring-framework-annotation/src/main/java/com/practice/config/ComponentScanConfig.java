package com.practice.config;

import com.practice.config.selector.ImportDemoSelector;
import com.practice.entity.DemoBean;
import com.practice.config.register.ImportDemoBeanDefinitionRegistrar;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.practice"})
@Import({ImportDemoSelector.class,ImportDemoBeanDefinitionRegistrar.class})
public class ComponentScanConfig {

    @Bean(name="demoBean",initMethod = "init",destroyMethod = "destroy")
    @Scope("prototype")
    public DemoBean demoBean(){
        return new DemoBean();
    }

}
