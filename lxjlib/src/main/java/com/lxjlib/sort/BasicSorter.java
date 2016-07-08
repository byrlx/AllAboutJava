package com.lxjlib.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Created by lx on 6/15/16.
 */
public abstract class BasicSorter<T> implements Sorter<T> {
    /**
     * Exchagne two elements
     *
     * @param data
     * @param i
     * @param j
     */
    protected void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    protected void swap(List<T> data, int i, int j) {
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    int compare(T a, T b, Comparator<T> c) {
        if (c != null) {
            return c.compare(a, b);
        }

        if (!(a instanceof Comparable))
            throw (new IllegalArgumentException("arguments must be subclass of comparable"));

        return ((Comparable) a).compareTo(b);
    }
}
