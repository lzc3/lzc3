package com.lzc.leetcode.listnode;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.leetcode.test.args.Args;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static com.lzc.leetcode.listnode.ListNodeUtils.buildArrayToListNodeList;

/**
 * 删除链表的倒数第N个节点
 */
public class Q19 extends Solution {

    @HandleSolution
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        for (ListNode temp = head; temp != null; temp = temp.next) {
            length++;
        }
        if (length == 1) {
            return null;
        }

        int deleteIndex = length - n + 1;
        int curIndex = 1;
        for (ListNode temp = head, pre = null; temp != null; pre = temp, temp = temp.next) {
            if (curIndex == deleteIndex) {
                if (pre == null) { // 此处注意，第一次写的时候遗漏了
                    head = head.next;
                } else {
                    pre.next = temp.next;
                    temp.next = null;
                }
                break;
            }
            curIndex++;
        }

        return head;
    }


    public ListNode copy2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();

        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }


    @Override
    protected Object[] offerArgs() {
        return new Args(
                buildArrayToListNodeList(new int[]{1, 2}), 2)
                .getArgs();
    }
}
