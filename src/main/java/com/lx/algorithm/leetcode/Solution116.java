package com.lx.algorithm.leetcode;

/**
 * Created by douhua on 5/24/16.
 */
public class Solution116 {
    /**
     * Definition for binary tree with next pointer.
     **/
    class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public void connect(TreeLinkNode root) {
        _connect(root);
    }

    private void _connect(TreeLinkNode node) {
        if (node == null || node.left == null) return;

        node.left.next = node.right;
        if (node.next != null) {
            node.right.next = node.next.left;
        }

        _connect(node.left);
        _connect(node.right);
    }
}
