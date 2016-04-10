package com.lx.algorithm.uninfind;

/**
 *  这个算法描述了"连通分量"的问题:
 *  给定两个"节点", 如果可以从节点A到达节点B, 那么表示A,B属于同一个连通分量.
 */

/**
 * 为了展示算法的改进, 这里使用数字表示节点, 使用数组存续该节点的"连接",
 * 初始状态, 每个数字的连接为其本身. 故有N个"连通分量".
 */

/**
 * 本接口定义了该算法的API
 */
abstract class UnionFind {
    /**
     * 总的节点数, 所有的节点值必须 <= number
     */
    int number;

    /**
     * 保存节点的连通分量
     */
    int[] ufs;

    /**
     * 返回节点 n 的连接
     * @param n
     * @return
     */
    abstract int find(int n);

    /**
     * 判断节点 a, b 是否属于同一 "连通分量"
     * @param a
     * @param b
     * @return
     */
    abstract boolean connect(int a, int b);

    /**
     * 合并节点a,b所属的连通分量
     * @param a
     * @param b
     */
    abstract void union(int a, int b);

    /**
     * 返回连通分量的数量
     * @return
     */
    abstract int count();
}
