package com.lx.algorithm.leetcode;

/**
 * Created by douhua on 5/26/16.
 */
public class Solution1160 {
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA, lenB;
        ListNode temp;
        for (lenA = 0, temp = headA; temp != null; temp = temp.next) {
            lenA++;
        }
        for (lenB = 0, temp = headB; temp != null; temp = temp.next) {
            lenB++;
        }

        if (lenA > lenB) {
            int dis = lenA - lenB;
            while (dis-- > 0) headA = headA.next;
        }
        if (lenA < lenB) {
            int dis = lenB - lenA;
            while (dis-- > 0) headB = headB.next;
        }

        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }
}
