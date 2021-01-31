package com.practice.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Autowired 的注入形式
 */
@Component
public class DemoBeanF {

    private final DemoBeanC demoBeanC;
    private final DemoBeanD demoBeanD;

    @Autowired
    public DemoBeanF(DemoBeanC demoBeanC, DemoBeanD demoBeanD) {
        this.demoBeanC = demoBeanC;
        this.demoBeanD = demoBeanD;
    }

    public DemoBeanC getDemoBeanC() {
        return demoBeanC;
    }

    public DemoBeanD getDemoBeanD() {
        return demoBeanD;
    }
}
