package com.lx.algorithm.datastructure;

import com.lx.algorithm.datastructure.UnionFind;

/**
 * 该算法展示了一个性能很"差"的UF算法,
 */
public class BadUnionFind extends UnionFind {
    public BadUnionFind(int number) {
        this.number = number;
        ufs = new int[number];
        for (int i = 0; i < number; i++) {
            ufs[i] = 0;
        }
    }

    @Override
    int find(int n) {
        return ufs[n];
    }

    @Override
    boolean connect(int a, int b) {
        return ufs[a] == ufs[b];
    }

    /**
     * 将所有a的连通分量的节点连接改为b的连接
     *
     * 这里每次都要遍历一次数组, 所以性能差
     *
     * @param a
     * @param b
     */
    @Override
    void union(int a, int b) {
        if (connect(a, b)) return;

        for (int i = 0; i < ufs.length; i++) {
            if (ufs[i] == ufs[a]) {
                ufs[i] = ufs[b];
                number--;
            }
        }
    }

    @Override
    int count() {
        return number;
    }
}
