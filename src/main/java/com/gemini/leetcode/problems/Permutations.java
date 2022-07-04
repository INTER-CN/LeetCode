package com.gemini.leetcode.problems;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations/
 * 全排列、递归
 *
 * @author 天何
 * @date 2022/7/4
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        return permute(nums, nums.length - 1);
    }

    private List<List<Integer>> permute(int[] nums, int index) {
        if (index == 0) {
            List<List<Integer>> list = new ArrayList<>();
            list.add(Arrays.asList(nums[0]));
            return list;
        }

        List<List<Integer>> subList = permute(nums, index - 1);
        List<List<Integer>> list = new ArrayList<>();
        for (List<Integer> subListItem : subList) {
            for (int i = 0; i <= index; i++) {
                List<Integer> listItem = new ArrayList<>();
                listItem.addAll(subListItem.subList(0, i));
                listItem.add(nums[index]);
                listItem.addAll(subListItem.subList(i, subListItem.size()));
                list.add(listItem);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Gson().toJson(new Permutations().permute(nums)));
    }
}
