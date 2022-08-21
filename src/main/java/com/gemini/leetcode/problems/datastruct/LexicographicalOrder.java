package com.gemini.leetcode.problems.datastruct;

/**
 * https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/
 * 字典树
 * 前序遍历，不需要实际构造字典树
 *
 * @Author Gemini
 * 2022-08-19
 **/
public class LexicographicalOrder {

    public int findKthNumber(int n, int k) {
        long num = 1L;
        while (true) {
            if (k == 1) return (int) num;
            int subCount = getSubCount(num, n);
            if (subCount + 1 >= k) {
                num *= 10;
                k--;
            } else {
                num++;
                k -= (subCount + 1);
            }
        }
    }

    private int getSubCount(long num, int n) {
        if (num * 10 > n) return 0;
        long value = num * 10;
        long range = 10;
        int count = 0;

        while (true) {
            if (value + range > n) {
                count += Math.max(n - value + 1, 0);
                break;
            } else {
                count += range;
                value *= 10;
                range *= 10;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new LexicographicalOrder().findKthNumber(681692778, 351251360));
    }
}
