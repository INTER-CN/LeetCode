package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/longest-common-subsequence-between-sorted-arrays/
 * 数组归并
 *
 * @author 天何
 * @date 2022/7/9
 */
public class LongestCommonSubsequence2 {

    public List<Integer> longestCommonSubsequence(int[][] arrays) {
        int[] lcs = longestCommonSubsequence(arrays[0], arrays[1]);
        for (int i = 2; i < arrays.length; i++) {
            if (lcs == null || lcs.length == 0) return Collections.emptyList();
            lcs = longestCommonSubsequence(lcs, arrays[i]);
        }
        return Arrays.stream(lcs).boxed().collect(Collectors.toList());
    }

    private int[] longestCommonSubsequence(int[] array1, int[] array2) {
        int i = 0, j = 0, m = array1.length, n = array2.length;

        ArrayList<Integer> commonList = new ArrayList<>();
        while (i < m && j < n) {
            if (array1[i] < array2[j]) {
                while (i < m && array1[i] < array2[j]) i++;
                if (i == m) break;
            } else if (array1[i] > array2[j]) {
                while (j < n && array1[i] > array2[j]) j++;
                if (j == n) break;
            }
            if (array1[i] == array2[j]) {
                commonList.add(array1[i]);
                i++;
                j++;
            }
        }

        int[] lcs = new int[commonList.size()];
        for (int index = 0; index < commonList.size(); index++) lcs[index] = commonList.get(index);
        return lcs;
    }

    public static void main(String[] args) {
        int[][] arrays = {
            {2, 3, 6, 8},
            {1, 2, 3, 5, 6, 7, 10},
            {2, 3, 4, 6, 9}
        };
        List<Integer> result = new LongestCommonSubsequence2().longestCommonSubsequence(arrays);
        System.out.println(new Gson().toJson(result));
    }
}
