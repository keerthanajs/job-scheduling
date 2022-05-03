package com.rec.scheduler;

public interface Job extends Runnable{
    void run();
    int executionTimeMillis(); //used in shortest job first
}
