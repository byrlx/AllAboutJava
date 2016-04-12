package com.lx.algorithm.trees;

import com.lx.util.Log;

/**
 * 红黑树(Red-Black Tree)
 * <p>
 * 红黑树的四条规则:
 * 1. 所有节点不是红色就是黑色
 * 2. 根节点是黑色
 * 3. 若节点为红色, 其子节点必须是黑色
 * 4. 左右子树的"黑高"相同, 黑高是指从该节点到所有叶子节点中间经历的黑色节点数量
 * <p>
 * 本里中构造的红黑树用于排序, 节点的左子树的节点其值都比该节点小,
 * 右子树的节点其值都比该节点大
 */
public class RbTree {
    private static final int[] DATA = {19, 7, 30, 18, 11, 22, 3, 25, 26, 38, 20};
    private static final int BLACK = 0;
    private static final int RED = 1;

    private Node root;

    /**
     * 基于数组构造红黑树
     */
    public void build(int[] data) {
        for (int i = 0, len = data.length; i < len; i++) {
            Node newNode = new Node(data[i]);
            insert(newNode);
        }
    }

    /**
     * 将新节点插入到root代表的红黑树,
     * 新节点的颜色会先被设置为红色, 然后基于排序规则插入到红黑树,
     * 如果插入后破坏了红黑树的任意规则, 则需要对红黑树进行重置
     */
    public void insert(Node newNode) {
        //第一个节点设为root
        if (root == null) {
            root = newNode;
            root.color = BLACK;
            return;
        }

        //先将节点根据排序插入到指定的位置
        Node cur = root;

        //根据数据的大小先将新节点插入到"应该插入"的位置,
        //注: 这有可能破坏红黑树的规则
        while (cur != null) {
            if (newNode.data < cur.data) {
                if (cur.left == null) {
                    cur.left = newNode;
                    newNode.parent = cur;
                    break;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = newNode;
                    newNode.parent = cur;
                    break;
                }
                cur = cur.right;
            }
        }

        resetRbTree(newNode);
    }

    /**
     * 如果一个节点的左右节点都为红色, 该使用该方法修改这个子树的
     * 结构, 将两个子节点都改为黑色, 并且将该节点改为红色.
     *
     * @param node
     */
    private void flipColor(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    /**
     * 重置红黑树, 因为新插入的节点可能会破坏红黑树的规则,
     * 所以每次插入一个节点后都要看是否需要重置红黑树
     */
    private void resetRbTree(Node newNode) {
        Node cur = newNode;

        //提示, 这里父节点永远是其父节点的左节点
        while (cur != root && cur.color == RED) {
            Node p = cur.parent;

            //没有破坏红黑树, 直接返回
            if (p.color == BLACK && cur == p.left) {
                break;
            }

            //父节点是黑点, 且新节点是父节点的右节点
            else if (p.color == BLACK && cur == p.right) {
                //如果父节点的左节点也为红色, 则说明父节点的左右节点都为红色
                if (p.left != null && p.left.color == RED) {
                    flipColor(p);
                    cur = p;
                    continue;
                } else {
                    rotateLeft(p);
                    break;
                }
            }

            //父节点是红色,
            else if (p.color == RED) {
                Node pp = p.parent;

                //新节点是父节点的左节点, 先右旋转, 再flip
                if (cur == p.left) {
                    cur = rotateRight(pp);   //祖父节点右旋
                    flipColor(cur);
                } else {
                    //新节点是父节点的右节点, 先左旋转, 再右旋转, 再flip
                    rotateLeft(p);
                    cur = rotateRight(pp);
                    flipColor(cur);
                }
            }
        }

        if (cur == root && cur.color == RED) {
            cur.color = BLACK;
        }
    }

    /**
     * 左旋转, 指将该节点的右节点放到该节点的当前位置,
     * 然后该节点作为其原来右节点的左节点.
     * <p>
     * 左旋转一般发生在该节点的右节点为颜色为红色, 并且其左节点颜色为黑色.
     * 旋转后需要将其和右节点的新链接(旋转后的)变为红色
     */

    private Node rotateLeft(Node node) {
        Node right = node.right;
        if (root == node) {
            root = right;
        }

        node.right = right.left;
        if(node.right != null) {
            node.right.parent = node.parent;
        }

        right.left = node;
        right.parent = node.parent;
        node.parent = right;

        //更改parent
        if (right.parent != null) {
            if (right.parent.left == node) {
                right.parent.left = right;
            } else {
                right.parent.right = right;
            }
        }

        right.color = node.color;
        node.color = RED;

        return right;
    }

    /**
     * 右旋转, 该节点的左节点放到该节点的当前位置,
     * 然后该节点作为其左节点的右节点
     * <p>
     * 右旋转一般发生在该节点的左节点和左节点的左节点都为红色的情况,
     * (因为插入的新节点都是标为红色, 所以当新插入的节点被插入到一颗
     * 红色节点的左节点时, 需要右旋转)
     * <p>
     * 如果新节点的插入位置是红节点的右节点, 那么需要先进行一次右旋转,
     * 再进行一次左旋转
     *
     * @param node
     * @return
     */
    private Node rotateRight(Node node) {
        Node left = node.left;
        if (root == node) {
            root = left;
        }

        node.left = left.right;
        if(node.left != null) {
            node.left.parent = node.parent;
        }

        left.right = node;
        left.parent = node.parent;
        node.parent = left;

        if (left.parent != null) {
            if (left.parent.left == node) {
                left.parent.left = left;
            } else {
                left.parent.right = left;
            }
        }

        left.color = node.color;
        node.color = RED;

        return left;
    }

    /**
     * 红黑树的节点
     */
    private class Node {
        int data;           //存放的数据
        int color;          //该节点颜色(也可以理解为其父节点到该节点的链接的颜色)
        int blackHeight;    //该节点的黑高度

        Node left;    //左子树
        Node right;   //右子树
        Node parent;  //指向父节点

        Node(int data) {
            this.data = data;
            color = RED;
            blackHeight = 0;
        }
    }

    public static void test() {
        RbTree rbTree = new RbTree();
        rbTree.build(DATA);

        DFS(rbTree.root);
    }

    /**
     * 中序遍历一棵树
     *
     * @param root
     */
    public static void DFS(Node root) {
        if (root == null) return;

        Node left = root.left;
        Node right = root.right;

        Log.e(root.data + "; color:" + (root.color == RED ? "red" : "black") +
                "; left:" + (left != null ? left.data : "null") +
                "; right:" + (right != null ? right.data : "null"));

        DFS(root.left);
        DFS(root.right);
    }
}
