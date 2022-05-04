package com.rec.scheduler;

import java.util.Queue;

public class Processor {
    private int numberOfCores;
    private Queue<Job> readyQueue;
    private CoreThread[] coreThreads;

    public Processor(int numberOfCores, Queue<Job> readyQueue){
        this.numberOfCores = numberOfCores;
        this.readyQueue = readyQueue;
    }

    public void powerOn(){
        this.coreThreads = new CoreThread[numberOfCores];
        for(int i=0;i<numberOfCores;i++){
            this.coreThreads[i] = new CoreThread(i, readyQueue);
            this.coreThreads[i].start();
        }
    }

    public void powerOff(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}

        for(int i=0;i<numberOfCores;i++){
            this.coreThreads[i].shutdown();
        }
    }

}
