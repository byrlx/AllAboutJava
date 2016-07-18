package com.lx.sort;

import java.util.Comparator;
import java.util.List;

/**
 * insert sort algorithm implementation:
 * select an element from index i, put it in the right position
 * in an ordered array with has all the data of [0, i-1]
 * <p>
 * Created by lx on 6/15/16.
 */
public class InsertSorter<T> extends BasicSorter<T> {
    @Override
    public void sort(T[] data) {
        sort(data, null);
    }

    @Override
    public void sort(T[] data, Comparator<T> c) {
        if (c == null && !(data[0] instanceof Comparable))
            throw new IllegalArgumentException("If data is not subclass of Comparable, you must pass an comparator");

        int sz = data.length;

        for (int cur = 0; cur < sz; cur++) {
            T temp = data[cur];
            int cmp, index = -1;
            for (cmp = cur - 1; cmp >= 0; cmp--) {
                if (compare(temp, data[cmp], c) > 0) {
                    index = cmp + 1; //where to insert
                    break;
                }
            }

            //should insert to index 0
            if (cmp == -1) {
                index = 0;
            }

            System.arraycopy(data, index, data, index + 1, cur - index);
            data[index] = temp;
        }

    }

    @Override
    public void sort(List<T> data) {
        sort(data, null);
    }

    @Override
    public void sort(List<T> data, Comparator<T> c) {
        T[] temp = (T[]) new Object[1];
        T[] arr = data.toArray(temp);
        sort(arr, c);

        for (int i = 0; i < arr.length; i++) {
            data.set(i, arr[i]);
        }
    }
}
