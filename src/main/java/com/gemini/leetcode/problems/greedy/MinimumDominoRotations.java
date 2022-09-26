package com.gemini.leetcode.problems.greedy;

/**
 * https://leetcode.cn/problems/minimum-domino-rotations-for-equal-row/
 * 贪心 + 模拟
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class MinimumDominoRotations {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int topResult = minDominoRotations(tops, bottoms, tops[0], true);
        if (tops[0] == bottoms[0]) return topResult;

        int bottomResult = minDominoRotations(tops, bottoms, bottoms[0], false);
        if (topResult < 0) return bottomResult;
        if (bottomResult < 0) return topResult;
        return Math.min(topResult, bottomResult);
    }

    private int minDominoRotations(int[] tops, int[] bottoms, int target, boolean isTop) {
        int topCount = 0;
        int bottomCount = 0;

        int n = tops.length;
        for (int i = 1; i < n; i++) {
            if (tops[i] != target && bottoms[i] != target) return -1;
            if (tops[i] != target) topCount++;
            if (bottoms[i] != target) bottomCount++;
        }

        int initRotation = tops[0] == bottoms[0] ? 0 : 1;

        return isTop ? Math.min(topCount, bottomCount + initRotation) : Math.min(topCount + initRotation, bottomCount);
    }

    public static void main(String[] args) {
        int[] tops = {2, 1, 2, 4, 2, 2};
        int[] bottoms = {5, 2, 6, 2, 3, 2};
        System.out.println(new MinimumDominoRotations().minDominoRotations(tops, bottoms));
    }
}
