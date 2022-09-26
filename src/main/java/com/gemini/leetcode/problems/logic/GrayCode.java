package com.gemini.leetcode.problems.logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/gray-code/
 * 二进制 + 递归
 *
 * @Author Gemini
 * 2022-09-19
 **/
public class GrayCode {

    public List<Integer> grayCode(int n) {
        if (n == 1) return Arrays.asList(0, 1);
        if (n == 2) return Arrays.asList(0, 1, 3, 2);

        List<Integer> subList = grayCode(n - 1);
        List<Integer> result = new LinkedList<>();

        result.addAll(subList);
        int x = (int) Math.pow(2, n - 1);
        for (int i = subList.size() - 1; i >= 0; i--) {
            result.add(subList.get(i) + x);
        }

        return result;
    }
}
