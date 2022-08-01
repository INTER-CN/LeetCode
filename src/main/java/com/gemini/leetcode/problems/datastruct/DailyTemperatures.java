package com.gemini.leetcode.problems.datastruct;

import com.google.gson.Gson;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/daily-temperatures/
 * 栈
 *
 * @author 天何
 * @date 2022/7/30
 */
public class DailyTemperatures {

    class StackNode {
        public int index;
        public int value;

        public StackNode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];

        Stack<StackNode> stack = new Stack<>();
        for (int i = 0; i < n - 1; i++) {
            if (temperatures[i] < temperatures[i + 1]) {
                result[i] = 1;
                while (!stack.isEmpty() && stack.peek().value < temperatures[i + 1]) {
                    StackNode node = stack.pop();
                    result[node.index] = i + 1 - node.index;
                }
            } else {
                stack.push(new StackNode(i, temperatures[i]));
            }
        }

        while (!stack.isEmpty()) {
            StackNode node = stack.pop();
            result[node.index] = 0;
        }

        result[n - 1] = 0;

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = new DailyTemperatures().dailyTemperatures(nums);
        System.out.println(new Gson().toJson(result));
    }
}
