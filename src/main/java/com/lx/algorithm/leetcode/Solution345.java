package com.lx.algorithm.leetcode;


import com.lx.Log;

/**
 * Created by douhua on 5/26/16.
 */
public class Solution345 {
    public boolean isVowel(char m) {
        switch (m) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

    public String reverseVowels(String s) {
        char[] toC = s.toCharArray();

        int begin = 0;
        int end = s.length() - 1;
        int lenght = s.length();
        while (true) {
            while (begin < s.length() && !isVowel(toC[begin])) begin++;
            while (end >= 0 && !isVowel(toC[end])) end--;

            if (begin >= end) break;
            char temp = toC[begin];
            toC[begin] = toC[end];
            toC[end] = temp;

            begin++;
            end--;
        }

        return String.valueOf(toC);
    }

    public static void test() {
        Solution345 solution345 = new Solution345();
        Log.e(solution345.reverseVowels("leetcode"));
    }
}
