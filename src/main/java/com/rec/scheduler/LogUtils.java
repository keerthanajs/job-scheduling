package com.rec.scheduler;

import java.util.Date;

public class LogUtils {
    public static void log(String message){
        System.out.printf("[%s] %s %n", new Date(), message);
    }
}
