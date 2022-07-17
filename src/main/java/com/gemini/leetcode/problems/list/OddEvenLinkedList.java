package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/odd-even-linked-list/
 * 链表操作
 *
 * @author 天何
 * @date 2022/7/14
 */
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;

        ListNode evenHead = new ListNode(0);
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenNode = evenHead;
        while (true) {
            evenNode.next = even;
            evenNode = evenNode.next;
            odd.next = even.next;
            even.next = null;
            if (odd.next == null) break;
            odd = odd.next;
            if (odd.next == null) break;
            even = odd.next;
        }

        odd.next = evenHead.next;

        return head;
    }

    public static void main(String[] args) {
        ListNode result = new OddEvenLinkedList().oddEvenList(ListNode.getTestList());
        ListNode.print(result);
    }
}
