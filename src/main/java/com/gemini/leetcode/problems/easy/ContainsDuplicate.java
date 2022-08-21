package com.gemini.leetcode.problems.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/contains-duplicate/
 * 哈希表水题
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }
}
