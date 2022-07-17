package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/reverse-linked-list-ii/
 * 链表操作
 *
 * @author 天何
 * @date 2022/7/15
 */
public class ReverseLinkedList2 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode pre = new ListNode(0);
        pre.next = head;

        int n = right - left + 1;
        ListNode reversePre = pre;
        for (int i = 1; i < left; i++) reversePre = reversePre.next;
        ListNode reverseHead = reversePre.next;

        ListNode p = reversePre, q = p.next, r = q.next;
        for (int i = 1; i < n; i++) {
            q.next = p;
            p = q;
            q = r;
            r = q.next;
        }
        q.next = p;

        reversePre.next = q;
        reverseHead.next = r;

        return left == 1 ? q : head;
    }

    public static void main(String[] args) {
        ListNode result = new ReverseLinkedList2().reverseBetween(ListNode.getTestList(), 2, 6);
        ListNode.print(result);
    }
}
