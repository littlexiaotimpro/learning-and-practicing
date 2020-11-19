package com.practice.mode.template.method;

import java.util.Arrays;
import java.util.List;

public class TemplateMethod extends AbstractTemplateMethod{

    private final List<AbstractTemplate> templateMethods;

    {
        templateMethods = Arrays.asList(new TemplateOne(), new TemplateTwo(), new TemplateThree());
    }

    @Override
    protected void executor() {
        templateMethods.forEach(templateMethod -> {
            templateMethod.start();
            templateMethod.first();
            templateMethod.second();
            templateMethod.third();
            templateMethod.end();
        });
    }
}
