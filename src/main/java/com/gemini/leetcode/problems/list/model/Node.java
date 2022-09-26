package com.gemini.leetcode.problems.list.model;

/**
 * @Author Gemini
 * 2022-08-24
 **/
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
