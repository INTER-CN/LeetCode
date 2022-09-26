package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/majority-element/
 * 线性扫描
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class MajorityElement {

    public int majorityElement(int[] nums) {
        int major = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == major) {
                count++;
            } else if (count == 0) {
                major = nums[i];
                count = 1;
            } else {
                count--;
            }
        }

        return major;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(new MajorityElement().majorityElement(nums));
    }
}
