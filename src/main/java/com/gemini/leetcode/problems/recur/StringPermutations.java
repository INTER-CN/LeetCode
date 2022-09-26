package com.gemini.leetcode.problems.recur;

import com.google.gson.Gson;

import java.util.*;

/**
 * https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
 * 字符串（字符数组）全排列，可能有重复
 *
 * @Author Gemini
 * 2022-09-23
 **/
public class StringPermutations {

    public String[] permutation(String s) {
        List<String> list = listPermutations(s);
        Set<String> set = new HashSet<>(list);
        return set.toArray(new String[set.size()]);
    }

    private List<String> listPermutations(String s) {
        int n = s.length();

        if (n == 1) return Collections.singletonList(s);

        List<String> subList = listPermutations(s.substring(1));

        List<String> result = new LinkedList<>();
        for (String sub : subList) {
            result.add(s.charAt(0) + sub);
            for (int i = 1; i < n - 1; i++) result.add(sub.substring(0, i) + s.charAt(0) + sub.substring(i));
            result.add(sub + s.charAt(0));
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "aac";

        String[] result = new StringPermutations().permutation(s);
        System.out.println(new Gson().toJson(result));
    }
}
