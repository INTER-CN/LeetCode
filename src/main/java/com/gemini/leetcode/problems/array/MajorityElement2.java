package com.gemini.leetcode.problems.array;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/majority-element-ii/
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class MajorityElement2 {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new LinkedList<>();

        int n = nums.length;
        if (n < 3) {
            result.add(nums[0]);
            if (n == 2 && nums[1] != nums[0]) result.add(nums[1]);
            return result;
        }

        int x1 = Integer.MIN_VALUE, c1 = 0;
        int x2 = Integer.MIN_VALUE, c2 = 0;
        for (int num : nums) {
            if (c1 > 0 && x1 == num) {
                c1++;
            } else if (c2 > 0 && x2 == num) {
                c2++;
            } else if (c1 == 0) {
                x1 = num;
                c1 = 1;
            } else if (c2 == 0) {
                x2 = num;
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }

        if (c1 > 0) {
            c1 = 0;
            for (int num : nums) if (num == x1) c1++;
            if (c1 > n / 3) result.add(x1);
        }

        if (c2 > 0) {
            c2 = 0;
            for (int num : nums) if (num == x2) c2++;
            if (c2 > n / 3) result.add(x2);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 3, 4, 4, 3, 2, 1, 4};
        System.out.println(new MajorityElement2().majorityElement(nums));
    }
}
