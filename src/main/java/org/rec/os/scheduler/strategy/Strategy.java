package org.rec.os.scheduler.strategy;

import org.rec.os.scheduler.Job;

import java.util.Queue;

public interface Strategy {
    Job findNextJob(Queue<Job> readyQueue);
}
