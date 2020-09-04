package com.practice.algorithm.tree;

import java.util.Arrays;

/**
 * 二叉树的构建及遍历
 */
public class _BinaryTree {

    /**
     * 构建二叉树
     * 二叉树各节点的关系：
     * 设，当前节点的索引为 i，父节点为 (i - 1)/2，左子节点为 (2i + 1)，右子节点为 (2i + 2)
     *
     * @param tree 二叉树所有节点的值
     */
    private static Node buildTree(int[] tree) {
        if (tree == null || tree.length <= 0) {
            throw new RuntimeException("构建二叉树异常，各节点信息有误！");
        }
        Node root = new Node(tree[0]);
        buildNode(tree, 0, root);
        return root;
    }

    /**
     * 构建二叉树节点
     *
     * @param tree        各节点值
     * @param i           当前节点索引
     * @param currentNode 当前节点
     */
    private static void buildNode(int[] tree, int i, Node currentNode) {
        int length = tree.length;
        if (i >= length) {
            return;
        }
        int leftIndex = 2 * i + 1;
        int rightIndex = 2 * i + 2;
        if (leftIndex < length) {
            currentNode.left = new Node(tree[leftIndex]);
        }
        if (rightIndex < length) {
            currentNode.right = new Node(tree[rightIndex]);
        }
        // 构建左子树
        buildNode(tree, leftIndex, currentNode.left);
        // 构建右子树
        buildNode(tree, rightIndex, currentNode.right);
    }

    public static void main(String[] args) {
        int[] tree = {4, 2, 5, 6, 1, 6, 3, 5};
        Node root = buildTree(tree);
        System.out.println(Arrays.toString(tree));
        // 递归遍历
        recursionTraverseTree(root);
    }

    /**
     * 递归遍历
     */
    private static void recursionTraverseTree(Node root){
        traverseTree(root);
    }

    /**
     * 递归前序遍历
     */
    private static void traverseTree(Node root) {
        if (root != null){
            System.out.print(root.value + " ");
            traverseTree(root.left);
            traverseTree(root.right);
        }
    }

    /**
     * 二叉树的节点
     */
    private static class Node {
        private int value;
        private Node left;
        private Node right;


        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
