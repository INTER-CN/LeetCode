package com.gemini.leetcode.problems.datastruct;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/remove-k-digits/
 * 贪心 + 栈
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if (k >= num.length()) return "0";

        int removeCount = 0;
        Stack<Integer> stack = new Stack<>();
        int digit;
        for (char c : num.toCharArray()) {
            digit = c - '0';
            while (!stack.isEmpty() && stack.peek() > digit && removeCount < k) {
                stack.pop();
                removeCount++;
            }
            stack.push(digit);
        }

        // keep m digits
        int m = num.length() - k;
        while (stack.size() > m) stack.pop();

        String s = "";
        while (!stack.isEmpty()) s = stack.pop() + s;

        // remove leading zeros
        int index = 0;
        while (index < s.length() && s.charAt(index) == '0') index++;
        if (index == s.length()) return "0";
        else return s.substring(index);
    }

    public static void main(String[] args) {
        String s = "123454321";
        int k = 5;
        System.out.println(new RemoveKDigits().removeKdigits(s, k));
    }
}
