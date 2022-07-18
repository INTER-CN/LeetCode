package com.gemini.leetcode.problems.recur;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/
 * 所有子集
 *
 * @author 天何
 * @date 2022/7/17
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, nums.length - 1);
    }

    private List<List<Integer>> subsets(int[] nums, int endIndex) {
        List<List<Integer>> resultList = new ArrayList<>();

        if (endIndex < 0) {
            List<Integer> list = new ArrayList<>();
            resultList.add(list);
            return resultList;
        }

        List<List<Integer>> subsetList = subsets(nums, endIndex - 1);
        for (List<Integer> subList : subsetList) {
            List<Integer> newList = new ArrayList<>();
            newList.addAll(subList);
            newList.add(nums[endIndex]);
            resultList.add(subList);
            resultList.add(newList);
        }

        return resultList;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new Subsets().subsets(nums);
        System.out.println(new Gson().toJson(result));
    }
}
