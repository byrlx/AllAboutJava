package com.lxjlib.algorithm.leetcode;

/**
 * Created by douhua on 3/3/16.
 */
public class EasyProblems {
    /**
     * @param s
     * @see <a href="https://leetcode.com/problems/length-of-last-word/">https://leetcode.com/problems/length-of-last-word/</a>
     */
    public int title58(String s) {
        String[] ss = s.split(" +");
        for (int i = ss.length - 1; i >= 0; i--) {
            if (ss[i].contains(" ")) continue;

            return ss[i].length();
        }

        return 0;
    }

    /**
     * https://leetcode.com/problems/reverse-integer/
     *
     * @param x
     * @return
     */
    public int title7(int x) {
        int factor = 1;
        int y = 0;

        int xx = Math.abs(x);
        while (xx > 0) {
            if (y > (Integer.MAX_VALUE / 10)) return 0;
            int m = xx % 10;
            y = y * 10 + m;
            xx = xx / 10;
        }

        return y * (x > 0 ? 1 : -1);
    }


    /**
     * https://leetcode.com/problems/string-to-integer-atoi/
     * TODO !!!!
     *
     * @param
     * @return
     */
    public int atoi(String str) {
        int result = 0;
        boolean trigger = false;
        int factor = 0;
        int len = str.length();
        int max_bound = Integer.MAX_VALUE / 10;
        int min_bound = Integer.MIN_VALUE * 10;

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '-' || c == '+') {
                if (factor * c != 0) return 0;
                factor = (c == '-') ? -1 : 1;
                continue;
            }
            if (c <= '9' && c >= '0') {
                if (result >= max_bound) {
                    if (factor == -1)
                        return Integer.MIN_VALUE;
                    return Integer.MAX_VALUE;
                }
                result = result * 10 + c - '0';
                trigger = true;
            } else if (trigger) {
                break;
            }
        }

        if (factor == -1)
            result *= factor;
        return result;
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int totalRemoved = 0;
        int removed = 0;
        int len = nums.length;

        if (len < 2) return nums.length;

        for (int i = 1, last = nums[0]; i < len; i++) {
            if (nums[i] == last) {
                removed++;
                continue;
            }

            if (removed > 0) {
                System.arraycopy(nums, i, nums, i - removed, len - i);
                len = len - removed;
                totalRemoved += removed;
                i = i - removed;
                removed = 0;
            }
            last = nums[i];
        }

        int result = nums.length - totalRemoved - removed;
        return result;
    }

    /**
     * https://leetcode.com/problems/missing-number/
     * <p>
     * 该函数使用int数组来做bitmap来做算法
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {

        int len = nums.length;
        int[] bitmap = new int[len / 31 + 1];

        for (int i = 0; i < len; i++) {
            int n_pos = nums[i];
            int pos = bitmap[n_pos / 31];
            bitmap[n_pos / 31] = pos | (0x1 << (n_pos % 31));
        }

        for (int i = 0; i < bitmap.length; i++) {
            if (bitmap[i] != Integer.MAX_VALUE) {
                int catching = bitmap[i];
                for (int j = 0; j < 31; j++) {
                    if ((catching & 1) == 0) {
                        return (j + 31 * i);
                    } else {
                        catching = catching >> 1;
                    }
                }
            }

        }

        return 0;
    }



    public static void test() {
        EasyProblems easy = new EasyProblems();

//        Common.print(easy.title58(" helo  wold    "));
//        Common.print(easy.title7(123));
//        Common.print(easy.atoi("-2147483648"));
//        Common.print(easy.removeDuplicates(new int[]{1, 1, 2, 2}));
//        Common.print(easy.missingNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12}));

    }
}
