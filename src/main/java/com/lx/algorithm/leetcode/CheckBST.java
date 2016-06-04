package com.lx.algorithm.leetcode;

/**
 * 检查一个二叉树是不是二叉查找树
 */
public class CheckBST {
    class Node {
        int value;
        Node left;
        Node right;
    }

    public boolean check(Node root) {
        return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean check(Node node, int minValue, int maxValue) {
        if (node == null) return true;

        if (node.value < minValue || node.value > maxValue) {
            return false;
        }

        return check(node.left, minValue, node.value - 1)
                && check(node.right, node.value + 1, maxValue);

    }
}
