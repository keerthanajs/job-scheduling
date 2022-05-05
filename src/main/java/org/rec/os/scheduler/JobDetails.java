package org.rec.os.scheduler;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class JobDetails {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int id;
    private final Date arrivalTime;
    private Date completionTime;
    private final Job job;

    public JobDetails(Job job){
        this.job = job;
        id = COUNTER.getAndIncrement();
        arrivalTime = new Date();
    }

    public void setCompletionTime(Date date){
        this.completionTime = date;
    }

    public int getId() {
        return id;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Job getJob() {
        return job;
    }

    public Date getCompletionTime(){
        return completionTime;
    }

    @Override
    public String toString() {
        return String.format("[ID=%s, Name=%s, ArrivalTime=%s]", id, job.name(), arrivalTime);
    }
}
