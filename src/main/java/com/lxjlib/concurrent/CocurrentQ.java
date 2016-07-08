package com.lxjlib.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by douhua on 5/5/16.
 */
public final class CocurrentQ {
    private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public static void add(String element) {
        queue.add(element);
    }

    public static void print() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!queue.isEmpty()) {
                    String element = queue.remove();
                    System.out.println(element + ";" + Thread.currentThread().getId() + ";" + System.currentTimeMillis());
                }
            }
        }).start();
    }

    public static void test() {
        for (int i = 0; i < 1000; i++) {
            final int m = i;
            add(m + "");
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    add(m + "");
//                }
//            });
        }
        for (int i = 0; i < 4; i++) {
            print();
        }
    }
}
