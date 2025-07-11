package com.lzc.leetcode.listnode;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.listnode.ListNode;

import static com.lzc.listnode.ListNodeUtils.buildArrayToListNodeList;

public class Q23 extends Solution {

    @HandleSolution
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode listNode = lists[0];
        for (int i = 1; i < lists.length; i++) {
            listNode = mergeTwoLists(lists[i], listNode);
        }

        return listNode;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode listNode = new ListNode();
        ListNode tail = listNode;
        while (list1 != null && list2 != null) {
            ListNode tempNode;
            if (list1.val < list2.val) {
                tempNode = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                tempNode = new ListNode(list2.val);
                list2 = list2.next;
            }
            tail.next = tempNode;
            tail = tempNode;
        }

        if (list1 != null) {
            tail.next = list1;
            tail = null;
        }
        if (list2 != null) {
            tail.next = list2;
            tail = null;
        }
        return listNode.next;
    }
    @Override
    protected Object[] offerArgs() {
        // todo 这个参数有点古怪
        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = buildArrayToListNodeList(new int[]{1, 4, 5});
        listNodes[1] = buildArrayToListNodeList(new int[]{1, 3, 4});
        listNodes[2] = buildArrayToListNodeList(new int[]{2, 5, 6});

        Object[] objects = new Object[1];
        objects[0] = listNodes;
        return objects;
    }
}
