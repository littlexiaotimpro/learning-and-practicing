package com.practice.entity;

import org.springframework.beans.factory.annotation.Value;

public class DemoBeanC {

    @Value("${default.c-value}")
    private int demoInt;

}
