package com.gemini.nowcoder.array;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * HJ92 在字符串中找出连续最长的数字串
 * https://www.nowcoder.com/practice/2c81f88ecd5a4cc395b5308a99afbbec?tpId=37&tqId=21315
 * 数组线性扫描
 *
 * @author 天何
 * @date 2022/7/26
 */
public class LongestDigitSubstring {

    private static void printLDS(String s) {
        int n = s.length();
        int max = 0;
        List<String> resultList = new LinkedList<>();

        // 定位到第一个数字
        int p = 0;
        while (p < n && !isDigit(s.charAt(p))) p++;
        int q = p;

        int len;
        while (p < n) {
            while (q < n && isDigit(s.charAt(q))) q++;
            len = q - p;
            if (len > max) {
                max = len;
                resultList = new LinkedList<>();
                resultList.add(s.substring(p, q));
            } else if (len == max) {
                resultList.add(s.substring(p, q));
            }

            if (q == n) break;

            p = q;
            while (p < n && !isDigit(s.charAt(p))) p++;
            if (p == n) break;

            q = p;
        }

        for (String item : resultList) System.out.print(item);
        System.out.print(",");
        System.out.println(max);
    }

    private static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            printLDS(scanner.nextLine());
        }
        scanner.close();
    }
}
