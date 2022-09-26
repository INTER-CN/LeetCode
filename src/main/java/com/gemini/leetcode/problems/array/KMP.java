package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * 经典算法：KMP
 *
 * @Author Gemini
 * 2022-09-05
 **/
public class KMP {

    public int strStr(String haystack, String needle) {
        char[] s = haystack.toCharArray();
        char[] p = needle.toCharArray();
        int n = s.length;
        int m = p.length;

        // build next
        int[] pre = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && p[i] != p[j]) j = pre[j - 1];
            if (p[i] == p[j]) j++;
            pre[i] = j;
        }

        // scan and match
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && s[i] != p[j]) j = pre[j - 1];
            if (s[i] == p[j]) j++;
            if (j == m) return i - m + 1;
        }

        return -1;
    }

    /**
     * 抄答案（宫水三叶）
     */
    public int strStr2(String haystack, String needle) {
        if (needle.isEmpty()) return 0;

        // 分别读取原串和匹配串的长度
        int n = haystack.length(), m = needle.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        String ss = " " + haystack;
        String pp = " " + needle;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }

        return -1;
    }

    public static void main(String[] args) {
        String s = "abeababeabf";
        String p = "abeabf";
        System.out.println(new KMP().strStr(s, p));
    }

}
