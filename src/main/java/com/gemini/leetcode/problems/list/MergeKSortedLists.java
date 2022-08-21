package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 * 链表操作
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class MergeKSortedLists {

    class NodePlus {
        public ListNode minNode;
        public int index;

        public NodePlus(ListNode minNode, int index) {
            this.minNode = minNode;
            this.index = index;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        ListNode[] nodes = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) nodes[i] = lists[i];

        ListNode pre = new ListNode(0);
        ListNode p = pre;
        NodePlus nodePlus = getMinNode(nodes);
        while (nodePlus.minNode != null) {
            p.next = nodePlus.minNode;
            p = p.next;
            nodes[nodePlus.index] = nodes[nodePlus.index].next;

            nodePlus = getMinNode(nodes);
        }

        return pre.next;
    }

    private NodePlus getMinNode(ListNode[] nodes) {
        ListNode minNode = nodes[0];
        int index = 0;

        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i] == null) continue;
            if (minNode == null || nodes[i].val < minNode.val) {
                minNode = nodes[i];
                index = i;
            }
        }

        return new NodePlus(minNode, index);
    }

    public static void main(String[] args) {
        ListNode[] lists = {
            ListNode.getTestList(),
            ListNode.getTestList(),
            ListNode.getTestList()
        };

        ListNode result = new MergeKSortedLists().mergeKLists(lists);
        ListNode.print(result);
    }
}
