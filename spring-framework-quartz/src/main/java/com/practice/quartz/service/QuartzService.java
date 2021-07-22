package com.practice.quartz.service;

import com.practice.quartz.bean.QuartzJob;
import org.quartz.*;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Service
public class QuartzService {

    private final Scheduler scheduler;

    public QuartzService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void startNewJob(String taskName, String taskGroup) {
        final SimpleTriggerImpl trigger = (SimpleTriggerImpl) TriggerBuilder.newTrigger()
                .withIdentity(taskName, taskGroup)
                .startAt(new Date())
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(3))
                .build();
        JobDetail job = JobBuilder.newJob(QuartzJob.class)
                .withIdentity(taskName, taskGroup)
                .build();
        try {
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
