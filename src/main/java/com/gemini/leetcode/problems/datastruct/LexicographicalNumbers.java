package com.gemini.leetcode.problems.datastruct;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/lexicographical-numbers/
 * 字典树
 *
 * @Author Gemini
 * 2022-09-05
 **/
public class LexicographicalNumbers {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new LinkedList<>();

        for (int i = 1; i <= 9; i++) preOrder(result, i, n);

        return result;
    }

    private void preOrder(List<Integer> result, int num, int n) {
        if (num > n) return;
        result.add(num);
        for (int i = 0; i <= 9; i++) preOrder(result, num * 10 + i, n);
    }

    public static void main(String[] args) {
        List<Integer> result = new LexicographicalNumbers().lexicalOrder(13);
        System.out.println(result);
    }
}
