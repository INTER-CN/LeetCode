package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/find-and-replace-in-string/
 * 模拟
 *
 * @Author Gemini
 * 2022-08-27
 **/
public class FindAndReplaceString {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        int[] flag = new int[n];
        for (int i = 0; i < n; i++) flag[i] = -2;

        int m = indices.length;
        for (int i = 0; i < m; i++) {
            if (!s.substring(indices[i]).startsWith(sources[i])) continue;
            flag[indices[i]] = i;
            for (int j = 1; j < sources[i].length(); j++) flag[indices[i] + j] = -1;
        }

        StringBuilder builder = new StringBuilder();
        int left = 0, right;
        while (left < n) {
            if (flag[left] >= 0) {
                builder.append(targets[flag[left]]);
                right = left + 1;
                while (right < n && flag[right] == -1) right++;
                left = right;
            } else {
                right = left + 1;
                while (right < n && flag[right] == -2) right++;
                builder.append(s, left, right);
                left = right;
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "abcd";
        int[] indices = {0, 2};
        String[] sources = {"a", "cd"};
        String[] targets = {"eee", "ffff"};
        System.out.println(new FindAndReplaceString().findReplaceString(s, indices, sources, targets));
    }
}
