package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
 * 链表操作
 *
 * @Author Gemini
 * 2022-08-22
 **/
public class RemoveDuplicatesFromSortedList2 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode p = pre, q = head, r = head.next;
        while (r != null) {
            while (r != null && q.val != r.val) {
                p = p.next;
                q = q.next;
                r = r.next;
            }
            if (r == null) break;
            q = r.next;
            while (q != null && q.val == r.val) q = q.next;
            p.next = q;
            if (q == null) break;
            r = q.next;
        }

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode testList = ListNode.getTestList();
        ListNode result = new RemoveDuplicatesFromSortedList2().deleteDuplicates(testList);
        ListNode.print(result);
    }
}
