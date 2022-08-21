package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/reorder-list/
 * 递归
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;

        // 找到最后一个节点
        ListNode p = head;
        ListNode q = p.next;
        ListNode r = q.next;
        while (r != null) {
            p = p.next;
            q = q.next;
            r = r.next;
        }

        // reorder，递归处理
        p.next = null;
        r = head.next;
        reorderList(r);
        head.next = q;
        q.next = r;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.getTestList();
        new ReorderList().reorderList(list);
        ListNode.print(list);
    }
}
