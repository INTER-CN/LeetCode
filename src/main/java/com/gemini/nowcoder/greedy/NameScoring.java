package com.gemini.nowcoder.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * HJ45 名字的漂亮度
 * https://www.nowcoder.com/practice/02cb8d3597cf416d9f6ae1b9ddc4fde3?tpId=37&tqId=21268
 * 字符串处理 + 贪心
 *
 * @author 天何
 * @date 2022/7/21
 */
public class NameScoring {

    private static int score(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        Arrays.sort(count);

        int score = 0;
        for (int i = 25; i >= 0; i--) {
            score += (i + 1) * count[i];
        }

        return score;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) System.out.println(score(scanner.next()));
        scanner.close();
    }
}
