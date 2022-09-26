package com.gemini.leetcode.problems.presum;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/corporate-flight-bookings/
 * 差分数组
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class CorporateFlightBookings {

    /**
     * 暴力模拟（AC）
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];

        for (int[] booking : bookings) {
            for (int i = booking[0] - 1; i < booking[1]; i++) {
                result[i] += booking[2];
            }
        }

        return result;
    }

    /**
     * 差分数组
     */
    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] result = new int[n];
        int[] diff = new int[n];

        int start, end, value;
        for (int[] booking : bookings) {
            start = booking[0];
            end = booking[1];
            value = booking[2];
            diff[start - 1] += value;
            if (end < n) diff[end] -= value;
        }

        result[0] = diff[0];
        for (int i = 1; i < n; i++) result[i] = result[i - 1] + diff[i];

        return result;
    }

    public static void main(String[] args) {
        int[][] bookings = {
            {1, 2, 10},
            {2, 3, 20},
            {2, 5, 25}
        };
        int n = 5;
        System.out.println(new Gson().toJson(new CorporateFlightBookings().corpFlightBookings2(bookings, n)));
    }
}
