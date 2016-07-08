package com.lxjlib.algorithm.leetcode;

/**
 * Created by douhua on 5/26/16.
 */

/**
 * Definition for singly-linked list.
 */
public class Solution203 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode prev = null, cur = head;

        while (cur != null) {
            if (cur.val == val) {
                if (prev == null) {
                    head = cur.next;
                } else {
                    prev.next = cur.next;
                }
            } else {
                prev = cur;
            }
            cur = cur.next;
        }

        return head;
    }
}
