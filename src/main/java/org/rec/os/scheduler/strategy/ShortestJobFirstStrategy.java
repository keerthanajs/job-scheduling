package org.rec.os.scheduler.strategy;

import org.rec.os.scheduler.JobDetails;

import java.util.Queue;

public class ShortestJobFirstStrategy implements SchedulingStrategy {
    @Override
    public JobDetails findNextJob(Queue<JobDetails> jobQueue) {
        synchronized (jobQueue){
            //find job with shortest execution time
            JobDetails jobDetails = jobQueue.peek();
            if(jobDetails!=null){
                for(JobDetails jd: jobQueue){
                    if(jd.getJob().executionTimeMillis() < jobDetails.getJob().executionTimeMillis()){
                        jobDetails = jd;
                    }
                }
                jobQueue.remove(jobDetails);
            }
            return jobDetails;
        }
    }
}
