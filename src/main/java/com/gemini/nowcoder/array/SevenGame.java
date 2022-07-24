package com.gemini.nowcoder.array;

import java.util.Scanner;

/**
 * HJ55 挑7
 * https://www.nowcoder.com/practice/ba241b85371c409ea01ac0aa1a8d957b?tpId=37&tqId=21278
 * 数组水题
 *
 * @author 天何
 * @date 2022/7/22
 */
public class SevenGame {

    private static final int MAX = 30000;

    private static int[] result;

    static {
        result = new int[MAX + 1];
        int count7 = 0;
        for (int i = 1; i <= MAX; i++) {
            if (get7(i)) count7++;
            result[i] = count7;
        }
    }

    private static boolean get7(int n) {
        if (n % 7 == 0) return true;
        while (n > 0) {
            if (n % 10 == 7) return true;
            n /= 10;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(result[n]);
    }
}
