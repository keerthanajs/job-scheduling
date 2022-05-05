package org.rec.os.scheduler;

import org.rec.os.utils.LogUtils;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * TODO
 * Job id to be created
 * Job Arrival time to be created
 * SJF to be implemented
 */
public class JobScheduler {
    private Processor processor;
    private Queue<Job> readyQueue;

    public JobScheduler(int parallelCount){
        readyQueue = new ArrayDeque<>(); //FCFS
        processor = new Processor(parallelCount, readyQueue);
        processor.powerOn();
    }

    public void submit(Job job){
        readyQueue.offer(job);
        LogUtils.log("["+job.name()+"] Job submitted.");
    }

    public void waitAndShutdown(){
        processor.powerOff();
    }
}
