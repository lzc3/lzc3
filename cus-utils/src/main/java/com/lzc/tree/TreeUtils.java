package com.lzc.tree;

import com.lzc.tree.binarytree.TreeNode;

import java.util.*;

public class TreeUtils {

    public static TreeNode<Integer> buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;
        TreeNode<Integer> root = new TreeNode<>(arr[0]);
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode<Integer> current = queue.poll();
            if (current == null) continue;
            // 左子节点
            if (arr[i] != null) {
                current.left = new TreeNode<>(arr[i]);
                queue.offer(current.left);
            }
            i++;
            // 右子节点
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode<>(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    public static <T> void printTree(TreeNode<T> root) {
        printTree(root, "", true);
    }

    private static <T> void printTree(TreeNode<T> node, String prefix, boolean isTail) {
        if (node == null) return;
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.value);
        if (node.left != null || node.right != null) {
            if (node.left != null && node.right != null) {
                printTree(node.left, prefix + (isTail ? "    " : "│   "), false);
                printTree(node.right, prefix + (isTail ? "    " : "│   "), true);
            } else if (node.left != null) {
                printTree(node.left, prefix + (isTail ? "    " : "│   "), true);
            } else {
                printTree(node.right, prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }
}
