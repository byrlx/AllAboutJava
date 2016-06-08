package com.lx.testandverify;

import com.lx.util.Log;

import java.util.concurrent.*;

/**
 * Test blocking queue
 */
public class blockingQueue<T> {
    private BlockingQueue<T> queue;

    public blockingQueue() {
        queue = new ArrayBlockingQueue<T>(5);
    }

    public T get() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void put(T obj) {
        try {
            queue.put(obj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        blockingQueue<String> bq = new blockingQueue<>();

        ExecutorService es = Executors.newFixedThreadPool(20);
        for (int i = 1; i < 21; i++) {
            final int tmp = i;
//            if (i % 4 == 0) {
            if (i <= 3 || i >= 15) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Log.e("thread start:" + tmp);
                        String s = bq.get();
                        Log.e("thread " + tmp + " get " + s);
                    }
                };
                es.submit(r);
            } else {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Log.e("thread start: " + tmp);
                        bq.put(tmp + "");
                        Log.e("thread put down: " + tmp);
                    }
                };
                es.submit(r);
            }
        }
    }

}
