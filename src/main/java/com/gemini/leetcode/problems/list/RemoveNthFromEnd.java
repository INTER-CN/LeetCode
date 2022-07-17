package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * 链表操作
 *
 * @author 天何
 * @date 2022/7/16
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;

        ListNode p = pre, q = head, r = head;
        for (int i = 0; i < n; i++) r = r.next;
        while (r != null) {
            p = p.next;
            q = q.next;
            r = r.next;
        }

        p.next = q.next;

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode result = new RemoveNthFromEnd().removeNthFromEnd(ListNode.getTestList(), 7);
        ListNode.print(result);
    }
}
