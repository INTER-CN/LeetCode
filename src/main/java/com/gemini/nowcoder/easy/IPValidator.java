package com.gemini.nowcoder.easy;

import java.util.Scanner;

/**
 * HJ90 合法IP
 * https://www.nowcoder.com/practice/995b8a548827494699dc38c3e2a54ee9?tpId=37&tqId=21313
 * 字符串水题
 *
 * @author 天何
 * @date 2022/7/26
 */
public class IPValidator {

    private static boolean validIP(String s) {
        if (s.contains(" ")) return false;
        String[] segments = s.split("\\.");
        if (segments.length != 4) return false;

        for (String segment : segments) {
            try {
                int i = Integer.parseInt(segment);
                if (i < 0 || i > 255) return false;
                if (!segment.equals(i + "")) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();
        scanner.close();

        System.out.println(validIP(s) ? "YES" : "NO");
    }
}
