package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/find-closest-lcci/
 * 字符串数组水题
 *
 * @Author Gemini
 * 2022-09-07
 **/
public class FindClosest {

    public int findClosest(String[] words, String word1, String word2) {
        if (word1.equals(word2)) return 0;

        int i1 = -1;
        int i2 = -1;
        int result = -1;
        int distance;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) i1 = i;
            else if (word2.equals(words[i])) i2 = i;
            if (i1 >= 0 && i2 >= 0) {
                distance = Math.abs(i1 - i2);
                if (result == -1 || distance < result) result = distance;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"};
        String word1 = "a";
        String word2 = "student";
        System.out.println(new FindClosest().findClosest(words, word1, word2));
    }
}
