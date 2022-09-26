package com.gemini.leetcode.problems.easy;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/compare-version-numbers/
 * 版本比较，字符串水题
 *
 * @Author Gemini
 * 2022-09-05
 **/
public class CompareVersionNumbers {

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        int n1 = v1.length;
        String[] v2 = version2.split("\\.");
        int n2 = v2.length;
        int n = Math.max(n1, n2);
        int[] a1 = new int[n];
        int[] a2 = new int[n];
        for (int i = 0; i < n1; i++) a1[i] = Integer.parseInt(v1[i]);
        for (int i = 0; i < n2; i++) a2[i] = Integer.parseInt(v2[i]);

        for (int i = 0; i < n; i++) {
            if (a1[i] < a2[i]) return -1;
            else if (a1[i] > a2[i]) return 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        String[] split = "1.01.001".split("\\.");
        System.out.println(new Gson().toJson(split));
    }
}
