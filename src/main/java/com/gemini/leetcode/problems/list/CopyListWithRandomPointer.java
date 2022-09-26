package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.problems.list.model.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/copy-list-with-random-pointer/
 * 链表操作
 *
 * @Author Gemini
 * 2022-08-24
 **/
public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node pre = new Node(0);
        Map<Node, Node> map = new HashMap<>();
        Node p1 = head, p2 = pre;
        while (p1 != null) {
            p2.next = new Node(p1.val);
            p2 = p2.next;
            p2.random = p1.random;
            map.put(p1, p2);
            p1 = p1.next;
        }

        p2 = pre.next;
        while (p2 != null) {
            p2.random = map.get(p2.random);
            p2 = p2.next;
        }

        return pre.next;
    }

    /**
     * 高端解法，空间复杂度O(1)
     */
    public Node copyRandomList2(Node head) {
        if (head == null) return null;

        Node p = head;
        while (p != null) {
            Node newNode = new Node(p.val);
            newNode.next = p.next;
            newNode.random = p.random;
            p.next = newNode;
            p = newNode.next;
        }

        p = head;
        while (p != null) {
            if (p.random != null) p.next.random = p.random.next;
            p = p.next.next;
        }

        Node pre = new Node(0);
        Node q = pre;
        p = head;
        while (p != null) {
            q.next = p.next;
            q = q.next;
            p.next = q.next;
            p = p.next;
        }

        return pre.next;
    }
}
