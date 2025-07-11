package com.lzc.listnode;

import java.util.StringJoiner;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        ListNode temp = this;
        StringJoiner sj = new StringJoiner("-->");
        while (temp != null) {
            String str = "【" +temp.val + '】';
            sj.add(str);
            temp = temp.next;
        }
        return sj.toString();
    }
}

