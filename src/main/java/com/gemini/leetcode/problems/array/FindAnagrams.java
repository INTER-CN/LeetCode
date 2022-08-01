package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 * 字符串线性扫描
 *
 * @author 天何
 * @date 2022/7/30
 */
public class FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new LinkedList<>();
        if (s.length() < p.length()) return list;

        int[] targetAnagram = new int[26];
        for (char ch : p.toCharArray()) targetAnagram[ch - 'a']++;

        int left = 0, right = p.length();
        int[] sourceAnagram = new int[26];
        for (int i = left; i < right; i++) sourceAnagram[s.charAt(i) - 'a']++;

        while (right <= s.length()) {
            if (equalAnagram(sourceAnagram, targetAnagram)) list.add(left);
            if (right == s.length()) break;
            sourceAnagram[s.charAt(left++) - 'a']--;
            sourceAnagram[s.charAt(right++) - 'a']++;
        }

        return list;
    }

    private boolean equalAnagram(int[] source, int[] target) {
        for (int i = 0; i < 26; i++) {
            if (source[i] != target[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> result = new FindAnagrams().findAnagrams("cbaebabacd", "abc");
        System.out.println(new Gson().toJson(result));
    }

}
