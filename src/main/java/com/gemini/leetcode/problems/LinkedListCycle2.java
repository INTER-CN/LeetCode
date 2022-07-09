package com.gemini.leetcode.problems;

import com.gemini.leetcode.model.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 *
 *
 * @author 天何
 * @date 2022/7/8
 */
public class LinkedListCycle2 {

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        ListNode current = head;
        while (current != null) {
            if (set.contains(current)) return current;
            set.add(current);
            current = current.next;
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;

        System.out.println(new LinkedListCycle2().detectCycle(n1).val);
    }
}
