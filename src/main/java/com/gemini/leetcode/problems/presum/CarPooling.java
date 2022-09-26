package com.gemini.leetcode.problems.presum;

/**
 * https://leetcode.cn/problems/car-pooling/
 * 差分数组
 *
 * @Author Gemini
 * 2022-08-27
 **/
public class CarPooling {

    private static final int MAX_LOCATION = 1000;

    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[MAX_LOCATION + 1];
        int max = 0;
        int num, start, end;
        for (int[] trip : trips) {
            num = trip[0];
            start = trip[1];
            end = trip[2];
            diff[start] += num;
            if (end < diff.length) diff[end] -= num;
            if (end > max) max = end;
        }

        int passengers = diff[0];
        if (passengers > capacity) return false;
        for (int i = 1; i <= max; i++) {
            passengers += diff[i];
            if (passengers > capacity) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] trips = {
            {2, 1, 5},
            {3, 5, 7}
        };
        System.out.println(new CarPooling().carPooling(trips, 3));
    }
}
