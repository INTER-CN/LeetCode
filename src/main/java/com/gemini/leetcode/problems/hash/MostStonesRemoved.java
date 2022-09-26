package com.gemini.leetcode.problems.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column/
 * 图的遍历
 * 用集合来记录节点坐标
 *
 * @Author Gemini
 * 2022-08-28
 **/
public class MostStonesRemoved {

    public int removeStones(int[][] stones) {
        int n = stones.length;

        boolean[] flag = new boolean[n];
        int count = 0;
        int flagCount = 0;
        int index;
        while (flagCount < n) {
            count++;

            // find a stone
            index = 0;
            while (flag[index]) index++;

            // record stone location
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> colSet = new HashSet<>();
            rowSet.add(stones[index][0]);
            colSet.add(stones[index][1]);
            flag[index] = true;
            flagCount++;

            // search stones until no more is searched
            boolean searched = true;
            while (searched) {
                searched = false;
                for (int i = 0; i < n; i++) {
                    if (flag[i]) continue;
                    if (rowSet.contains(stones[i][0]) || colSet.contains(stones[i][1])) {
                        searched = true;
                        flag[i] = true;
                        rowSet.add(stones[i][0]);
                        colSet.add(stones[i][1]);
                        flagCount++;
                    }
                }
            }
        }

        return n - count;
    }

    public static void main(String[] args) {
        int[][] stones = {
            {0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}
        };
        System.out.println(new MostStonesRemoved().removeStones(stones));
    }
}
