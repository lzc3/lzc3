package com.lzc.leetcode.listnode;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.leetcode.test.args.Args;


import static com.lzc.leetcode.listnode.ListNodeUtils.buildArrayToListNodeList;

/**
 *
 */
public class Q2 extends Solution {

    @HandleSolution
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumListNode = new ListNode();
        ListNode tail = sumListNode;
        int up = 0;
        while (l1 != null || l2 != null) {
            int num1 = 0, num2 = 0;
            if (l1 != null) {
                num1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                num2 = l2.val;
                l2 = l2.next;
            }
            int sum = num1 + num2 + up;
            ListNode listNode = new ListNode(sum % 10);
            up = sum >= 10 ? 1 : 0;
            tail.next = listNode;
            tail = listNode;
        }

        if (up == 1) {
            ListNode listNode = new ListNode(1);
            tail.next = listNode;
        }

        return sumListNode.next;
    }

    @Override
    protected Object[] offerArgs() {
        return new Args(
                buildArrayToListNodeList(new int[]{2, 4, 3}),
                buildArrayToListNodeList(new int[]{5, 6, 4}))
                .getArgs();
    }
}
