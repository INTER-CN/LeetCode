package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string-ii/
 * 两次遍历
 *
 * @Author Gemini
 * 2022-09-14
 **/
public class ReverseWords2 {

    public void reverseWords(char[] s) {
        int n = s.length;
        reverseInRange(s, 0, n - 1);

        int left, right = 0;
        while (right < n) {
            left = right;
            while (right < n && s[right] != ' ') right++;
            reverseInRange(s, left, right - 1);
            right++;
        }
    }

    private void reverseInRange(char[] s, int start, int end) {
        int i = start, j = end;
        char temp;
        while (i < j) {
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
