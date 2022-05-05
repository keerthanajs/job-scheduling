package org.rec.os;


import org.rec.os.jobs.SimpleJob;
import org.rec.os.scheduler.JobScheduler;

public class Main {
    public static void main(String[] args) {
        JobScheduler js = new JobScheduler(1);
        js.submit(new SimpleJob("Report", 2000));
        js.submit(new SimpleJob("AccountSummary", 5000));
        js.submit(new SimpleJob("TaxCalculation", 3000));
        js.submit(new SimpleJob("BatchProcessing", 6000));
        js.submit(new SimpleJob("Indexing", 1000));
        js.submit(new SimpleJob("Backup", 4000));
        js.start();
        js.waitAndShutdown();
    }
}
