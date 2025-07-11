package com.lzc.tree.binarytree;

import lombok.Data;

@Data
public class TreeNode<T> {
    T value;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T t) {
        value = t;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }
}
