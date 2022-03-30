package com.practice.deomfactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoAFactory implements DemoFactory {
    @Override
    public void apply() {
        log.debug("【{}】", this.getClass().getSimpleName());
    }
}
