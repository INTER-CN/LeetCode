package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/minimum-window-substring/
 * 线性扫描 + 计数
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        int[] target = new int[52];
        for (char c : t.toCharArray()) addCount(target, c);

        int[] window = new int[52];
        int start = 0, end = 0;
        String result = "", sub;
        while (end < s.length()) {
            // 右端点右移直到覆盖
            while (end < s.length() && !covered(window, target)) addCount(window, s.charAt(end++));
            if (!covered(window, target)) return result;

            // 左端点右移直到不覆盖
            while (start < end && covered(window, target)) removeCount(window, s.charAt(start++));
            if (start <= end) {
                sub = s.substring(start - 1, end);
                if (result.equals("") || result.length() > sub.length()) result = sub;
            }
        }

        return result;
    }

    private void addCount(int[] arr, char c) {
        if ('a' <= c && c <= 'z') ++arr[c - 'a'];
        else ++arr[c - 'A' + 26];
    }

    private void removeCount(int[] arr, char c) {
        if ('a' <= c && c <= 'z') --arr[c - 'a'];
        else --arr[c - 'A' + 26];
    }

    private boolean covered(int[] window, int[] target) {
        for (int i = 0; i < target.length; i++) {
            if (target[i] > window[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "a";
        String t = "a";
        System.out.println(new MinimumWindowSubstring().minWindow(s, t));
    }
}
