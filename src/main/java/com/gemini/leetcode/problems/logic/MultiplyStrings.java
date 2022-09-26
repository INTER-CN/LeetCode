package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/multiply-strings/
 * 大数乘法
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        String sum = "0";
        int n = num2.length();
        for (int trailingZeros = 0; trailingZeros < n; trailingZeros++) {
            int digit = num2.charAt(n - 1 - trailingZeros) - '0';
            if (digit == 0) continue;
            String multiResult = multiplyOneDigit(num1, digit);
            StringBuilder builder = new StringBuilder();
            builder.append(multiResult);
            for (int i = 0; i < trailingZeros; i++) builder.append("0");
            sum = add(sum, builder.toString());
        }

        return sum;
    }

    private String multiplyOneDigit(String num, int digit) {
        if (digit == 0) return "0";
        if (digit == 1) return num;

        String result = "";

        int adder = 0, m;
        for (int i = num.length() - 1; i >= 0; i--) {
            m = (num.charAt(i) - '0') * digit + adder;
            result = (m % 10) + result;
            adder = m / 10;
        }
        if (adder > 0) result = adder + result;

        return result;
    }

    private String add(String num1, String num2) {
        if (num1 == "" || num1 == "0") return num2;
        if (num2 == "" || num2 == "0") return num1;

        String result = "";

        int n = Math.max(num1.length(), num2.length());
        int i1 = num1.length() - 1;
        int i2 = num2.length() - 1;
        int adder = 0, n1, n2, s;
        for (int i = 0; i < n; i++) {
            n1 = i1 >= 0 ? (num1.charAt(i1--) - '0') : 0;
            n2 = i2 >= 0 ? (num2.charAt(i2--) - '0') : 0;
            s = n1 + n2 + adder;
            result = (s % 10) + result;
            adder = s / 10;
        }
        if (adder > 0) result = adder + result;

        return result;
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(new MultiplyStrings().multiply(num1, num2));
    }
}
