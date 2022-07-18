package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/product-of-array-except-self/
 * 线性动态规划
 *
 * @author 天何
 * @date 2022/7/18
 */
public class ProductOfAllExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] result = new int[n];
        for (int i = 0; i < n; ++i) result[i] = 1;

        // 从左向右
        int product = 1;
        for (int i = 0; i < n; ++i) {
            result[i] *= product;
            product *= nums[i];
        }

        // 从右向左
        product = 1;
        for (int i = n - 1; i >= 0; --i) {
            result[i] *= product;
            product *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] result = new ProductOfAllExceptSelf().productExceptSelf(nums);
        System.out.println(new Gson().toJson(result));
    }
}
