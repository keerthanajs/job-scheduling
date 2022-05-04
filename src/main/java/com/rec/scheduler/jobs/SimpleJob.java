package com.rec.scheduler.jobs;

import com.rec.scheduler.Job;
import com.rec.scheduler.LogUtils;

public class SimpleJob implements Job {
    private final String name;
    private final int executionTime;

    public SimpleJob(String name, int executionTime){
        this.name = name;
        this.executionTime = executionTime;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void execute() {
       LogUtils.log("*** ["+ name+"] Job started by "+Thread.currentThread().getName());
        try {
            Thread.sleep(executionTimeMillis());
        } catch (InterruptedException e) {
        }
        LogUtils.log("###### ["+ name+"] Job finished in "+executionTimeMillis()+" ms by "+Thread.currentThread().getName());
    }

    @Override
    public int executionTimeMillis() {
        return executionTime;
    }
}
