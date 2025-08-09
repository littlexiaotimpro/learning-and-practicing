package com.practice.kotlin

import java.util.*

/**
 * 二叉树的构建及遍历
 */
object BinaryTree {
    /**
     * 构建二叉树
     * 二叉树各节点的关系：
     * 设，当前节点的索引为 i，父节点为 (i - 1)/2，左子节点为 (2i + 1)，右子节点为 (2i + 2)
     *
     * @param tree 二叉树所有节点的值
     */
    private fun buildTree(tree: IntArray): Node {
        if (tree.isEmpty()) {
            throw RuntimeException("构建二叉树异常，各节点信息有误！")
        }
        val root = Node(tree[0])
        buildNode(tree, 0, root)
        return root
    }

    /**
     * 构建二叉树节点
     *
     * @param tree        各节点值
     * @param i           当前节点索引
     * @param currentNode 当前节点
     */
    private fun buildNode(tree: IntArray, i: Int, currentNode: Node ?= null) {
        val length = tree.size
        if (i >= length) {
            return
        }
        val leftIndex = 2 * i + 1
        val rightIndex = 2 * i + 2
        if (leftIndex < length) {
            currentNode?.left = Node(tree[leftIndex])
        }
        if (rightIndex < length) {
            currentNode?.right = Node(tree[rightIndex])
        }
        // 构建左子树
        buildNode(tree, leftIndex, currentNode?.left)
        // 构建右子树
        buildNode(tree, rightIndex, currentNode?.right)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val tree = intArrayOf(4, 2, 5, 6, 1, 6, 3, 5)
        val root = buildTree(tree)
        println(tree.contentToString())
        // 递归遍历
        val recursion = Recursion()
        recursion.recursionTraverseTree(root)
        // 循环遍历
        val traverse = Traverse()
        traverse.traverseTree(root)
    }

    /**
     * 二叉树的节点
     */
    class Node(val value: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    /**
     * 递归遍历
     */
    class Recursion {
        fun recursionTraverseTree(root: Node?) {
            println("-------递归遍历-------")
            traversePreviousTree(root)
            println()
            traverseCenterTree(root)
            println()
            traverseAfterTree(root)
            println()
        }

        /**
         * 前序遍历
         */
        private fun traversePreviousTree(root: Node?) {
            if (root != null) {
                print(root.value.toString() + " ")
                traversePreviousTree(root.left)
                traversePreviousTree(root.right)
            }
        }

        /**
         * 中序遍历
         */
        private fun traverseCenterTree(root: Node?) {
            if (root != null) {
                traverseCenterTree(root.left)
                print(root.value.toString() + " ")
                traverseCenterTree(root.right)
            }
        }

        /**
         * 后序遍历
         */
        private fun traverseAfterTree(root: Node?) {
            if (root != null) {
                traverseAfterTree(root.left)
                traverseAfterTree(root.right)
                print(root.value.toString() + " ")
            }
        }
    }

    /**
     * 循环遍历
     */
    class Traverse {
        fun traverseTree(root: Node?) {
            println("-------循环遍历-------")
            this.traversePreviousTree(root)
            println()
            this.traverseCenterTree(root)
            println()
            this.traverseAfterTree(root)
            println()
        }

        /**
         * 前序遍历
         * 对于每一个子树，都遵循先遍历根节点，然后左节点，最后右节点
         * 借助栈的先入后出特性
         */
        private fun traversePreviousTree(root: Node?) {
            val stack = Stack<Node>()
            var treeNode = root
            while (treeNode != null || !stack.isEmpty()) {
                //迭代访问节点的左孩子，并入栈
                while (treeNode != null) {
                    print(treeNode.value.toString() + " ")
                    stack.push(treeNode)
                    treeNode = treeNode.left
                }
                //如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
                if (!stack.isEmpty()) {
                    treeNode = stack.pop()
                    treeNode = treeNode.right
                }
            }
        }

        /**
         * 中序遍历
         * 对于每一个子树，都遵循先遍历左节点，然后根节点，最后右节点
         */
        private fun traverseCenterTree(root: Node?) {
            val stack = Stack<Node>()
            var treeNode = root
            while (treeNode != null || !stack.isEmpty()) {
                //迭代访问节点的左孩子，并入栈
                while (treeNode != null) {
                    stack.push(treeNode)
                    treeNode = treeNode.left
                }
                //如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
                if (!stack.isEmpty()) {
                    treeNode = stack.pop()
                    // 访问右节点前先输出根节点值
                    print(treeNode.value.toString() + " ")
                    treeNode = treeNode.right
                }
            }
        }

        /**
         * 后序遍历
         * 对于每一个子树，都遵循先遍历左节点，然后右节点，最后根节点
         */
        private fun traverseAfterTree(root: Node?) {
            val stack = Stack<Node?>()
            var treeNode = root
            var lastVisit: Node? = null //标记每次遍历最后一次访问的节点
            while (treeNode != null || !stack.isEmpty()) { //节点不为空，结点入栈，并且指向下一个左孩子
                while (treeNode != null) {
                    stack.push(treeNode)
                    treeNode = treeNode.left
                }
                //栈不为空
                if (!stack.isEmpty()) {
                    //出栈
                    treeNode = stack.pop()
                    /*
                     * 这块就是判断treeNode是否有右孩子，
                     * 如果没有输出treeNode.value，让lastVisit指向treeNode，并让treeNode为空
                     * 如果有右孩子，将当前节点继续入栈，treeNode指向它的右孩子,继续重复循环
                     */
                    if (treeNode!!.right == null || treeNode.right === lastVisit) {
                        print(treeNode.value.toString() + " ")
                        lastVisit = treeNode
                        treeNode = null
                    } else {
                        stack.push(treeNode)
                        treeNode = treeNode.right
                    }
                }
            }
        }
    }
}
