package com.lx.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of select sort algorithms:
 * picture a "smallest(biggest)" one of the left data one time.
 * exchange it with the element of the position that the "selected"
 * element should be
 */
public class SelectSorter<T> extends BasicSorter<T> {
    @Override
    public void sort(T[] data) {
        sort(data, null);
    }

    @Override
    public void sort(T[] data, Comparator<T> c) {
        for (int i = 0, length = data.length; i < length; i++) {
            int index = i;
            for (int j = i + 1; j < length; j++) {
                if (compare(data[index], data[j], c) > 0) {
                    index = j;
                }
            }
            if (index != i) {
                swap(data, i, index);
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
