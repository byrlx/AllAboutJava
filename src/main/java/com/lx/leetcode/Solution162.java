package com.lx.leetcode;

/**
 * Created by douhua on 5/24/16.
 */
public class Solution162 {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        int i;
        for (i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }

        return i;
    }

}
