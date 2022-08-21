package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 * 链表水题
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode pre = new ListNode(0);

        ListNode p = list1, q = list2, r = pre;
        while (p != null && q != null) {
            if (p.val > q.val) {
                r.next = q;
                q = q.next;
            } else {
                r.next = p;
                p = p.next;
            }
            r = r.next;
        }

        if (p != null) r.next = p;
        if (q != null) r.next = q;

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode list1 = ListNode.getTestList();
        ListNode list2 = ListNode.getTestList();
        ListNode result = new MergeTwoSortedLists().mergeTwoLists(list1, list2);
        ListNode.print(result);
    }
}
