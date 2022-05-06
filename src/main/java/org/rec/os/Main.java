package org.rec.os;


import org.rec.os.jobs.SimpleJob;
import org.rec.os.scheduler.JobScheduler;
import org.rec.os.scheduler.strategy.SchedulingStrategy;

public class Main {
    public static void main(String[] args) {
        int cores = Integer.getInteger("cores", 1);
        long startTime = System.currentTimeMillis();
        JobScheduler js = new JobScheduler(cores);
        js.submit(new SimpleJob("Report", 2000));
        js.submit(new SimpleJob("AccountSummary", 5000));
        js.submit(new SimpleJob("TaxCalculation", 3000));
        js.submit(new SimpleJob("BatchProcessing", 6000));
        js.submit(new SimpleJob("Indexing", 1000));
        js.submit(new SimpleJob("Backup", 4000));
        js.start();
        js.waitAndShutdown();
        long endTime = System.currentTimeMillis();
        System.out.printf("Total time taken: %s millis, cores: %s, strategy: %s%n", (endTime-startTime), cores,
                SchedulingStrategy.getDefault().getClass().getSimpleName());
    }
}
