package com.gemini.leetcode.problems.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/
 * 哈希表
 *
 * @author 天何
 * @date 2022/7/17
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums == null) return 0;

        int n = nums.length;

        if (n < 2) return n;

        boolean[] flag = new boolean[n];
        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (indexMap.containsKey(nums[i])) {
                indexMap.get(nums[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                indexMap.put(nums[i], list);
            }
        }

        int max = 1, count, num;

        for (int i = 0; i < n; i++) {
            if (flag[i]) continue;

            count = 1;

            // 向左扫描
            num = nums[i] - 1;
            while (indexMap.containsKey(num)) {
                ++count;
                List<Integer> list = indexMap.get(num);
                for (int index : list) flag[index] = true;
                --num;
            }

            // 向右扫描
            num = nums[i] + 1;
            while (indexMap.containsKey(num)) {
                ++count;
                List<Integer> list = indexMap.get(num);
                for (int index : list) flag[index] = true;
                ++num;
            }

            if (count > max) max = count;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(nums));
    }
}
