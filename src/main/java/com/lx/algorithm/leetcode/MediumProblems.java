package com.lx.algorithm.leetcode;

/**
 * Created by douhua on 3/3/16.
 */
public class MediumProblems {
    /**
     * https://leetcode.com/problems/search-for-a-range/
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    /**
     * 二分查找法
     *
     * @param nums
     * @param start: 数组开始下标
     * @param end:   数组结束下标
     * @param target
     * @return
     */
    private int[] binarySearch(int[] nums, int start, int end, int target) {
        if (start > end) return new int[]{-1, -1};

        int mid = (start + end) / 2;
        int midNum = nums[mid];

        if (midNum == target) {
            int startPos;
            int endPos;
            startPos = endPos = mid;

            for (; startPos > start; startPos--) {
                if (nums[startPos - 1] != target) break;
            }

            for (; endPos < end; endPos++) {
                if (nums[endPos + 1] != target) break;
            }

            return new int[]{startPos, endPos};
        } else if (midNum > target) {
            return binarySearch(nums, 0, mid - 1, target);
        } else {
            return binarySearch(nums, mid + 1, end, target);
        }
    }


    public static void test() {
        MediumProblems medium = new MediumProblems();

//        medium.searchRange()
    }
}
