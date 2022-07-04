package com.gemini.leetcode.problems;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 * 水
 *
 * @author 天何
 * @date 2022/6/27
 */
public class RemoveDuplicatesFromSortedList {

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode firstCursor = head, checkCursor = head;

        while (firstCursor != null && checkCursor != null) {
            while (checkCursor != null && checkCursor.val == firstCursor.val) {
                checkCursor = checkCursor.next;
            }
            firstCursor.next = checkCursor;
            firstCursor = checkCursor;
        }

        return head;
    }

    public static void main(String[] args) {

    }

}
