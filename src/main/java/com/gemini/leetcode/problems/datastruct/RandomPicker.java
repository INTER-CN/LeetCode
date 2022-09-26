package com.gemini.leetcode.problems.datastruct;

/**
 * https://leetcode.cn/problems/random-pick-with-weight/
 * 二分法
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class RandomPicker {

    private int[] v;
    private int n;
    private int sum;

    public RandomPicker(int[] w) {
        this.n = w.length;
        this.sum = 0;
        this.v = new int[this.n];
        for (int i = 0; i < this.n; i++) {
            this.sum += w[i];
            this.v[i] = this.sum;
        }
    }

    public int pickIndex() {
        double ratio = Math.random();
        int target = (int) (ratio * sum);
        int left = 0, right = n - 1;
        while (true) {
            if (left == right) return left;
            if (left + 1 == right) {
                if (target >= v[left]) return right;
                else return left;
            }

            int mid = left + (right - left) / 2;
            if (v[mid - 1] <= target && target < v[mid]) return mid;
            else if (target >= v[mid]) left = mid + 1;
            else right = mid - 1;
        }
    }

    public static void main(String[] args) {
        int[] w = {3, 14, 1, 7};
        RandomPicker randomPicker = new RandomPicker(w);
        for (int i = 0; i < randomPicker.sum * 2; i++) System.out.print(randomPicker.pickIndex() + ",");
    }
}
