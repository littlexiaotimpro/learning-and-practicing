package com.practice.config;

import com.practice.config.factory.DemoPropertySourceFactory;
import com.practice.config.selector.ImportDemoSelector;
import com.practice.entity.DemoBean;
import com.practice.config.register.ImportDemoBeanDefinitionRegistrar;
import com.practice.entity.DemoBeanE;
import org.springframework.context.annotation.*;

/**
 * 自定义factory，通过 PropertySource 加载 yml/yaml 配置文件
 */
@PropertySource(value = {"classpath:/props.yaml"}, factory = DemoPropertySourceFactory.class)
@Configuration
@ComponentScan(basePackages = {"com.practice"})
@Import({ImportDemoSelector.class,ImportDemoBeanDefinitionRegistrar.class})
public class ComponentScanConfig {

    @Bean(name="demoBean",initMethod = "init",destroyMethod = "destroy")
    @Scope("singleton")
    public DemoBean demoBean(){
        return new DemoBean();
    }

    @Bean
    public DemoBeanE demoBeanE(DemoBean demoBean){
        DemoBeanE demoBeanE = new DemoBeanE();
        demoBeanE.setDemoBean(demoBean);
        return demoBeanE;
    }
}
