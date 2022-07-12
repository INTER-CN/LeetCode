package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/longest-common-prefix/
 * 字符串水题
 *
 * @author 天何
 * @date 2022/7/9
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[0].charAt(i) != strs[j].charAt(i)) return builder.toString();
            }
            builder.append(strs[0].charAt(i));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
    }
}
