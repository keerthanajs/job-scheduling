package org.rec.os.jobs;

import org.rec.os.scheduler.Job;

public class ConsolidatedAccountStatementJob implements Job {
    @Override
    public void execute() {
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
