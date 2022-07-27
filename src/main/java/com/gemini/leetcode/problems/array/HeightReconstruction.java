package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/queue-reconstruction-by-height/
 * 数组排序
 *
 * @author 天何
 * @date 2022/7/25
 */
public class HeightReconstruction {

    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;

        // 按高度排序
        sortByHeight(people, 0, n - 1);

        // 依序重新填充数组，相等的值算作遮挡
        int[][] result = new int[n][2];
        boolean[] flag = new boolean[n];

        for (int[] person : people) {
            // 向右移动[1]个未被占用或者值相等的位置
            int index = 0;
            while (!blockingSpot(result, flag, index, person[0])) index++;
            for (int i = 0; i < person[1]; i++) {
                index++;
                while (!blockingSpot(result, flag, index, person[0])) index++;
            }
            result[index] = person;
            flag[index] = true;
        }

        return result;
    }

    private void sortByHeight(int[][] people, int start, int end) {
        if (start >= end) return;
        int left = start, right = end;
        while (left < right) {
            while (left < right && compare(people, left, right) < 0) right--;
            if (left == right) break;
            swap(people, left, right);
            while (left < right && compare(people, left, right) < 0) left++;
            if (left == right) break;
            swap(people, left, right);
        }
        sortByHeight(people, start, left - 1);
        sortByHeight(people, left + 1, end);
    }

    private int compare(int[][] people, int i, int j) {
        if (people[i][0] < people[j][0]) return -1;
        if (people[i][0] == people[j][0] && people[i][1] < people[j][1]) return -1;
        return 1;
    }

    private void swap(int[][] people, int i, int j) {
        int[] temp = people[i];
        people[i] = people[j];
        people[j] = temp;
    }

    private boolean blockingSpot(int[][] result, boolean[] flag, int index, int value) {
        if (flag[index] == false) return true;
        if (result[index][0] == value) return true;
        return false;
    }

    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] result = new HeightReconstruction().reconstructQueue(people);
        System.out.println(new Gson().toJson(result));
    }
}
