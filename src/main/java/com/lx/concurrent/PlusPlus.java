package com.lx.concurrent;

import com.lx.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Verify "++" operation not thread safe
 */
public class PlusPlus {
    int value;
    //通过添加关键字实现同步
    synchronized int getSafeValue(){
        return value++;
    }

    //根据结果证明 '++' 操作不是原子操作
    int getValue(){
        return value++;
    }

    public static void test(){
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        final PlusPlus plusPlus = new PlusPlus();
        for(int i=0; i<1000;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add(plusPlus.getSafeValue());
                }
            }).start();
        }

        try {
            Thread.sleep(10*1000);
            Collections.sort(list);
            Log.e(list.toArray());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
