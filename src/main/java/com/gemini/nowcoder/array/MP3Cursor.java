package com.gemini.nowcoder.array;

import java.util.Scanner;

/**
 * HJ64 MP3光标位置
 * https://www.nowcoder.com/practice/eaf5b886bd6645dd9cfb5406f3753e15?tpId=37&tqId=21287
 * 数组操作
 *
 * @author 天何
 * @date 2022/7/26
 */
public class MP3Cursor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String actions = scanner.next();
        scanner.close();

        int[] window = new int[2];
        int index = 1;

        // 不需要翻页
        if (n <= 4) {
            int offset = 0;
            for (char action : actions.toCharArray()) {
                if (action == 'D') offset++;
                else if (action == 'U') offset--;
            }
            offset = offset % n;
            if (offset < 0) offset += n;
            for (int i = 1; i <= n; i++) System.out.print(i + " ");
            System.out.println();
            System.out.println(index + offset);
            return;
        }

        // 需要翻页
        for (char action : actions.toCharArray()) {
            if (index == 1 && action == 'U') {
                index = n;
                window[1] = n;
                window[0] = n - 3;
            } else if (index == n && action == 'D') {
                index = 1;
                window[0] = 1;
                window[1] = 4;
            } else if (index == window[0] && action == 'U') {
                index--;
                window[0]--;
                window[1]--;
            } else if (index == window[1] && action == 'D') {
                index++;
                window[0]++;
                window[1]++;
            } else if (action == 'U') {
                index--;
            } else if (action == 'D') {
                index++;
            }
        }

        for (int i = window[0]; i <= window[1]; i++) System.out.print(i + " ");
        System.out.println();
        System.out.println(index);
    }
}
