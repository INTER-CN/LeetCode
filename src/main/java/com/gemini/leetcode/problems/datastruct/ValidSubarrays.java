package com.gemini.leetcode.problems.datastruct;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/number-of-valid-subarrays/
 * 单调栈
 * 每完成一次入栈操作，栈的size代表对应的有效子数组个数
 * 每个有效子数组从栈的每个元素开始，一直到栈顶（中间包含已出栈的元素）
 *
 * @Author Gemini
 * 2022-08-19
 **/
public class ValidSubarrays {

    public int validSubarrays(int[] nums) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() > num) stack.pop();
            stack.push(num);
            count += stack.size();
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 5, 3};
        System.out.println(new ValidSubarrays().validSubarrays(nums));
    }
}
