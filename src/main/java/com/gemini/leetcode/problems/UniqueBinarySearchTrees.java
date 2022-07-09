package com.gemini.leetcode.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/unique-binary-search-trees/
 * 二叉搜索树
 * 计算公式：f(n) = f(n-1)*f(0) + f(n-2)*f(1) + f(n-3)*f(2) + ... + f(1)*f(n-2) + f(0)*f(n-1)
 * 算出来直接存结果
 *
 * @author 天何
 * @date 2022/7/7
 */
public class UniqueBinarySearchTrees {

    public static final Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>() {{
        put(1, 1);
        put(2, 2);
        put(3, 5);
        put(4, 14);
        put(5, 42);
        put(6, 132);
        put(7, 429);
        put(8, 1430);
        put(9, 4862);
        put(10, 16796);
        put(11, 58786);
        put(12, 208012);
        put(13, 742900);
        put(14, 2674440);
        put(15, 9694845);
        put(16, 35357670);
        put(17, 129644790);
        put(18, 477638700);
        put(19, 1767263190);
    }};

    public int numTrees(int n) {
        return resultMap.get(n);
    }

    public void numTreesFunc() {
        final int max = 19;
        int[] result = new int[max + 1];
        result[0] = 1;
        result[1] = 1;
        result[2] = 2;
        for (int n = 3; n <= max; n++) {
            result[n] = 0;
            for (int i = 0; i < n; i++) result[n] += result[n - 1 - i] * result[i];
        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(i + " >> " + result[i]);
        }
    }

    public static void main(String[] args) {
        new UniqueBinarySearchTrees().numTreesFunc();
    }
}
