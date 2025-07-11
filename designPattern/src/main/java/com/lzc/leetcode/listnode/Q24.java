package com.lzc.leetcode.listnode;

import com.lzc.leetcode.test.Solution;
import com.lzc.listnode.ListNode;

public class Q24 extends Solution {

    public ListNode swapPairs(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode headCopy = head.next == null ? head : head.next;
        ListNode pre = head;
        boolean changeFlag = true;
        while (head != null) {
            if (changeFlag) {
                ListNode next = head.next;
                ListNode nextNext = next == null ? null : next.next;

                if (next != null) {
                    pre.next = next;
                    next.next = head;
                }
                head.next = nextNext;
            } else {
                pre = head;
                head = head.next;
            }
            changeFlag = !changeFlag;
        }

        return headCopy;
    }

    @Override
    protected Object[] offerArgs() {
        return new Object[0];
    }
}
