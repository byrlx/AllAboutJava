package com.lx.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Created by douhua on 6/15/16.
 */
public interface Sorter<T> {
    /**
     * Sort data represented by an array
     * T must be sublcass of {@link Comparable}
     *
     * @param data
     */
     void sort(T[] data);

    /**
     * T does not need to be sublcass of {@link Comparable}
     *
     * @param data
     * @param c
     */
    void sort(T[] data, Comparator<T> c);

    /**
     * Sort data represented by list
     * T must be sublcass of {@link Comparable}
     *
     * @param data
     */
    void sort(List<T> data);

    /**
     * T does not need to be sublcass of {@link Comparable}
     *
     * @param data
     * @param c
     */
    void sort(List<T> data, Comparator<T> c);
}
