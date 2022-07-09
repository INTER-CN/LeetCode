package com.gemini.leetcode.problems;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/word-break/
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/8
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return true;

        int n = s.length();

        // dp[i]: s[0..i)是否能拆分
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        int startIndex;
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                if (word.length() <= i) {
                    startIndex = i - word.length();
                    if (s.substring(startIndex).startsWith(word) && dp[startIndex]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(new WordBreak().wordBreak(s, wordDict));
    }
}
