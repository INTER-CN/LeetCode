package com.gemini.leetcode.problems.tree;

/**
 * https://leetcode.cn/problems/path-sum-iv/
 * 用数组表示二叉树
 *
 * @author 天何
 * @date 2022/7/9
 */
public class PathSum4 {

    public int pathSum(int[] nums) {
        // 构造二叉树
        int[] tree = new int[15];
        for (int i = 0; i < tree.length; i++) tree[i] = -1;
        int level, pos, val;
        for (int num : nums) {
            level = num / 100;
            pos = num % 100 / 10;
            val = num % 10;
            tree[getIndex(level, pos)] = val;
        }

        // 叶节点只计算一次，非叶节点计算的次数取决于其下叶节点的个数

        // 求路径和
        int sum = 0;
        for (int i = 0; i < 15; i++) sum += getOnePathSum(tree, i);
        return sum;
    }

    private int getOnePathSum(int[] tree, int index) {
        // 空节点
        if (tree[index] < 0) return 0;

        // 非叶节点
        if (index < 7 && (tree[2 * index + 1] >= 0 || tree[2 * index + 2] >= 0)) return 0;

        int sum = 0;
        while (index >= 0) {
            sum += tree[index];
            if (index == 0) break;
            index = (index - 1) / 2;
        }

        return sum;
    }

    private int getIndex(int level, int pos) {
        switch (level) {
            case 1:
                return 0;
            case 2:
                switch (pos) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                }
            case 3:
                switch (pos) {
                    case 1:
                        return 3;
                    case 2:
                        return 4;
                    case 3:
                        return 5;
                    case 4:
                        return 6;
                }
            case 4:
                switch (pos) {
                    case 1:
                        return 7;
                    case 2:
                        return 8;
                    case 3:
                        return 9;
                    case 4:
                        return 10;
                    case 5:
                        return 11;
                    case 6:
                        return 12;
                    case 7:
                        return 13;
                    case 8:
                        return 14;
                }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {113, 215, 221};
        System.out.println(new PathSum4().pathSum(nums));
    }
}
