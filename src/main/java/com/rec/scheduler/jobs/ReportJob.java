package com.rec.scheduler.jobs;

import com.rec.scheduler.Job;

public class ReportJob implements Job {
    @Override
    public void run() {
        System.out.println("Starting ReportJob");
        try {
            Thread.sleep(executionTimeMillis());
        } catch (InterruptedException e) {
        }
        System.out.println("ReportJob completed");
    }

    @Override
    public int executionTimeMillis() {
        return 100;
    }
}
