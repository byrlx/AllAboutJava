package com.lx.algorithm.leetcode;


import com.lx.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by douhua on 5/24/16.
 */
public class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new LinkedList<>();

        if(numRows == 0) return result;

        int sz = 0;

        List<Integer> last = new LinkedList<>();
        last.add(1);

        result.add(last);
        sz++;
        numRows--;

        while (numRows > 0) {
            List<Integer> cur = new LinkedList<>();
            last = result.get(sz - 1);

            int lastElement = 0;
            for (int m : last) {
                cur.add(lastElement + m);
                lastElement = m;
            }
            cur.add(1);

            result.add(cur);
            sz++;
            numRows--;
        }

        return result;
    }

    public static void test(){
        Solution118 solution118 = new Solution118();
        List<List<Integer>> l = solution118.generate(5);
        Log.e("break");
    }
}
