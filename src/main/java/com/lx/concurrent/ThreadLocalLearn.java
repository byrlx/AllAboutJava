package com.lx.concurrent;

import com.lx.Log;

/**
 * Created by lx on 7/8/16.
 */
public class ThreadLocalLearn {
    private String globalStr;
    private ThreadLocal<String> threadLocalStr = new ThreadLocal<String>(){
        public String initialValue(){
            return globalStr;
        }
    };

    public ThreadLocalLearn(String string) {
        globalStr = string;
        threadLocalStr.set(string);
    }

    public void setStr(String s) {
        globalStr = s;
    }

    public String getStr(){
        return threadLocalStr.get();
    }

    public static void test(){
        MainThreads threads = new MainThreads();
        ThreadLocalLearn tll = new ThreadLocalLearn("fisrt");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(tll.getStr());
            }
        };

        threads.start(100, runnable);

        tll.setStr("second");
    }
}
