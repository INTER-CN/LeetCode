package com.gemini.leetcode.problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/fruit-into-baskets/
 * longest sub array with at most two distinct values
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class FruitIntoBaskets {

    private static final int BASKET_NUM = 2;

    public int totalFruit(int[] fruits) {
        int usedCount = 0, fruitCount;
        int n = fruits.length;
        // [left,right)
        int left = 0, right = 0;
        Map<Integer, Integer> map = new HashMap<>();

        int maxLength = 1;
        while (right < n) {
            while (right < n && (usedCount < BASKET_NUM || map.getOrDefault(fruits[right], 0) > 0)) {
                fruitCount = map.getOrDefault(fruits[right], 0);
                map.put(fruits[right], ++fruitCount);
                if (fruitCount == 1) usedCount++;
                right++;
            }

            if (right - left > maxLength) maxLength = right - left;
            if (right == n) break;

            while (left <= right && usedCount == BASKET_NUM) {
                fruitCount = map.get(fruits[left]);
                map.put(fruits[left], --fruitCount);
                if (fruitCount == 0) usedCount--;
                left++;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 2};
        System.out.println(new FruitIntoBaskets().totalFruit(nums));
    }
}
