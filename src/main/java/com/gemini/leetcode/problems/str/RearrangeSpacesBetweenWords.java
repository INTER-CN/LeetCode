package com.gemini.leetcode.problems.str;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/rearrange-spaces-between-words/
 * 字符串处理
 *
 * @Author Gemini
 * 2022-09-07
 **/
public class RearrangeSpacesBetweenWords {

    public String reorderSpaces(String text) {
        List<String> wordList = new LinkedList<>();
        StringBuilder builder = new StringBuilder();

        int n = text.length();
        int spaceCount = n;
        // [left,right)
        int left = 0, right = 0;
        while (right < n && text.charAt(right) == ' ') right++;

        while (right < n) {
            left = right;
            while (right < n && text.charAt(right) != ' ') right++;
            wordList.add(text.substring(left, right));
            spaceCount -= (right - left);
            while (right < n && text.charAt(right) == ' ') right++;
        }

        int m = wordList.size();

        if (m == 1) {
            builder.append(wordList.get(0));
            for (int j = 0; j < spaceCount; j++) builder.append(' ');
            return builder.toString();
        }

        int spaceUnit = spaceCount / (m - 1);
        for (int i = 0; i < m - 1; i++) {
            builder.append(wordList.get(i));
            for (int j = 0; j < spaceUnit; j++) builder.append(' ');
        }
        builder.append(wordList.get(m - 1));
        int remaining = spaceCount - spaceUnit * (m - 1);
        for (int j = 0; j < remaining; j++) builder.append(' ');

        return builder.toString();
    }

    public static void main(String[] args) {
        String text = " practice   makes   perfect";
        System.out.println(new RearrangeSpacesBetweenWords().reorderSpaces(text));
    }
}
