package com.lx.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of bubble sort:
 * traverse the data set every time, compare the two elements
 * next to each other, exchange them if order is wrong.
 */
public class BubbleSorter<T> extends BasicSorter<T> {
    @Override
    public void sort(T[] data) {
        sort(data, null);
    }

    @Override
    public void sort(T[] data, Comparator<T> c) {
        for (int len = data.length - 1; len > 0; len--) {
            for (int i = 0; i < len; i++) {
                if (compare(data[i], data[i + 1], c) > 0) {
                    swap(data, i, i + 1);
                }
            }

        }
    }

    @Override
    public void sort(List<T> data) {

    }

    @Override
    public void sort(List<T> data, Comparator<T> c) {

    }
}
