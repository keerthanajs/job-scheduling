package org.rec.os.scheduler.strategy;

import org.rec.os.scheduler.JobDetails;

import java.util.Queue;

public class FirstComeFirstServeStrategy implements SchedulingStrategy {
    @Override
    public JobDetails findNextJob(Queue<JobDetails> jobQueue) {
        return jobQueue.poll();
    }
}
