package com.rec.scheduler;


import com.rec.scheduler.jobs.ConsolidatedAccountStatementJob;
import com.rec.scheduler.jobs.ReportJob;

public class Main {
    public static void main(String[] args) {
        JobScheduler js = new JobScheduler(2);
        js.submit(new ReportJob());
        js.submit(new ConsolidatedAccountStatementJob());
        js.waitAndShutdown();
    }
}
