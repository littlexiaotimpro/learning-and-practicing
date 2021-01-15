package com.practice.cron;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = {"com.practice.cron"})
@EnableScheduling
public class CronConfiguration {
}
