package com.gemini.leetcode.problems.hash;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/group-anagrams/
 * 哈希表
 *
 * @author 天何
 * @date 2022/7/17
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String countString = getLetterCountString(str);
            if (map.containsKey(countString)) {
                map.get(countString).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(countString, list);
            }
        }

        return map.values().stream().collect(Collectors.toList());
    }

    private String getLetterCountString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count;
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                count = map.get(ch);
                map.put(ch, count + 1);
            } else {
                map.put(ch, 1);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            builder.append(map.getOrDefault(ch, 0));
            builder.append(",");
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = new GroupAnagrams().groupAnagrams(strs);
        System.out.println(new Gson().toJson(result));
    }
}
