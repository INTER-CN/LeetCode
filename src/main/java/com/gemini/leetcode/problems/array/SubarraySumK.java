package com.gemini.leetcode.problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为 K 的子数组
 * https://leetcode.cn/problems/subarray-sum-equals-k/
 * 前缀和 + 哈希表
 *
 * @author 天何
 * @date 2022/7/7
 */
public class SubarraySumK {

    public int subarraySum(int[] nums, int k) {
        // 前缀和 与其出现的次数 之间的映射
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, 1);

        int pre;
        int count = 0;
        for (int num : nums) {
            sum += num;
            pre = sum - k;
            if (map.containsKey(pre)) {
                count += map.get(pre);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 7, 2, -3, 1, 4, 2};
        int k = 7;
        System.out.println(new SubarraySumK().subarraySum(nums, k));
    }
}
