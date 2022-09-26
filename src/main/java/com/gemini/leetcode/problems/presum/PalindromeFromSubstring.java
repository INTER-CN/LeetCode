package com.gemini.leetcode.problems.presum;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/can-make-palindrome-from-substring/
 * 前缀和
 *
 * @Author Gemini
 * 2022-08-27
 **/
public class PalindromeFromSubstring {

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        // 26个字母出现次数的前缀和
        int[][] occurrence = new int[26][n];
        int[] letterSum = new int[26];
        int index;
        for (int i = 0; i < n; i++) {
            index = s.charAt(i) - 'a';
            letterSum[index]++;
            for (int j = 0; j < 26; j++) occurrence[j][i] = letterSum[j];
        }

        List<Boolean> result = new LinkedList<>();
        int oddCount, preSum;
        for (int[] query : queries) {
            if (query[0] == query[1]) {
                result.add(true);
                continue;
            }
            oddCount = 0;
            for (int i = 0; i < 26; i++) {
                preSum = occurrence[i][query[1]] - (query[0] == 0 ? 0 : occurrence[i][query[0] - 1]);
                oddCount += (preSum % 2);
            }
            if ((query[1] - query[0]) % 2 == 1) {
                // 偶数个
                result.add(2 * query[2] >= oddCount);
            } else {
                // 奇数个
                result.add(2 * query[2] >= oddCount - 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "abcda";
        int[][] queries = {
            {3, 3, 0},
            {1, 2, 0},
            {0, 3, 1},
            {0, 3, 2},
            {0, 4, 1}
        };
        List<Boolean> result = new PalindromeFromSubstring().canMakePaliQueries(s, queries);
        System.out.println(new Gson().toJson(result));
    }
}
