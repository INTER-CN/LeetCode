package com.gemini.leetcode.problems;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/add-two-numbers/
 * 链表操作
 *
 * @author 天何
 * @date 2022/7/4
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode node1 = l1;
        ListNode node2 = l2;
        int sum, adder = 0;

        ListNode head = new ListNode(0);
        ListNode current = head;
        while (node1 != null || node2 != null) {
            sum = ((node1 != null) ? node1.val : 0) + ((node2 != null) ? node2.val : 0) + adder;
            ListNode node = new ListNode(sum % 10);
            adder = sum / 10;
            current.next = node;
            current = node;
            if (node1 != null) node1 = node1.next;
            if (node2 != null) node2 = node2.next;
        }
        if (adder > 0) {
            ListNode lastNode = new ListNode(adder);
            current.next = lastNode;
        }

        return head.next;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;

        ListNode resultNode = new AddTwoNumbers().addTwoNumbers(a1, b1);
        System.out.println(resultNode);
    }
}
