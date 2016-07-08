package com.lxjlib.algorithm.leetcode;


import com.lxjlib.Log;

/**
 * Created by douhua on 5/26/16.
 */
public class Solution344 {
    public String reverseString(String s) {
        char[] result = new char[s.length()];

        int i = 0, j = s.length() - 1;

        while(i<j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            result[j] = a;
            result[i] = b;
            i++; j--;
        }

        if(i==j) {
            result[i] = s.charAt(i);
        }

        return String.valueOf(result);
    }

    public static void test(){
        Log.e((new Solution344()).reverseString("hello"));
    }
}
