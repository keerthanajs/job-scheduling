package org.rec.os.scheduler.strategy;

import org.rec.os.scheduler.JobDetails;

import java.util.Queue;

public interface SchedulingStrategy {
    public static SchedulingStrategy getDefault(){
        String strategy = System.getProperty("strategy", "FCFS");
        if("SJF".equalsIgnoreCase(strategy)){
            return new ShortestJobFirstStrategy();
        }else {
            return new FirstComeFirstServeStrategy();
        }
    }
    JobDetails findNextJob(Queue<JobDetails> readyQueue);
}
