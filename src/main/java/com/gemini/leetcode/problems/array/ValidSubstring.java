package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters/
 * 线性扫描
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class ValidSubstring {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // find first valid substring
        int[] window = new int[52];
        int charCount = 0;
        int right = 0;
        while (right < s.length() && charCount < 3) {
            if (++window[getIndex(s.charAt(right++))] == 1) ++charCount;
        }
        if (right == s.length() && charCount < 3) return s.length();
        --window[getIndex(s.charAt(--right))];
        --charCount;

        int max = right, len;
        int left = 0;

        while (true) {
            if (--window[getIndex(s.charAt(left++))] == 0) --charCount;
            while (right < s.length() && charCount < 3) {
                if (++window[getIndex(s.charAt(right++))] == 1) ++charCount;
            }
            if (right == s.length() && charCount < 3) break;
            --window[getIndex(s.charAt(--right))];
            --charCount;
            len = right - left;
            if (len > max) max = len;
        }
        len = right - left;
        if (len > max) max = len;


        return max;
    }

    private int getIndex(char c) {
        return ('a' <= c && c <= 'z') ? (c - 'a') : (c - 'A' + 26);
    }

    public static void main(String[] args) {
        String s = "eceba";
        System.out.println(new ValidSubstring().lengthOfLongestSubstringTwoDistinct(s));
    }
}
