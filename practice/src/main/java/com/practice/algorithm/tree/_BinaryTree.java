package com.practice.algorithm.tree;

import java.util.Arrays;
import java.util.Stack;

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
        Recursion recursion = new Recursion();
        recursion.recursionTraverseTree(root);
        // 循环遍历
        Traverse traverse = new Traverse();
        traverse.traverseTree(root);
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

    /**
     * 递归遍历
     */
    public static class Recursion {

        public void recursionTraverseTree(Node root) {
            System.out.println("-------递归遍历-------");
            traversePreviousTree(root);
            System.out.println();
            traverseCenterTree(root);
            System.out.println();
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

    /**
     * 循环遍历
     */
    public static class Traverse {

        public void traverseTree(Node root) {
            System.out.println("-------循环遍历-------");
            this.traversePreviousTree(root);
            System.out.println();
            this.traverseCenterTree(root);
            System.out.println();
            this.traverseAfterTree(root);
            System.out.println();
        }

        /**
         * 前序遍历
         * 对于每一个子树，都遵循先遍历根节点，然后左节点，最后右节点
         * 借助栈的先入后出特性
         */
        public void traversePreviousTree(Node root) {
            Stack<Node> stack = new Stack<>();
            Node treeNode = root;
            while (treeNode != null || !stack.isEmpty()) {
                //迭代访问节点的左孩子，并入栈
                while (treeNode != null) {
                    System.out.print(treeNode.value + " ");
                    stack.push(treeNode);
                    treeNode = treeNode.left;
                }
                //如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
                if (!stack.isEmpty()) {
                    treeNode = stack.pop();
                    treeNode = treeNode.right;
                }
            }
        }

        /**
         * 中序遍历
         * 对于每一个子树，都遵循先遍历左节点，然后根节点，最后右节点
         */
        public void traverseCenterTree(Node root) {
            Stack<Node> stack = new Stack<>();
            Node treeNode = root;
            while (treeNode != null || !stack.isEmpty()) {
                //迭代访问节点的左孩子，并入栈
                while (treeNode != null) {
                    stack.push(treeNode);
                    treeNode = treeNode.left;
                }
                //如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
                if (!stack.isEmpty()) {
                    treeNode = stack.pop();
                    // 访问右节点前先输出根节点值
                    System.out.print(treeNode.value + " ");
                    treeNode = treeNode.right;
                }
            }
        }

        /**
         * 后序遍历
         * 对于每一个子树，都遵循先遍历左节点，然后右节点，最后根节点
         */
        public void traverseAfterTree(Node root) {
            Stack<Node> stack = new Stack<>();
            Node treeNode = root;
            Node lastVisit = null;   //标记每次遍历最后一次访问的节点
            while (treeNode != null || !stack.isEmpty()) {//节点不为空，结点入栈，并且指向下一个左孩子
                while (treeNode != null) {
                    stack.push(treeNode);
                    treeNode = treeNode.left;
                }
                //栈不为空
                if (!stack.isEmpty()) {
                    //出栈
                    treeNode = stack.pop();
                    /*
                     * 这块就是判断treeNode是否有右孩子，
                     * 如果没有输出treeNode.value，让lastVisit指向treeNode，并让treeNode为空
                     * 如果有右孩子，将当前节点继续入栈，treeNode指向它的右孩子,继续重复循环
                     */
                    if (treeNode.right == null || treeNode.right == lastVisit) {
                        System.out.print(treeNode.value + " ");
                        lastVisit = treeNode;
                        treeNode = null;
                    } else {
                        stack.push(treeNode);
                        treeNode = treeNode.right;
                    }
                }
            }
        }
    }
}
