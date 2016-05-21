package com.lx.algorithm.datastructure;

/**
 * Created by douhua on 5/20/16.
 * <p>
 * 单链表, 只能从头到为正向遍历
 * !!!非线程安全!!!
 */
public class LinkedList<T> {
    private Node head;
    private Node tail;

    public LinkedList() {
    }

    public T tail(){
        return tail.data;
    }

    public LinkedList(T[] dataSet) {
        Node cur = head;
        for (T data : dataSet) {
            Node node = new Node(data);

            if (head == null) {
                cur = head = node;
                continue;
            }

            cur.next = node;
            cur = node;
        }
        tail = cur;
    }

    public void append(T data) {
        Node node = new Node(data);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    public boolean contains(T data) {
        Node cur = head;
        while (cur != null) {
            if (cur.data.equals(data)) {
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    //只删除第一个找到的
    public void delete(T data) {
        Node prev = null;
        Node cur = head;

        while (cur != null) {
            if (cur.data.equals(data)) { //find it
                if (prev == null) {//head
                    head = cur.next;
                } else {
                    prev.next = cur.next;
                }
                break;
            }

            prev = cur;
            cur = cur.next;
        }
        if(prev.next == null) {
            tail = prev;
        }
    }

    private class Node {
        Node(T data) {
            this.data = data;
        }


        T data;
        Node next;
    }
}
