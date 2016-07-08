package com.lxjlib.algorithm.leetcode;

/**
 * Created by douhua on 5/24/16.
 */

import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * <p>
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 **/

/**
 * 解题思路:
 * 1. 利用递归, 先遍历最右子树, 右子树到头再返回上一层遍历左子树.
 * 2. 设置一个全局变量max记录当前记录的树"深度", 在返回上一层递归遍历时, 小于深度的节点不可见.
 */
public class Solution199 {
    private int curHeight = 0;
    private List<Integer> result;

    public List<Integer> rightSideView(TreeNode root) {
        result = new LinkedList<>();

        traverseNode(root, 1);

        return result;
    }

    /**
     * @param node 遍历该节点
     * @param n    该节点的深度
     */
    private void traverseNode(TreeNode node, int n) {
        if (node == null) return; //空节点

        if (curHeight < n) {
            result.add(node.val);
            curHeight = n;
        }

        traverseNode(node.right, n + 1);
        traverseNode(node.left, n + 1);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
