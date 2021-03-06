package org.rec.os.scheduler;

public interface Job {
    default String name(){
        return this.getClass().getName();
    }
    void execute();
    int executionTimeMillis(); //used in shortest job first
}
