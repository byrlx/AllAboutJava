package com.lx.algorithm.leetcode;

import java.util.Arrays;

/**
 * Created by douhua on 5/26/16.
 */
public class Solution283 {
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; ) {
            if (nums[i] != 0) {
                i++;
                continue;
            }
            System.arraycopy(nums, i + 1, nums, i, len - i - 1);
            len--;
        }

        for (; len < nums.length; len++) {
            nums[len] = 0;
        }

    }
}
