package com.lx.algorithm.leetcode;

import java.util.Stack;

/**
 * Created by douhua on 5/27/16.
 */
public class Solution232 {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> temp = new Stack<>();

    // Push element x to the back of queue.
    public void push(int x) {
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }

        stack.push(x);

        while(!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        stack.pop();
    }

    // Get the front element.
    public int peek() {
        return stack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.isEmpty();
    }
}
