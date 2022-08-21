package com.gemini.leetcode.problems.array;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/largest-number/
 * 数组排序
 * 雷同的题目：
 * https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 *
 * @Author Gemini
 * 2022-08-09
 **/
public class LargestNumber {

    public String largestNumber(int[] nums) {
        Integer[] ns = new Integer[nums.length];
        for (int i = 0; i < ns.length; i++) ns[i] = nums[i];

        Arrays.sort(ns, (n1, n2) -> {
            String s1 = String.format("%d%d", n1, n2);
            String s2 = String.format("%d%d", n2, n1);
            int i = 0;
            while (i < s1.length()) {
                if (s1.charAt(i) < s2.charAt(i)) return 1;
                else if (s1.charAt(i) > s2.charAt(i)) return -1;
                i++;
            }

            return 0;
        });

        StringBuilder builder = new StringBuilder();
        for (Integer n : ns) {
            builder.append(n);
        }

        String s = builder.toString();
        if (s.startsWith("0")) return "0";

        return s;
    }

    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(new LargestNumber().largestNumber(nums));
    }
}
