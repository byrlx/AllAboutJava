package com.lxjlib.concurrent;


import com.lxjlib.Log;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by douhua on 6/13/16.
 */
public class Basic {
    Random random = new Random(20);

    class StringCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            int value = (random.nextInt(20) + 20) % 20;
            Log.e("sleep " + value+ "s after start thread:" + Thread.currentThread().getId());
            Thread.sleep(value * 1000);
            return ("this is thread:" + Thread.currentThread().getId());
        }
    }

    public void testExecutor() {
        System.out.printf("%d, %x, %x, %x, %x\n",  -1<<29, 0<<29, (1<<29) -1, 2<<29, 3<<29);
        Log.e(String.valueOf(-1<<29));
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<String>> results = Collections.synchronizedList(new ArrayList<>(15));
        for (int i = 0; i < 15; i++) {
            StringCallable callable = new StringCallable();
            results.add(service.submit(callable));
        }

        for (; ; ) {
            int loopNum = 0;
            ListIterator<Future<String>> iterator = results.listIterator();
            while(iterator.hasNext()){
                Future<String> temp = iterator.next();
                try {
                    Log.e(temp.get(1, TimeUnit.SECONDS));
                    loopNum++;
                    iterator.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    Log.e("time out");
                    continue;
                }
            }
            Log.e("loopNum is " + loopNum + "; size is " + results.size());
            if (results.size() == 0) break;
        }
    }

    public static void test() {
        (new Basic()).testExecutor();
    }
}
