package com.gemini.leetcode.problems.array;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/minimum-deletions-to-make-character-frequencies-unique/
 * 数组线性扫描
 *
 * @Author Gemini
 * 2022-09-02
 **/
public class MinimumDeletions {

    public int minDeletions(String s) {
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) nums[s.charAt(i) - 'a']++;

        Arrays.sort(nums);

        int count = 0;
        int deletions;
        for (int i = 25; i > 0; i--) {
            if (nums[i] > nums[i - 1]) continue;
            deletions = nums[i] == 0 ? nums[i - 1] : (nums[i - 1] - (nums[i] - 1));
            count += deletions;
            nums[i - 1] -= deletions;
        }

        return count;
    }

    public static void main(String[] args) {
        String s = "aab";
        System.out.println(new MinimumDeletions().minDeletions(s));
    }
}
