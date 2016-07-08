package com.lxjlib.algorithm.datastructure;


import com.lxjlib.Log;

/**
 * 双向链表
 */
public class DoubleLinkedList<T> {
    private Node head; //链表头

    public DoubleLinkedList() {
    }

    public DoubleLinkedList(T[] dataSet) {
        for (T data : dataSet) {
            Node node = new Node(data);
            if (head == null) {
                head = node;
            } else {
                head.prev.push(node);
            }
        }
    }

    //获取head
    public Node head() {
        return head;
    }

    //插入新节点
    public void push(T data) {
        Node node = new Node(data);
        head.prev.push(node);
    }

    //删除链表里的所有包含data的节点
    public void delete(T data) {
        if (head == null) return;

        Node last = head.prev;
        Node cur = head;


        while (true) {
            if (cur.next == cur) {//head
                if (cur.data.equals(data)) {
                    cur.pop();
                    head = null;
                }
                break;
            }

            if (cur.data.equals(data)) {
                Node temp = cur;
                if(head == cur) {
                    head = cur.next;
                }
                cur = cur.next;
                temp.pop();
                if (temp == last) {
                    break;
                }
            } else {
                cur = cur.next;
                if(cur == head) break;
            }
        }
    }

    public boolean contains(T data) {
        Node cur = head;
        do {
            if (cur.data.equals(data)) return true;
            cur = cur.next;
        } while (cur != head);

        return false;
    }

    //返回包含的data的所有节点数量
    public int containsAll(T data) {
        int n = 0;
        Node cur = head;
        do {
            if (cur.data.equals(data)) n++;
            cur = cur.next;
        } while (cur != head);

        return n;
    }

    //正向遍历
    public void traverse() {
        Node cur = head;

        do {
            Log.e(cur.data.toString());
            cur = cur.next;
        } while (cur != head);
    }

    //反向遍历
    public void backwardTraverse() {
        Node cur = head.prev;
        do {

            Log.e(cur.data.toString());
            cur = cur.prev;
        } while (cur != head.prev);
    }

    public class Node {
        Node prev, next;
        T data;


        Node(T data) {
            this.data = data;
            prev = next = this;
        }

        public T data() {
            return data;
        }

        //在当前节点后面插入新节点, 并返回新插入的节点
        Node push(Node node) {
            node.next = next;
            node.prev = this;

            next.prev = node;
            next = node;

            return node;
        }

        //将自己从链表中删除, 并返回下一个节点
        Node pop() {
            Node result = next != this ? next : null;
            prev.next = next;
            next.prev = prev;

            prev = next = null;

            return result;
        }
    }
}
