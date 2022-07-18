package com.gemini.leetcode.problems.datastruct;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/lru-cache/
 *
 * @author 天何
 * @date 2022/7/17
 */
public class LRUCache {

    class ListNode {
        public int key;
        public int value;
        public ListNode left;
        public ListNode right;

        public ListNode() {}
    }

    private int capacity;

    private int usedCapacity;

    private ListNode head;

    private ListNode tail;

    private Map<Integer, ListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.usedCapacity = 0;
        this.head = new ListNode();
        this.tail = this.head;
        map = new HashMap<>();
    }

    public int get(int key) {
        ListNode node = getNode(key);
        return node == null ? -1 : node.value;
    }

    private ListNode getNode(int key) {
        // 根据map取出节点
        ListNode node = map.get(key);
        if (node == null) return null;

        if (node.left == head) return node;

        // 节点提到最前
        if (node == tail) {
            tail = tail.left;
        } else {
            node.left.right = node.right;
            node.right.left = node.left;
        }
        node.left = head;
        node.right = head.right;
        head.right.left = node;
        head.right = node;

        return node;
    }

    public void put(int key, int value) {
        // 根据map取出节点
        ListNode node = getNode(key);
        if (node != null) {
            node.value = value;
            return;
        }


        // 排到队首
        node = new ListNode();
        node.key = key;
        node.value = value;
        if (head.right != null) {
            node.left = head;
            node.right = head.right;
            head.right.left = node;
            head.right = node;
        } else {
            head.right = node;
            node.left = head;
            tail = node;
        }
        map.put(key, node);

        // 砍掉队尾
        if (usedCapacity == capacity) {
            map.remove(tail.key);
            tail = tail.left;
            tail.right = null;
        } else {
            ++usedCapacity;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
    }
}
