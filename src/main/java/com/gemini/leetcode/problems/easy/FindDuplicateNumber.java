package com.gemini.leetcode.problems.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/find-the-duplicate-number/
 * 当水题来刷，O(n)空间
 *
 * @author 天何
 * @date 2022/7/9
 */
public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return num;
            set.add(num);
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(new FindDuplicateNumber().findDuplicate(nums));
    }
}
