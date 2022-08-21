package com.gemini.leetcode.problems.recur;

/**
 * https://leetcode.cn/problems/number-of-digit-one/
 * 递归
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class NumberOfDigit1 {

    public int countDigitOne(int n) {
        // f(9) = 1
        // f(99) = 10 * f(9) + 10 = 20
        // f(999) = 10 * f(99) + 100 = 300
        // f(9999) = 10 * f(999) + 1000 = 4000 ...
        if (n == 0) return 0;
        if (n < 10) return 1;
        switch (n) {
            case 99:
                return 20;
            case 999:
                return 300;
            case 9_999:
                return 4_000;
            case 99_999:
                return 50_000;
            case 999_999:
                return 600_000;
            case 9_999_999:
                return 7_000_000;
            case 99_999_999:
                return 80_000_000;
            case 999_999_999:
                return 900_000_000;
            default:
                break;
        }

        int base = 10;
        while (base * 10 <= n) base *= 10;

        int topDigit = n / base;
        int remaining = n % base;

        int result = 0;

        if (topDigit == 1) {
            result += countDigitOne(base - 1);
            result += (remaining + 1);
            result += countDigitOne(remaining);
        } else if (topDigit == 2) {
            result += countDigitOne(base - 1);
            result += (base + countDigitOne(base - 1));
            result += countDigitOne(remaining);
        } else {
            result += countDigitOne(base - 1);
            result += (base + countDigitOne(base - 1));
            result += (countDigitOne(base - 1) * (topDigit - 2));
            result += countDigitOne(remaining);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfDigit1().countDigitOne(13));
    }
}
