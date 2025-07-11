package com.lzc.leetcode.listnode;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.leetcode.test.args.Args;
import com.lzc.listnode.ListNode;

import static com.lzc.listnode.ListNodeUtils.buildArrayToListNodeList;

public class Q160 extends Solution {


    @HandleSolution
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
            if (l1 != null) {
                System.out.print(l1.val + "-");
            }
            if (l2 != null) {
                System.out.println(l2.val);
            }
        }
        return l1;
    }

    @Override
    protected Object[] offerArgs() {
        return new Args(
                buildArrayToListNodeList(new int[]{1, 2, 3}),
                buildArrayToListNodeList(new int[]{4, 5, 6, 7}))
                .getArgs();
    }

}
