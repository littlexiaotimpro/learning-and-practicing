package com.practice.quartz.bean;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;

public class QuartzJob implements Job, Serializable {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        class MyTask implements Runnable, Serializable {
            @Override
            public void run() {
                final Thread thread = Thread.currentThread();
                System.out.println(
                        String.format(
                                "taskGroup[%s], taskName[%s], 执行完成",
                                thread.getThreadGroup().getName(),
                                thread.getName()));
            }
        }
        final MyTask myTask = new MyTask();
        myTask.run();
    }
}
