package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/expressive-words/
 * 字符线性扫描
 *
 * @Author Gemini
 * 2022-08-31
 **/
public class ExpressiveWords {

    public int expressiveWords(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (isStretchy(s, word)) count++;
        }
        return count;
    }

    private boolean isStretchy(String s, String word) {
        int m = s.length();
        int n = word.length();

        int i = 0, j = 0;
        int i0, j0;
        int iLen, jLen;
        boolean stretchy;
        while (i < m && j < n) {
            if (s.charAt(i) != word.charAt(j)) return false;
            i0 = i;
            j0 = j;
            while (i < m && s.charAt(i0) == s.charAt(i)) i++;
            while (j < n && word.charAt(j0) == word.charAt(j)) j++;
            iLen = i - i0;
            jLen = j - j0;
            stretchy = (iLen == jLen || iLen > jLen && iLen >= 3);
            if (!stretchy) return false;
        }

        return i == m && j == n;
    }

    public static void main(String[] args) {
        String s = "heeellooo";
        String[] words = {"hello", "hi", "helo"};
        System.out.println(new ExpressiveWords().expressiveWords(s, words));
    }
}
