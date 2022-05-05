package org.rec.os.scheduler.strategy;

import org.rec.os.scheduler.Job;

import java.util.Queue;

public class ShortestJobFirstStrategy implements Strategy {
    @Override
    public Job findNextJob(Queue<Job> jobQueue) {
        //find job with shortest execution time
        Job job = jobQueue.peek();
        if(job!=null){
            for(Job j: jobQueue){
                if(j.executionTimeMillis() < job.executionTimeMillis()){
                    job = j;
                }
            }
            jobQueue.remove(job);
        }
        return job;
    }
}
