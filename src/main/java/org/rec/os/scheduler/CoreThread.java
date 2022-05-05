package org.rec.os.scheduler;

import org.rec.os.scheduler.strategy.SchedulingStrategy;

import java.util.Date;
import java.util.Queue;

public class CoreThread extends Thread{
    private Queue<JobDetails> readyQueue;
    private volatile boolean shutdownInitiated;

    public CoreThread(int id, Queue<JobDetails> readyQueue) {
        this.readyQueue = readyQueue;
        setName("Core#"+(id+1));
    }

    @Override
    public void run() {
        while(true) {
            //run the jobs from the ready queue as long as the queue is not empty
            while(!readyQueue.isEmpty()){
                //retrieve the next job
                JobDetails jobDetails = SchedulingStrategy.getDefault().findNextJob(readyQueue);
                //run the next job in this core
                if(jobDetails!=null){
                    jobDetails.getJob().execute();
                    jobDetails.setCompletionTime(new Date());
                }
            }
            //sleep 1 millisecond before checking the queue again
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}

            //exit the loop only when ready queue is empty and shutdown is initiated
            if(shutdownInitiated && readyQueue.isEmpty()){
                break;
            }
        }
    }

    public void shutdown(){
        shutdownInitiated = true;
    }
}
