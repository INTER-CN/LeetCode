package com.gemini.leetcode.problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/two-sum/
 * 哈希表
 *
 * @author 天何
 * @date 2022/7/7
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // 数值与下标的映射关系
        Map<Integer, Integer> map = new HashMap<>();

        map.put(nums[0], 0);

        int required;
        for (int i = 1; i < nums.length; i++) {
            required = target - nums[i];
            if (map.containsKey(required)) {
                int[] result = new int[2];
                result[0] = map.get(required);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = new TwoSum().twoSum(nums, target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
