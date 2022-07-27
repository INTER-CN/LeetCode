package com.gemini.nowcoder.array;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * HJ74 参数解析
 * https://www.nowcoder.com/practice/668603dc307e4ef4bb07bcd0615ea677?tpId=37&tqId=21297
 * 字符数组线性扫描
 *
 * @author 天何
 * @date 2022/7/26
 */
public class ArgumentScanner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();
        scanner.close();

        char[] arr = s.toCharArray();
        int n = arr.length;
        int p = 0, q = 0;
        List<String> argList = new LinkedList<>();
        while (q < n) {
            if (arr[p] == '"') {
                q = p + 1;
                while (q < n && arr[q] != '"') q++;
                argList.add(s.substring(p + 1, q));
                if (q == n - 1) break;
                p = q + 1;
            } else {
                while (q < n && arr[q] != ' ') q++;
                argList.add(s.substring(p, q));
                if (q == n) break;
                p = q;
            }
            while (p < n && arr[p] == ' ') p++;
            q = p;
        }

        System.out.println(argList.size());
        for (String arg : argList) {
            System.out.println(arg);
        }
    }
}
