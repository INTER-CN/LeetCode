package com.gemini.leetcode.problems.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters/
 * 哈希表
 *
 * @Author Gemini
 * 2022-08-27
 **/
public class SubstringWithKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;

        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        // [start, end)
        int start = 0, end = 0;
        int count = 0;
        int len;
        while (true) {
            // move end rightwards until count == k
            while (end < s.length() && (count < k || map.getOrDefault(s.charAt(end), 0) > 0)) {
                int v = map.getOrDefault(s.charAt(end), 0);
                map.put(s.charAt(end), v + 1);
                if (v == 0) count++;
                end++;
            }
            len = end - start;
            if (len > max) max = len;
            if (end == s.length()) break;

            // move start rightwards until count < k
            while (start < end && count == k) {
                int v = map.get(s.charAt(start));
                map.put(s.charAt(start), v - 1);
                if (v == 1) count--;
                start++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String s = "eceba";
        int k = 2;
        System.out.println(new SubstringWithKDistinctCharacters().lengthOfLongestSubstringKDistinct(s, k));
    }
}
