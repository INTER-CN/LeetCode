package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/sort-list/
 * 链表排序，使用归并排序
 *
 * @author 天何
 * @date 2022/7/18
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = new ListNode(0);
        ListNode p, q, r, left, right, fast, slow;

        // 找到中间节点
        fast = head;
        slow = head;
        while (fast != null) {
            fast = fast.next;
            if (fast == null) break;
            fast = fast.next;
            if (fast == null) break;
            slow = slow.next;
        }

        // 递归排序
        right = sortList(slow.next);
        slow.next = null;
        left = sortList(head);

        // 合并结果
        p = left;
        q = right;
        r = pre;
        while (p != null && q != null) {
            if (p.val <= q.val) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }
        while (p != null) {
            r.next = p;
            p = p.next;
            r = r.next;
        }
        while (q != null) {
            r.next = q;
            q = q.next;
            r = r.next;
        }

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode result = new SortList().sortList(ListNode.getTestList());
        ListNode.print(result);
    }
}
