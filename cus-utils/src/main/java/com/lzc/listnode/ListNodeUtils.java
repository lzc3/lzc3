package com.lzc.listnode;

public class ListNodeUtils {

    /**
     * 将数组转换成链表，并返回头节点
     * @param arr 数组
     * @return 头节点
     */
    public static ListNode buildArrayToListNodeList(int[] arr) {
        ListNode header = new ListNode();
        if (arr == null || arr.length == 0) {
            return header;
        }

        ListNode tail = header;
        for (int i : arr) {
            ListNode curNode = new ListNode(i);
            tail.next = curNode;
            tail = curNode;
        }
        return header.next;
    }

}
