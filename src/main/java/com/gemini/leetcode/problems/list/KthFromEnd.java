package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * 链表水题
 *
 * @author 天何
 * @date 2022/7/9
 */
public class KthFromEnd {

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return null;

        ListNode p = head, q = head;

        for (int i = 1; i < k; i++) {
            q = q.next;
            if (q == null) return null;
        }

        while (q.next != null) {
            p = p.next;
            q = q.next;
        }

        return p;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(new KthFromEnd().getKthFromEnd(n1, 2).val);
    }
}
