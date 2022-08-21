package com.gemini.leetcode.problems.logic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/reverse-integer/
 * 队列操作 + 逻辑处理
 *
 * @Author Gemini
 * 2022-08-09
 **/
public class ReverseInteger {

    public int reverse(int x) {
        if (x == 0 || x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) return 0;

        int sign = x > 0 ? 1 : -1;
        int v = Math.abs(x);
        Queue<Integer> queue = new LinkedList<>();
        while (v > 0) {
            queue.add(v % 10);
            v /= 10;
        }

        int r = 0;
        for (int i = 0; i < 9; i++) {
            if (queue.isEmpty()) break;
            r = r * 10 + queue.poll();
        }

        if (queue.isEmpty()) return sign * r;

        int max10 = Integer.MAX_VALUE / 10;
        if (r > max10) {
            return 0;
        } else if (r == max10) {
            int digit = queue.peek();
            if (sign == 1 && digit > 7 || sign == -1 && digit > 8) return 0;
        }

        r = r * 10 + queue.poll();
        return sign * r;
    }

    public static void main(String[] args) {
        int n = 1463847412;
        System.out.println(new ReverseInteger().reverse(n));
    }
}
