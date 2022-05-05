package org.rec.os.scheduler.strategy;

import org.rec.os.scheduler.Job;

import java.util.Queue;

public class FirstComeFirstServeStrategy implements Strategy {
    @Override
    public Job findNextJob(Queue<Job> jobQueue) {
        return jobQueue.poll();
    }
}
