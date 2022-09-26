package com.gemini.leetcode.problems.greedy;

/**
 * https://leetcode.cn/problems/gas-station/
 * 贪心
 *
 * @Author Gemini
 * 2022-09-13
 **/
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int index = 0;
        int n = gas.length;

        int startIndex = 0, diff;
        while (startIndex <= index) {
            startIndex = index;
            diff = 0;
            while (diff >= 0) {
                diff += (gas[index] - cost[index]);
                index++;
                if (index == n) index = 0;
                if (index == startIndex) {
                    if (diff >= 0) {
                        return index;
                    } else {
                        return -1;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        System.out.println(new GasStation().canCompleteCircuit(gas, cost));
    }
}
