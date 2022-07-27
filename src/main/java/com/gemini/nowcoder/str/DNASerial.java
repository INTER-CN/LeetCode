package com.gemini.nowcoder.str;

import java.util.Scanner;

/**
 * HJ63 DNA序列
 * https://www.nowcoder.com/practice/e8480ed7501640709354db1cc4ffd42a?tpId=37&tqId=21286
 * 字符串处理
 *
 * @author 天何
 * @date 2022/7/24
 */
public class DNASerial {

    private static boolean validChar(char ch) {
        return ch == 'G' || ch == 'C';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = scanner.nextInt();
        scanner.close();

        if (s.length() < n) return;

        String result = s.substring(0, n);
        int max = 0;
        for (int i = 0; i < n; i++) if (validChar(s.charAt(i))) max++;
        int count = max;
        int start = 0, end = n;
        while (end < s.length()) {
            if (validChar(s.charAt(start++))) count--;
            if (validChar(s.charAt(end++))) count++;
            if (count > max) {
                max = count;
                result = s.substring(start, end);
            }
        }

        System.out.println(result);
    }
}
