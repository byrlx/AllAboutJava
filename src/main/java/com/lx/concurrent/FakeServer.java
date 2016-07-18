package com.lx.concurrent;

import com.lx.Log;
import com.lx.util.Common;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用该例子来模拟JICP中的Servlet
 */
public class FakeServer {
    int count = 0;  //Not Thread Safe
    /**
     * 使用{@link AtomicInteger}保证了操作的原子性
     */
    AtomicInteger ai = new AtomicInteger(0); //Thread Safe


    FakeServer() {
        lastNum.set(-1);
        result.set(new int[]{-1, -2});
    }

    /**
     * 该函数不是线程安全的, 虽然lastNum和result本身的操作是原子的,
     * 但是对这两个进行复合操作不是原子的.
     * 可能会出现更新了一个变量, 另一个变量却没有更新的现象.
     * 尤其是加入sleep会放大这个现象.
     *
     * @param bi
     * @return
     */
    //These two are not thread safe
    private final AtomicReference<Integer> lastNum = new AtomicReference<>();
    private final AtomicReference<int[]> result = new AtomicReference<>();

    public int[] unsafeService(int bi) {
        count++;
        ai.incrementAndGet();

        if (bi == lastNum.get()) {
            return result.get();
        }

        lastNum.set(bi);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int[] subs = new int[2];
        subs[0] = 1;
        subs[1] = bi - 1;
        result.set(subs);

        return result.get();
    }

    /**
     * 通过加入关键字, 可以避免多状态同步问题
     */
    int lastNum2 = -1;
    int[] result2 = new int[]{-1, -2};
    public synchronized int[] safeService(int bi) {
        if (bi == lastNum2) return result2;
        lastNum2 = bi;

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result2[0] = 1;
        result2[1] = bi - 1;
        return result2;
    }

    public static void test() {
        int[] randoms = Common.getRandomIntArr(5, 1000);
        FakeServer fakeServer = new FakeServer();
        for (int i = 0; i < 1000; i++) {
            final int num = randoms[i];
            final int order = i;
            new Thread(() -> {
                int[] result = fakeServer.unsafeService(num);
                if ((result[0] + result[1]) != num) {
                    Log.e("unsafeService return a wrong answer");
                }
                /**
                 int[] result2 = fakeServer.safeService(num);
                 if ((result2[0] + result2[1]) != num) {
                 Log.e("safeService return a wrong answer: num:" + num + "; result:" + result2[0] + "/" + result2[1]);
                 }
                 */
            }).start();
        }

        try {
            Thread.sleep(3000);
            Log.e("count:" + fakeServer.count + "; atomicCount:" + fakeServer.ai.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
