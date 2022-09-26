package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/swap-adjacent-in-lr-string/
 * 逻辑题，字符串提取
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class SwapLR {

    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) return false;
        int n = start.length();
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') i++;
            if (i == n) break;
            while (j < n && end.charAt(j) == 'X') j++;
            if (j == n) break;
            if (start.charAt(i) != end.charAt(j)) return false;
            if (start.charAt(i) == 'L' && i < j) return false;
            if (start.charAt(i) == 'R' && i > j) return false;
            i++;
            j++;
        }
        while (i < n && start.charAt(i) == 'X') i++;
        while (j < n && end.charAt(j) == 'X') j++;
        return i == j;
    }

    public static void main(String[] args) {
        String start = "LXXLXRLXXL";
        String end = "XLLXRXLXLX";
        System.out.println(new SwapLR().canTransform(start, end));
    }
}
