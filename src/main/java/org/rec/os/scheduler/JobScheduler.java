package org.rec.os.scheduler;

import org.rec.os.scheduler.strategy.SchedulingStrategy;
import org.rec.os.utils.LogUtils;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 */
public class JobScheduler {
    private Processor processor;
    private Queue<JobDetails> readyQueue;

    public JobScheduler(int parallelCount){
        readyQueue = new ArrayDeque<>(); //FCFS
        processor = new Processor(parallelCount, readyQueue);
        LogUtils.log("Running with "+parallelCount+" core(s)");
    }

    public void start(){
        LogUtils.log("Using "+ SchedulingStrategy.getDefault().getClass().getSimpleName());
        processor.powerOn();
    }

    public void submit(Job job){
        JobDetails jd = new JobDetails(job);
        readyQueue.offer(jd);
        LogUtils.log(jd+" Job submitted.");
    }

    public void waitAndShutdown(){
        processor.powerOff();
    }
}
