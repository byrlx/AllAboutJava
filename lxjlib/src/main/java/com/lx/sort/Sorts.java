package com.lx.sort;

import com.lx.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for sort
 */
public class Sorts {
    public static Sorter sorter;

    static String[] data = new String[] {"c", "e", "z", "a", "l", "l", "m", "b", "q", "o", "b"};
    static List<String> data2;
    static {
        data2 = new ArrayList<>();
        //"c", "e", "z", "a", "l", "l", "m", "b", "q", "o", "b"
        data2.add("c");
        data2.add("e");
        data2.add("z");
        data2.add("a");
        data2.add("l");
        data2.add("l");
        data2.add("m");
        data2.add("b");
        data2.add("q");
        data2.add("o");
        data2.add("b");
    }

    public static <T> void sort(Sorter<T> sorter, T[] data) {
        sorter.sort(data);
    }

    public static <T> void sort(Sorter<T> sorter, List<T> data) {
        sorter.sort(data);
    }

    public static void test(){

//        sort(new InsertSorter<>(), data2);
//        sort(new SelectSorter<String>(), data);
        sort(new BubbleSorter<String>(), data);

        Log.e(data);
    }
}
