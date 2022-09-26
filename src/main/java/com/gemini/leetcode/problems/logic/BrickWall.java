package com.gemini.leetcode.problems.logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/brick-wall/
 * 模拟
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class BrickWall {

    public int leastBricks(List<List<Integer>> wall) {
        int n = wall.size();
        if (n == 1) return wall.get(0).size() > 1 ? 0 : 1;

        List<Integer> list0 = wall.get(0);
        int sum = sum(list0);

        int[] index = new int[n];
        int[] lengths = new int[n];
        for (int i = 0; i < n; i++) {
            lengths[i] = wall.get(i).get(0);
        }

        int x = 1;
        int maxCount = 0;
        int count, minLen;
        while (x < sum) {
            count = 0;
            minLen = sum;
            for (int i = 0; i < n; i++) {
                if (lengths[i] < x) lengths[i] += wall.get(i).get(++index[i]);
                if (lengths[i] == x) count++;
                if (lengths[i] < minLen) minLen = lengths[i];
            }
            if (count > maxCount) maxCount = count;
            if (count == 0) x = minLen;
            else x++;
        }

        return n - maxCount;
    }

    private int sum(List<Integer> list) {
        int sum = 0;
        for (int i : list) sum += i;
        return sum;
    }

    public static void main(String[] args) {
        List<List<Integer>> wall = new LinkedList<>();
//        wall.add(Arrays.asList(1, 2, 2, 1));
//        wall.add(Arrays.asList(3, 1, 2));
//        wall.add(Arrays.asList(1, 3, 2));
//        wall.add(Arrays.asList(2, 4));
//        wall.add(Arrays.asList(3, 1, 2));
//        wall.add(Arrays.asList(1, 3, 1, 1));
        wall.add(Arrays.asList(1));
        wall.add(Arrays.asList(1));
        wall.add(Arrays.asList(1));
        System.out.println(new BrickWall().leastBricks(wall));
    }
}
