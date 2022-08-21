package com.gemini.leetcode.problems.easy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/palindrome-number/
 * 栈 + 队列
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;

        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        int v;
        while (x > 0) {
            v = x % 10;
            stack.push(v);
            queue.add(v);
            x /= 10;
        }

        int n = stack.size() / 2;
        for (int i = 0; i < n; i++) {
            if (stack.pop() != queue.poll()) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome(1234321));
    }
}
