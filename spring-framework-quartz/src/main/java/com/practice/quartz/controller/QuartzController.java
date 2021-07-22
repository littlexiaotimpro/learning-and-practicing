package com.practice.quartz.controller;

import com.practice.quartz.service.QuartzService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuartzController {

    private final QuartzService quartzService;

    public QuartzController(QuartzService quartzService) {
        this.quartzService = quartzService;
    }

    @GetMapping(value = "quartz/jobs")
    public void createJobs() {
        for (int i = 0; i < 10; i++) {
            quartzService.startNewJob("quartz_task_" + i, "quartz_group");
        }
    }

}
