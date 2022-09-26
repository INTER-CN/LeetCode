package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/one-away-lcci/
 * 字符串编辑距离
 * 水题
 *
 * @Author Gemini
 * 2022-09-07
 **/
public class OneAway {

    public boolean oneEditAway(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1) return false;
        if (s1.equals(s2)) return true;

        if (s1.length() - s2.length() > 0) return oneInsertAway(s2, s1);
        else if (s1.length() - s2.length() < 0) return oneInsertAway(s1, s2);

        return oneReplaceAway(s1, s2);
    }

    private boolean oneReplaceAway(String s1, String s2) {
        int n = s1.length();
        int i = 0;
        while (i < n && s1.charAt(i) == s2.charAt(i)) i++;
        if (i == n - 1) return true;
        return s1.substring(i + 1).equals(s2.substring(i + 1));
    }

    private boolean oneInsertAway(String s1, String s2) {
        int n = s1.length();
        int i = 0;
        while (i < n && s1.charAt(i) == s2.charAt(i)) i++;
        if (i == n) return true;
        return s1.substring(i).equals(s2.substring(i + 1));
    }

    public static void main(String[] args) {
        String s1 = "pale";
        String s2 = "ple";
        System.out.println(new OneAway().oneEditAway(s1, s2));
    }
}
