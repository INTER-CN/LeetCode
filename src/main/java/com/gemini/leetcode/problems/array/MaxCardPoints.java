package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/
 * 一维数组线性扫描
 * max points to take, min points to leave
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class MaxCardPoints {

    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        for (int point : cardPoints) sum += point;
        int n = cardPoints.length;
        if (k == n) return sum;

        int remainingSum = 0;
        // [left,right)
        int left = 0, right = n - k;
        for (int i = left; i < right; i++) remainingSum += cardPoints[i];
        int minRemainingSum = remainingSum;
        while (right < n) {
            remainingSum += cardPoints[right++];
            remainingSum -= cardPoints[left++];
            if (remainingSum < minRemainingSum) minRemainingSum = remainingSum;
        }

        return sum - minRemainingSum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        System.out.println(new MaxCardPoints().maxScore(nums, k));
    }
}
