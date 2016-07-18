package com.lx.algorithm.leetcode;


import com.lx.Log;

/**
 * Created by douhua on 5/26/16.
 */
public class Solution27 {
    public int removeElement(int[] nums, int val) {
        int begin, end;

        if (nums == null || nums.length == 0) return 0;

        for (begin = 0, end = nums.length - 1; ; ) {
            while (begin < nums.length && nums[begin] != val) begin++;
            while (end >= 0 && nums[end] == val) end--;

            if (begin > end) break;
            nums[begin++] = nums[end--];
        }

        return end + 1;
    }

    public static void test(){
        Log.e((new Solution27().removeElement(new int[] {1}, 1)) + "");
    }
}
