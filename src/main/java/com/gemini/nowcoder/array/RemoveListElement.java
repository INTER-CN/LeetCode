package com.gemini.nowcoder.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * HJ48 从单向链表中删除指定值的节点
 * https://www.nowcoder.com/practice/f96cd47e812842269058d483a11ced4f?tpId=37&tqId=21271
 * 哈希表 + 链表
 * 根据输入构造链表，并遍历链表
 *
 * @author 天何
 * @date 2022/7/21
 */
public class RemoveListElement {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, ListNode> nodeMap = new HashMap<>();

        int n = scanner.nextInt();
        ListNode pre = new ListNode(0);
        int headVal = scanner.nextInt();
        ListNode head = new ListNode(headVal);
        pre.next = head;
        nodeMap.put(headVal, head);
        int p, q;
        ListNode pNode;
        for (int i = 1; i < n; i++) {
            q = scanner.nextInt();
            p = scanner.nextInt();
            pNode = nodeMap.get(p);
            ListNode qNode = new ListNode(q);
            nodeMap.put(q, qNode);
            qNode.next = pNode.next;
            pNode.next = qNode;
        }

        int target = scanner.nextInt();

        pNode = pre.next;
        while (pNode != null) {
            if (pNode.val != target) System.out.print(pNode.val + " ");
            pNode = pNode.next;
        }
        System.out.println();

        scanner.close();
    }
}
