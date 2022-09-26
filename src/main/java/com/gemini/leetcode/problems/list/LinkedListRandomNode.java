package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

import java.util.Random;

/**
 * https://leetcode.cn/problems/linked-list-random-node/
 * 蓄水池算法
 *
 * @Author Gemini
 * 2022-09-05
 **/
public class LinkedListRandomNode {

    private ListNode head;
    private Random random;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        int result = 0;

        ListNode p = head;
        int i = 1;
        while (p != null) {
            if (random.nextInt(i++) == 0) {
                result = p.val;
            }
            p = p.next;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode testList = ListNode.getTestList();
        LinkedListRandomNode obj = new LinkedListRandomNode(testList);
        for (int i = 0; i < 20; i++) System.out.print(obj.getRandom() + ",");
        System.out.println();
    }
}
