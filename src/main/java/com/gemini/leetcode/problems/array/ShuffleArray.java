package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

import java.util.Random;

/**
 * https://leetcode.cn/problems/shuffle-an-array/
 * 随机排列
 *
 * @Author Gemini
 * 2022-09-15
 **/
public class ShuffleArray {

    private int[] nums;
    private Random random;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int n = nums.length;
        int[] result = new int[n];
        int index = 0;

        boolean[] flag = new boolean[n];
        int k;
        for (int i = n; i >= 1; i--) {
            int r = random.nextInt(i);
            k = -1;
            for (int j = 0; j <= r; j++) {
                k++;
                while (k < n && flag[k]) k++;
            }
            result[index++] = nums[k];
            flag[k] = true;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        ShuffleArray shuffleArray = new ShuffleArray(nums);
        Gson gson = new Gson();
        for (int i = 0; i < 6; i++) {
            int[] shuffle = shuffleArray.shuffle();
            System.out.println(gson.toJson(shuffle));
        }
    }
}
