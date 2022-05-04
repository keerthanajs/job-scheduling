package com.rec.scheduler;


import com.rec.scheduler.jobs.ConsolidatedAccountStatementJob;
import com.rec.scheduler.jobs.ReportJob;
import com.rec.scheduler.jobs.SimpleJob;

public class Main {
    public static void main(String[] args) {
        JobScheduler js = new JobScheduler(1);
        js.submit(new SimpleJob("Report", 2000));
        js.submit(new SimpleJob("AccountSummary", 5000));
        js.submit(new SimpleJob("TaxCalculation", 3000));
        js.submit(new SimpleJob("BatchProcessing", 10000));
        js.submit(new SimpleJob("Indexing", 2000));
        js.submit(new SimpleJob("Backup", 5000));
        js.waitAndShutdown();
    }
}
