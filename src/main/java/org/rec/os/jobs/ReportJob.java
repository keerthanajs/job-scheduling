package org.rec.os.jobs;

import org.rec.os.scheduler.Job;

public class ReportJob implements Job {
    @Override
    public void execute() {
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
