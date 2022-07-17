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
        System.out.println(new KthFromEnd().getKthFromEnd(ListNode.getTestList(), 2).val);
    }
}
