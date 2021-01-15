package com.practice.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void schedule() {
        System.out.println("Time: " + new Date());
    }

}
