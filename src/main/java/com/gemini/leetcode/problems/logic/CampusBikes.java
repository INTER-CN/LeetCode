package com.gemini.leetcode.problems.logic;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.cn/problems/campus-bikes/
 * 模拟
 *
 * @Author Gemini
 * 2022-08-27
 **/
public class CampusBikes {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;

        int[] result = new int[n];
        boolean[] workerFlag = new boolean[n];
        boolean[] bikeFlag = new boolean[m];

        // compute and sort all pairs by distance
        Map<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int distance = distance(workers[i], bikes[j]);
                List<int[]> list = map.computeIfAbsent(distance, k -> new LinkedList<>());
                list.add(new int[]{i, j});
            }
        }

        // traverse the map to get result
        int count = 0;
        int workerIndex, bikeIndex;
        for (List<int[]> list : map.values()) {
            for (int[] pair : list) {
                workerIndex = pair[0];
                bikeIndex = pair[1];
                if (workerFlag[workerIndex] || bikeFlag[bikeIndex]) continue;
                result[workerIndex] = bikeIndex;
                workerFlag[workerIndex] = true;
                bikeFlag[bikeIndex] = true;
                count++;
                if (count == n) return result;
            }
        }

        return result;
    }

    private int distance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }

    public static void main(String[] args) {
        int[][] workers = {
            {0, 0},
            {1, 1},
            {2, 0}
        };
        int[][] bikes = {
            {1, 0},
            {2, 2},
            {2, 1}
        };
        int[] result = new CampusBikes().assignBikes(workers, bikes);
        System.out.println(new Gson().toJson(result));
    }
}
