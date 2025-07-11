package com.lzc.leetcode.listnode;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.leetcode.test.args.Args;
import com.lzc.listnode.ListNode;

import static com.lzc.listnode.ListNodeUtils.buildArrayToListNodeList;

public class Q21 extends Solution {

    @HandleSolution
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
        return new Args(
                buildArrayToListNodeList(new int[]{2, 4, 3}),
                buildArrayToListNodeList(new int[]{5, 6, 4}))
                .getArgs();
    }
}
