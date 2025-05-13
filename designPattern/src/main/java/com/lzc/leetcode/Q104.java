package com.lzc.leetcode;

public class Q104 {

    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return iterTree(root, 0);
    }

    public int iterTree(TreeNode treeNode, int deep) {
        if (treeNode == null) {
            return deep;
        }
        deep++;
        TreeNode left = treeNode.left;
        TreeNode right = treeNode.right;

        return Math.max(iterTree(left, deep), iterTree(right, deep));

    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}