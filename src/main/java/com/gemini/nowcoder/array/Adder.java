package com.gemini.nowcoder.array;

import java.util.Scanner;

/**
 * HJ57 高精度整数加法
 * https://www.nowcoder.com/practice/49e772ab08994a96980f9618892e55b6?tpId=37&tqId=21280
 * 大数加法
 *
 * @author 天何
 * @date 2022/7/22
 */
public class Adder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();
        scanner.close();

        int n = Math.max(s1.length(), s2.length()) + 1;
        int[] d1 = new int[n];
        int[] d2 = new int[n];
        int[] result = new int[n];

        int i = 0, j;
        for (j = s1.length() - 1; j >= 0; j--) d1[i++] = s1.charAt(j) - '0';
        i = 0;
        for (j = s2.length() - 1; j >= 0; j--) d2[i++] = s2.charAt(j) - '0';

        int sum, adder = 0;
        for (i = 0; i < n; i++) {
            sum = d1[i] + d2[i] + adder;
            result[i] = sum % 10;
            adder = sum / 10;
        }

        i = n - 1;
        while (i >= 0 && result[i] == 0) i--;
        if (i < 0) {
            System.out.println("0");
            return;
        }

        StringBuilder builder = new StringBuilder();
        while (i >= 0) builder.append(result[i--]);

        System.out.println(builder);
    }
}
