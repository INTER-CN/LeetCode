package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/
 * 链表操作
 *
 * @author 天何
 * @date 2022/7/15
 */
public class ReverseLinkedListK {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) return head;
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }

        if (len < k) return head;

        int groupNum = len / k;

        ListNode pre = new ListNode(0);
        pre.next = head;

        p = pre;
        ListNode q = head, r = q.next;
        ListNode reversePre = p, reverseTail;
        for (int count = 0; count < groupNum; count++) {
            for (int i = 1; i < k; i++) {
                q.next = p;
                p = q;
                q = r;
                r = q.next;
            }
            q.next = p;
            reverseTail = reversePre.next;
            reversePre.next.next = r;
            reversePre.next = q;

            if (r == null) break;

            reversePre = reverseTail;
            q = r;
            r = q.next;
        }

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode result = new ReverseLinkedListK().reverseKGroup(ListNode.getTestList(), 2);
        ListNode.print(result);
    }
}
