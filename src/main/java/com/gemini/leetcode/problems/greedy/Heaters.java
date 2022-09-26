package com.gemini.leetcode.problems.greedy;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/heaters/
 * 排序 + 贪心
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class Heaters {

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int houseIndex = 0;
        int m = houses.length;
        int heaterIndex = 0;
        int n = heaters.length;
        int distance, maxDistance = 0;
        if (houses[houseIndex] < heaters[heaterIndex]) {
            maxDistance = heaters[heaterIndex] - houses[houseIndex];
            while (houseIndex < m && houses[houseIndex] < heaters[heaterIndex]) houseIndex++;
            if (houseIndex == m) return maxDistance;
        }
        while (heaterIndex + 1 < n && heaters[heaterIndex + 1] <= houses[houseIndex]) heaterIndex++;

        while (houseIndex < m) {
            distance = getNearestHeaterDistance(houses[houseIndex], heaters, heaterIndex, n);
            if (distance > maxDistance) maxDistance = distance;
            houseIndex++;
            if (houseIndex == m) break;
            while (heaterIndex < n - 1 && houses[houseIndex] >= heaters[heaterIndex + 1]) heaterIndex++;
        }

        return maxDistance;
    }

    private int getNearestHeaterDistance(int housePosition, int[] heaters, int heaterIndex, int n) {
        if (heaterIndex == n - 1) return housePosition - heaters[heaterIndex];
        else return Math.min(heaters[heaterIndex + 1] - housePosition, housePosition - heaters[heaterIndex]);
    }

    public static void main(String[] args) {
        int[] houses = {35308228, 158374933, 75260298, 824938981, 595028635, 962408013, 137623865, 997389814, 20739063};
        int[] heaters = {635339425, 654001669, 777724115, 269220094, 34075629, 478446501, 864546517};
        System.out.println(new Heaters().findRadius(houses, heaters));
    }
}
