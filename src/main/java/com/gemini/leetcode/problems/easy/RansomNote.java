package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/ransom-note/
 * 水题
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] r = new int[26];
        int[] m = new int[26];

        for (char c : ransomNote.toCharArray()) r[c - 'a']++;
        for (char c : magazine.toCharArray()) m[c - 'a']++;

        for (int i = 0; i < 26; i++) if (r[i] > m[i]) return false;

        return true;
    }
}
