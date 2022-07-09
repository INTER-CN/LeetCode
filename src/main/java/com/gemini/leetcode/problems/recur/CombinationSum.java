package com.gemini.leetcode.problems.recur;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum/
 * 递归
 *
 * @author 天何
 * @date 2022/7/5
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSum(candidates, candidates.length - 1, target);
    }

    private List<List<Integer>> combinationSum(int[] candidates, int index, int target) {
        if (index == 0) {
            if (target % candidates[0] == 0) {
                int count = target / candidates[0];
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < count; i++) list.add(candidates[0]);
                return Arrays.asList(list);
            } else {
                return Collections.emptyList();
            }
        }

        // 当前值使用0-n次
        int n = target / candidates[index];
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            List<List<Integer>> subList = combinationSum(candidates, index - 1, target - candidates[index] * i);
            for (List<Integer> subListItem : subList) {
                for (int j = 0; j < i; j++) subListItem.add(candidates[index]);
                result.add(subListItem);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = new CombinationSum().combinationSum(candidates, target);
        System.out.println(new Gson().toJson(result));
    }
}
