package com.gemini.leetcode.problems.datastruct;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/
 * 栈（不对）
 * 动态规划（太复杂）
 * 栈，双向扫描（AC）
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class LongestValidParentheses {

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    public int longestValidParentheses(String s) {
        if (s.length() == 0) return 0;

        int leftCount = 0;
        // [left,right)
        int left = 0, right = 0;
        int max = 0, len;
        List<int[]> segmentList = new LinkedList<>();
        while (right < s.length()) {
            if (s.charAt(right) == '(') {
                leftCount++;
                right++;
            } else if (leftCount == 0) {
                segmentList.add(new int[]{left, right});
                left = right + 1;
                right = left;
            } else {
                leftCount--;
                right++;
                if (leftCount == 0) {
                    segmentList.add(new int[]{left, right});
                    left = right;
                }
            }
        }
        if (leftCount > 0) {
            segmentList.add(new int[]{left + leftCount, right});
        }

        // merge segments
        if (segmentList.size() == 0) return 0;

        left = segmentList.get(0)[LEFT];
        right = segmentList.get(0)[RIGHT];
        max = right - left;
        for (int i = 1; i < segmentList.size(); i++) {
            int[] segment = segmentList.get(i);
            if (segment[LEFT] == right) {
                right = segment[RIGHT];
            } else {
                left = segment[LEFT];
                right = segment[RIGHT];
            }
            len = right - left;
            if (len > max) max = len;
        }

        return max;
    }

    public int longestValidParentheses2(String s) {
        if (s.length() == 0) return 0;

        int n = s.length();
        int[] dp = new int[n];

        int leftCount = 0;
        // [left,right)
        int left = 0, right = 0;
        int max = 0;
        while (right < n) {
            if (s.charAt(right) == '(') {
                leftCount++;
                right++;
            } else if (leftCount == 0) {
                left = right + 1;
                right = left;
            } else {
                leftCount--;
                dp[right] = right + 1 - left - leftCount;
                int preIndex = right - dp[right];
                if (preIndex >= 0 && dp[preIndex] > 0) dp[right] += dp[preIndex];
                if (dp[right] > max) max = dp[right];
                right++;
            }
        }

        return max;
    }

    public int longestValidParentheses3(String s) {
        if (s.length() == 0) return 0;

        int n = s.length();

        int leftCount = 0;
        // from left to right [left,right)
        int left = 0, right = 0;
        int max = 0, len;
        while (right < n) {
            if (s.charAt(right) == '(') {
                leftCount++;
                right++;
            } else if (leftCount == 0) {
                right++;
                left = right;
            } else {
                leftCount--;
                right++;
                if (leftCount == 0) {
                    len = right - left;
                    if (len > max) max = len;
                }
            }
        }
        // from right to left (left,right]
        left = n - 1;
        right = n - 1;
        int rightCount = 0;
        while (left >= 0) {
            if (s.charAt(left) == ')') {
                rightCount++;
                left--;
            } else if (rightCount == 0) {
                left--;
                right = left;
            } else {
                rightCount--;
                left--;
                if (rightCount == 0) {
                    len = right - left;
                    if (len > max) max = len;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String s = "()((())";
        System.out.println(new LongestValidParentheses().longestValidParentheses3(s));
    }
}
