package com.gemini.leetcode.problems.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 哈希表
 *
 * @author 天何
 * @date 2022/7/15
 */
public class SubstringNoRepeatingChar {

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        int n = s.length();
        if (n < 2) return n;

        int head = 0, tail = 0;
        // 字符出现的次数 s[head..tail)
        Map<Character, Integer> map = new HashMap<>();

        int max = 1;

        while (true) {
            // tail后移直到出现重复字符
            while (tail < n && !map.containsKey(s.charAt(tail))) {
                map.put(s.charAt(tail), 1);
                tail++;
            }
            max = Math.max(max, tail - head);
            if (tail == n) break;

            map.put(s.charAt(tail++), 2);

            // head后移直到没有重复字符
            while (true) {
                Integer count = map.get(s.charAt(head));
                if (count == 1) {
                    map.remove(s.charAt(head));
                    head++;
                } else {
                    // count == 2
                    map.put(s.charAt(head), 1);
                    head++;
                    break;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new SubstringNoRepeatingChar().lengthOfLongestSubstring("xxxx"));
    }
}
