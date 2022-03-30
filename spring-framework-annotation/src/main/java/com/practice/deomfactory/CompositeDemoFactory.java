package com.practice.deomfactory;


import java.util.List;

public class CompositeDemoFactory {

    public CompositeDemoFactory() {

    }

    public CompositeDemoFactory(List<DemoFactory> demoFactories) {
        demoFactories.forEach(DemoFactory::apply);
    }
}
