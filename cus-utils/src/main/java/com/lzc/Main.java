package com.lzc;

import com.lzc.listnode.ListNode;
import com.lzc.tree.binarytree.TreeNode;
import org.lzc.utils.BoxUtils;

import static com.lzc.listnode.ListNodeUtils.buildArrayToListNodeList;
import static com.lzc.tree.TreeUtils.buildTree;
import static com.lzc.tree.TreeUtils.printTree;

public class Main {

    public static void main(String[] args) {
        ListNode listNode = buildArrayToListNodeList(new int[]{1, 2, 3});
        System.out.println(listNode);

        TreeNode<Integer> root = buildTree(BoxUtils.boxedIntArrWithNull(new String[]{"1", "2", "3", null, "4", "5", "6", "7"}));
        printTree(root);
    }

}
