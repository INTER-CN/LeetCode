package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/string-to-integer-atoi/
 * 字符串处理
 *
 * @Author Gemini
 * 2022-08-09
 **/
public class Atoi {

    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] chars = s.toCharArray();
        int n = chars.length;
        int i = 0;

        // 前导空格
        while (i < n && chars[i] == ' ') i++;
        if (i == n) return 0;

        // 符号
        int sign = chars[i] == '-' ? -1 : 1;
        if (chars[i] == '-' || chars[i] == '+') i++;
        if (i == n) return 0;

        // 前导0
        while (i < n && chars[i] == '0') i++;
        if (i == n) return 0;

        // 数值
        int v = 0, digit;
        int digitCount = 0;
        int max10 = Integer.MAX_VALUE / 10;
        while (i < n && '0' <= chars[i] && chars[i] <= '9') {
            digit = chars[i] - '0';
            if (digitCount == 10) return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            if (digitCount == 9) {
                if (v > max10) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                } else if (v == max10) {
                    if (sign == 1 && digit > 7) return Integer.MAX_VALUE;
                    if (sign == -1 && digit > 8) return Integer.MIN_VALUE;
                }
            }
            v = v * 10 + digit;
            digitCount++;
            i++;
        }

        return sign * v;
    }

    public static void main(String[] args) {
        String s = "4193 with words";
        System.out.println(new Atoi().myAtoi(s));
    }
}
