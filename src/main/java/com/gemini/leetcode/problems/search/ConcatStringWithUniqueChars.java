package com.gemini.leetcode.problems.search;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 * DFS + 剪枝
 *
 * @Author Gemini
 * 2022-09-02
 **/
public class ConcatStringWithUniqueChars {

    private int max;

    public int maxLength(List<String> list) {
        max = 0;
        boolean[] flag = new boolean[26];
        calculateMaxLength(flag, 0, list, 0);

        return max;
    }

    private void calculateMaxLength(boolean[] preFlag, int preLength, List<String> list, int index) {
        if (index == list.size()) return;

        // with this item
        boolean[] currentFlag = uniqueCharFlag(list.get(index));
        if (currentFlag != null) {
            boolean[] mergedFlag = mergeCharFlag(preFlag, currentFlag);
            if (mergedFlag != null) {
                int newLength = preLength + list.get(index).length();
                if (newLength > max) max = newLength;
                calculateMaxLength(mergedFlag, newLength, list, index + 1);
            }
        }

        // without this item
        calculateMaxLength(preFlag, preLength, list, index + 1);
    }

    private boolean[] uniqueCharFlag(String s) {
        boolean[] flag = new boolean[26];
        int index;
        for (int i = 0; i < s.length(); i++) {
            index = s.charAt(i) - 'a';
            if (flag[index]) return null;
            flag[index] = true;
        }
        return flag;
    }

    private boolean[] mergeCharFlag(boolean[] flag1, boolean[] flag2) {
        boolean[] result = new boolean[26];
        for (int i = 0; i < 26; i++) {
            if (flag1[i] && flag2[i]) return null;
            result[i] = flag1[i] || flag2[i];
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("ab", "cd", "cde", "cdef", "efg", "fgh", "abxyz");
        System.out.println(new ConcatStringWithUniqueChars().maxLength(list));
    }
}
