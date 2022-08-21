package com.gemini.leetcode.problems.logic;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/short-encoding-of-words/
 * 字符串排序
 *
 * @Author Gemini
 * 2022-08-09
 **/
public class EncodingWords {

    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());

        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            if (builder.toString().contains(word + "#")) continue;
            builder.append(word + "#");
        }

        return builder.length();
    }

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        System.out.println(new EncodingWords().minimumLengthEncoding(words));
    }
}
