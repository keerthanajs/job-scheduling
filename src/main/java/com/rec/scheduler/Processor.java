package com.rec.scheduler;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Processor {
    private int numberOfCores;
    private Queue<Job> readyQueue;
    private ExecutorService executorService;
    private volatile boolean shutdownInitiated;

    public Processor(int numberOfCores, Queue<Job> readyQueue){
        this.numberOfCores = numberOfCores;
        this.readyQueue = readyQueue;
    }

    public void powerOn(){
        executorService = Executors.newFixedThreadPool(numberOfCores);
        //call process() method asynchronously
        new Thread(()->process()).start();
    }

    private void process(){
        while(!shutdownInitiated){
            while(readyQueue.isEmpty() && !shutdownInitiated){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {}
            }

            if(!readyQueue.isEmpty()) {
                //queue is not empty, retrieve the next job
                Job nextJob = readyQueue.remove();

                //ask the available core to execute the job
                executorService.submit(nextJob);
            }
        }
    }

    public void powerOff(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {}
        shutdownInitiated = true;
    }

}
