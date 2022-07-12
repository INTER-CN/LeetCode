package com.gemini.nowcoder.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * HJ24 合唱队
 * https://www.nowcoder.com/practice/6d9d69e3898f45169a441632b325c7b4?tpId=37&tqId=21247
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/11
 */
public class Chorus {

    public static void main(String[] args) throws Exception {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = Integer.parseInt(str);
        int[] height = new int[n];
        str = br.readLine();
        String[] heightStrs = str.split(" ");
        for (int i = 0; i < n; i++) height[i] = Integer.parseInt(heightStrs[i]);
        br.close();

        if (n < 3) {
            System.out.println(0);
            return;
        }

        // ascending[i]: height[0..i]形成递增序列且保留height[i]需要移除的个数
        int[] ascending = new int[n];
        for (int i = 1; i < n; i++) {
            ascending[i] = i;
            for (int j = 0; j < i; j++) {
                if (height[j] < height[i]) ascending[i] = Math.min(ascending[i], i - 1 - j + ascending[j]);
            }
        }

        // descending[i]: height[i..n-1]形成递减序列且保留height[i]需要移除的个数
        int[] descending = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            descending[i] = n - 1 - i;
            for (int j = n - 1; j > i; j--) {
                if (height[j] < height[i]) descending[i] = Math.min(descending[i], j - i - 1 + descending[j]);
            }
        }

        int min = n, sum;
        for (int i = 0; i < n; i++) {
            sum = ascending[i] + descending[i];
            min = Math.min(min, sum);
        }

        System.out.println(min);
    }
}
