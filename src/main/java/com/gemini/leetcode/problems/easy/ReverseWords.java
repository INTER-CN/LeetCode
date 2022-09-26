package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 * 字符串操作
 * 需要做trim操作以及删除多余的空格，直接倒序遍历 + StringBuilder
 *
 * @Author Gemini
 * 2022-08-24
 **/
public class ReverseWords {

    public String reverseWords(String s) {
        int right = s.length() - 1, left;
        StringBuilder builder = new StringBuilder();
        while (right >= 0) {
            while (right >= 0 && s.charAt(right) == ' ') right--;
            if (right < 0) break;
            left = right;
            while (left >= 0 && s.charAt(left) != ' ') left--;
            left++;
            builder.append((builder.length() == 0 ? "" : " ") + s.substring(left, right + 1));
            right = left - 1;
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String s = " the  sky is  blue ";
        System.out.println(new ReverseWords().reverseWords(s));
    }
}
