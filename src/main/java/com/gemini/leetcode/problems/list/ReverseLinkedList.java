package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 * https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/
 * https://leetcode.cn/problems/UHnkqh/
 * 链表操作
 *
 * @author 天何
 * @date 2022/7/15
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = new ListNode(0);
        pre.next = head;

        ListNode p = pre, q = pre.next, r = q.next;
        while (r != null) {
            q.next = p;
            p = q;
            q = r;
            r = q.next;
        }
        q.next = p;

        head.next = null;

        return q;
    }

    public static void main(String[] args) {
        ListNode result = new ReverseLinkedList().reverseList(ListNode.getTestList());
        ListNode.print(result);
    }
}
