package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.ListNode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/add-two-numbers-ii/
 * 栈
 *
 * @Author Gemini
 * 2022-09-02
 **/
public class AddTwoNumbers2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode p1 = l1, p2 = l2;
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (p1 != null) {
            s1.push(p1.val);
            p1 = p1.next;
        }
        while (p2 != null) {
            s2.push(p2.val);
            p2 = p2.next;
        }

        ListNode pre = new ListNode(0);
        int adder = 0;
        int sum;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            sum = (s1.isEmpty() ? 0 : s1.pop()) + (s2.isEmpty() ? 0 : s2.pop()) + adder;
            ListNode node = new ListNode(sum % 10);
            node.next = pre.next;
            pre.next = node;
            adder = sum / 10;
        }
        if (adder > 0) {
            ListNode node = new ListNode(adder);
            node.next = pre.next;
            pre.next = node;
        }

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7, new ListNode(2, new ListNode(4, new ListNode(3))));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode result = new AddTwoNumbers2().addTwoNumbers(l1, l2);
        ListNode.print(result);
    }
}
