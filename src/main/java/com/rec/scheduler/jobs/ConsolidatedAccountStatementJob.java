package com.rec.scheduler.jobs;

import com.rec.scheduler.Job;

public class ConsolidatedAccountStatementJob implements Job {
    @Override
    public void run() {
        System.out.println("Preparing consolidated account statement");
        try {
            Thread.sleep(executionTimeMillis());
        } catch (InterruptedException e) {
        }
        System.out.println("Account statement creation completed");
    }

    @Override
    public int executionTimeMillis() {
        return 2000;
    }
}
