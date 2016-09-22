package com.lx.j8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created by lx on 9/8/16.
 */
public class J8Basic {

    interface J8Interface {
        void println(String s);
    }

    class J8InterfaceImpl implements J8Interface {
        @Override
        public void println(String s) {
            System.out.println("interface says: " + s);
        }
    }

    void testInterface(J8Interface i) {
        i.println("helo java 8");
    }

    public static void main(String[] args) {
        J8Basic obj = new J8Basic();

        obj.testInterface(s -> System.out.println("hel"));
        obj.testCollections();
        obj.testRunnable();
    }

    void testCollections() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("z");
        strings.add("f");
        strings.add("m");

        long cnt = strings.stream()
                .map(s -> s.toUpperCase())
                .filter(s -> s.compareTo("A") > 0)
                .count();

        System.out.println("" + cnt);
    }

    void testRunnable() {
        Runnable runnable = () -> System.out.println("this is runnable");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnable);
    }

    void test() {
        BinaryOperator<Long> add = (x, y) -> x + y;
    }
}
