package com.lxjlib.algorithm.leetcode;


import com.lxjlib.Log;

/**
 * Created by douhua on 5/27/16.
 */
public class Solution112 {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

//        return hasPathSum(root, 0, sum);
        return _hasPathSum(root, sum);
    }

    public boolean _hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            return sum == root.val;
        }

        return _hasPathSum(root.left, sum - root.val) || _hasPathSum(root.right, sum - root.val);
    }


    boolean hasPathSum(TreeNode root, int sum, int target) {
        if (root == null) return false;
        sum += root.val;
        if (root.left == null && root.right == null) //leaf
        {
            if (sum == target)
                return true;
            else
                return false;
        }
        return hasPathSum(root.left, sum, target)
                || hasPathSum(root.right, sum, target);
    }

    public void test() {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        a.left = b;

        Log.e(hasPathSum(a, 1) + "");
    }
}
