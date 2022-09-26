package com.gemini.leetcode.problems.greedy;

import com.gemini.leetcode.problems.greedy.model.Relation;

/**
 * https://leetcode.cn/problems/find-the-celebrity/
 * 贪心搜索
 *
 * @Author Gemini
 * 2022-08-24
 **/
public class FindTheCelebrity extends Relation {

    public int findCelebrity(int n) {
        int a = 0, b = 1;
        while (b < n) {
            if (knows(a, b)) a = b;
            b++;
        }

        for (int i = 0; i < n; i++) {
            if (i == a) continue;
            if (knows(a, i) || !knows(i, a)) return -1;
        }

        return a;
    }
}
