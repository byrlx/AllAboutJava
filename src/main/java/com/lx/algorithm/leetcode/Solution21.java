package com.lx.algorithm.leetcode;

/**
 * Definition for singly-linked list.
 */

public class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head, cur;
        head = cur = null;

        while (l1 != null && l2 != null) {
            ListNode temp;
            if (l1.val <= l2.val) {
                temp = l1;
                l1 = l1.next;
            } else {
                temp = l2;
                l2 = l2.next;
            }

            if (head == null) {
                head = cur = temp;
            } else {
                cur.next = temp;
                cur = temp;
            }
        }

        if (l1 != null) {
            if (head == null) return l1;
            cur.next = l1;
        }
        if (l2 != null) {
            if (head == null) return l2;
            cur.next = l2;
        }

        return head;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}