package com.lx.concurrent;

/**
 * Created by lx on 7/8/16.
 */
public class MainThreads {
    private static final int DEFAULT_THREAD_NUM = 100;
    private int threadNumber = DEFAULT_THREAD_NUM;

    public void setThreadNumber(int num) {
        threadNumber = num;
    }

    public void start(int num, Runnable runnable) {
        for(int i=0; i<num; i++) {
            new Thread(runnable).start();
        }
    }
    public void start(Runnable runnable){
        start(threadNumber, runnable);
    }
}
