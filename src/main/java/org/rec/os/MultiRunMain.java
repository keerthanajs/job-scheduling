package org.rec.os;

import org.rec.os.jobs.SimpleJob;
import org.rec.os.scheduler.Job;
import org.rec.os.scheduler.JobScheduler;
import org.rec.os.scheduler.strategy.SchedulingStrategy;
import org.rec.os.ui.BarChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MultiRunMain {
    static final int NO_OF_JOBS = 10;
    static Job[] jobs = new Job[NO_OF_JOBS];
    static int[] CORES_ARRAY = {1, 2, 4, 6, 8, 10};
    static {
        for(int i=1;i<=jobs.length;i++){
            jobs[i-1] = new SimpleJob("Job-"+i, i*100);
        }
        List<Job> jobList = new ArrayList<>();
        jobList.addAll(Arrays.asList(jobs));
        Collections.shuffle(jobList);
        jobs = jobList.toArray(new Job[0]);
    }

    public static void main(String[] args) {
        long[] sjsfTimes = new long[CORES_ARRAY.length];
        long[] fcfsTimes = new long[CORES_ARRAY.length];

        for(int i=0;i<CORES_ARRAY.length;i++){
            sjsfTimes[i] = runShortestJobFirstWithCores(CORES_ARRAY[i]);
            fcfsTimes[i] = runFirstComeFirstServe(CORES_ARRAY[i]);
        }
        System.out.println("Durations: "+Arrays.asList(jobs));
        System.out.println("CORES : "+Arrays.toString(CORES_ARRAY));
        System.out.println("SJSF  : "+Arrays.toString(sjsfTimes));
        System.out.println("FCFS  : "+Arrays.toString(fcfsTimes));
        BarChart.setData(CORES_ARRAY, sjsfTimes, fcfsTimes);
        BarChart.main(args);
    }

    static long runJobs(int noOfCores){
        long startTime = System.currentTimeMillis();
        JobScheduler js = new JobScheduler(noOfCores);
        for(Job job: jobs){
            js.submit(job);
        }
        js.start();
        js.waitAndShutdown();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    static long runShortestJobFirstWithCores(int noOfCores){
        System.setProperty("strategy","sjf");
        long timeTaken = runJobs(noOfCores);
        System.out.printf("Total time taken: %s millis, cores: %s, strategy: %s%n", timeTaken, noOfCores,
                SchedulingStrategy.getDefault().getClass().getSimpleName());
        return timeTaken;
    }

    static long runFirstComeFirstServe(int noOfCores){
        System.setProperty("strategy","fcfs");
        long timeTaken = runJobs(noOfCores);
        System.out.printf("Total time taken: %s millis, cores: %s, strategy: %s%n", timeTaken, noOfCores,
                SchedulingStrategy.getDefault().getClass().getSimpleName());
        return timeTaken;
    }

}
