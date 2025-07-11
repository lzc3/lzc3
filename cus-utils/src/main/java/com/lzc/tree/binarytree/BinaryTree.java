package com.lzc.tree.binarytree;

public class BinaryTree<T> {
    TreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    // 插入节点（非平衡，仅示例结构）
    public void insert(T val) {
        root = insertRecursive(root, val);
    }

    private TreeNode<T> insertRecursive(TreeNode<T> current, T val) {
        if (current == null) {
            return new TreeNode<T>(val);
        }

        // 简单交替插入左右子树（仅演示二叉树结构，非BST规则）
        if (Math.random() > 0.5) {
            current.left = insertRecursive(current.left, val);
        } else {
            current.right = insertRecursive(current.right, val);
        }
        return current;
    }

    // 前序遍历
    public void preOrderTraversal() {
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    // 中序遍历
    public void inOrderTraversal() {
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(TreeNode<T> node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.print(node.value + " ");
            inOrderRecursive(node.right);
        }
    }

    // 后序遍历
    public void postOrderTraversal() {
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(TreeNode<T> node) {
        if (node != null) {
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }
}
