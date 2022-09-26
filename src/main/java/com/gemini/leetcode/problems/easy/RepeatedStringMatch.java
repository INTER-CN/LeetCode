package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/repeated-string-match/
 * 字符串操作
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class RepeatedStringMatch {

    public int repeatedStringMatch(String a, String b) {
        if (a.length() >= b.length()) {
            if (a.indexOf(b) >= 0) return 1;
            if ((a + a).indexOf(b) >= 0) return 2;
            return -1;
        }

        StringBuilder builder = new StringBuilder();
        int count = 0;
        while (builder.length() < b.length()) {
            builder.append(a);
            count++;
        }

        if (builder.indexOf(b) >= 0) return count;

        builder.append(a);
        count++;
        if (builder.indexOf(b) >= 0) return count;

        return -1;
    }
}
