package com.practice.algorithm.tree;

import java.util.Arrays;

/**
 * @author XiaoSi
 * @className _BinarySearchTree
 * @description 二叉搜索树
 * @date 2020/11/13
 */
public class _BinarySearchTree {

    private static Node buildTree(int[] tree) {
        if (tree == null || tree.length <= 0) {
            throw new RuntimeException("构建二叉树异常，各节点信息有误！");
        }
        Node root = new Node(tree[0]);
        for (int i = 1; i < tree.length; i++) {
            buildNode(root, tree[i]);
        }
        return root;
    }

    private static void buildNode(Node node, int v){
        Node tranNode;
        do {
            if (node.value <= v) {
                // 当前值位于右子树
                tranNode = node;
                node = node.right;
                if (node == null){
                    tranNode.right = new Node(v);
                }
            } else {
                // 当前值位于左子树
                tranNode = node;
                node = node.left;
                if (node == null){
                    tranNode.left = new Node(v);
                }
            }
        } while (node != null);
    }

    public static void main(String[] args) {
        int[] tree = {4, 2, 5, 6, 1, 6, 3, 5};
        Node root = buildTree(tree);
        System.out.println(Arrays.toString(tree));
        Recursion recursion = new Recursion();
        recursion.recursionTraverseTree(root);
    }

    /**
     * 二叉树的节点
     */
    private static class Node {
        private final int value;
        private Node left;
        private Node right;


        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static class Recursion {

        public void recursionTraverseTree(Node root) {
            System.out.println("-------前序遍历-------");
            traversePreviousTree(root);
            System.out.println("\n-------中序遍历-------");
            traverseCenterTree(root);
            System.out.println("\n-------后序遍历-------");
            traverseAfterTree(root);
            System.out.println();
        }

        /**
         * 前序遍历
         */
        public void traversePreviousTree(Node root) {
            if (root != null) {
                System.out.print(root.value + " ");
                traversePreviousTree(root.left);
                traversePreviousTree(root.right);
            }
        }

        /**
         * 中序遍历
         */
        public void traverseCenterTree(Node root) {
            if (root != null) {
                traverseCenterTree(root.left);
                System.out.print(root.value + " ");
                traverseCenterTree(root.right);
            }
        }

        /**
         * 后序遍历
         */
        public void traverseAfterTree(Node root) {
            if (root != null) {
                traverseAfterTree(root.left);
                traverseAfterTree(root.right);
                System.out.print(root.value + " ");
            }
        }
    }
}
