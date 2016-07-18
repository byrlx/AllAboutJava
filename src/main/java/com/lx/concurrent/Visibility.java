package com.lx.concurrent;

import com.lx.Log;

/**
 * Created by lx on 7/8/16.
 */
public class Visibility {
    static boolean ready;
    static int number;

    static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            Log.e(number+"");
        }
    }

    public static void test(){
        new ReaderThread().start();
        number = 2;
        ready = true;
    }
}
