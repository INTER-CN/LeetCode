package com.gemini.leetcode.problems.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/power-of-three/
 * 预计算
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class PowerOfThree {

    private static final Set<Integer> POWER_SET = new HashSet<Integer>() {{
        add(1);
        add(3);
        add(9);
        add(27);
        add(81);
        add(243);
        add(729);
        add(2187);
        add(6561);
        add(19683);
        add(59049);
        add(177147);
        add(531441);
        add(1594323);
        add(4782969);
        add(14348907);
        add(43046721);
        add(129140163);
        add(387420489);
        add(1162261467);
    }};

    public boolean isPowerOfThree(int n) {
        return POWER_SET.contains(n);
    }
}
