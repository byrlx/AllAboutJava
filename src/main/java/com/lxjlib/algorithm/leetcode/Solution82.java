package com.lxjlib.algorithm.leetcode;

/**
 * Created by douhua on 5/26/16.
 */
public class Solution82 {
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        for (ListNode prev = null, cur = head; cur != null && cur.next != null; ) {
            if (cur.val != cur.next.val) {
                prev = cur;
                cur = cur.next;
                continue;
            }

            ListNode temp = cur.next.next;
            while (temp != null && cur.val == temp.val) {
                temp = temp.next;
            }
            if (prev == null) {
                cur = head = temp;
            } else {
                prev.next = temp;
                cur = temp;
            }
        }

        return head;
    }
}
