package com.lxjlib.algorithm.sort;

/**
 * Created by douhua on 5/20/16.
 * <p>
 * 排序的基类, 一般排序都有"比较"和"交互"几个基本操作,
 * 该基类提供了这两个操作
 * <p>
 * BaseSort 操作的数据都要实现Comparable类
 */
public class BaseSort {
    Comparable[] data;

    public BaseSort(Comparable[] data) {
        this.data = data;
    }

    /**
     * 比较两个元素大小
     *
     * @param a
     * @param b
     * @return
     */
    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }


    /**
     * 交换两个元素
     */
    public void exchange(int i, int j) {
        Comparable tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
