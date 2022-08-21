package com.gemini.leetcode.problems.datastruct;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/basic-calculator-ii/
 * 栈
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class BasicCalculator2 {

    public int calculate(String s) {
        int index = 0;

        // read first number
        while (index < s.length() && s.charAt(index) == ' ') index++;
        int sign = 1;
        if (s.charAt(index) == '-') {
            sign = -1;
            index++;
            while (index < s.length() && !isDigit(s, index)) index++;
        }
        int start = index;
        while (index < s.length() && isDigit(s, index)) index++;
        int end = index;
        int num = sign * Integer.parseInt(s.substring(start, end));
        if (index == s.length()) return num;

        Stack<Integer> stack = new Stack<>();
        stack.push(num);

        int v;
        while (index < s.length()) {
            // read one operator
            while (index < s.length() && (s.charAt(index) == ' ' || isDigit(s, index))) index++;
            if (index == s.length()) break;
            char operator = s.charAt(index++);

            // read one operand
            while (index < s.length() && !isDigit(s, index)) index++;
            if (index == s.length()) break;
            start = index;
            while (index < s.length() && isDigit(s, index)) index++;
            end = index;
            num = Integer.parseInt(s.substring(start, end));

            switch (operator) {
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    stack.push(-num);
                    break;
                case '*':
                    v = stack.pop();
                    v *= num;
                    stack.push(v);
                    break;
                case '/':
                    v = stack.pop();
                    v /= num;
                    stack.push(v);
                    break;
                default:
                    break;
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) sum += stack.pop();
        return sum;
    }

    private boolean isDigit(String s, int index) {
        return '0' <= s.charAt(index) && s.charAt(index) <= '9';
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalculator2().calculate(" - 3-5 / 2 "));
    }
}
