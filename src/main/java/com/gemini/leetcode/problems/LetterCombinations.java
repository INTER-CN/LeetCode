package com.gemini.leetcode.problems;

import com.google.gson.Gson;

import java.util.*;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * 递归
 *
 * @author 天何
 * @date 2022/6/30
 */
public class LetterCombinations {

    private static final Map<Character, List<String>> DICT = new HashMap<Character, List<String>>() {{
        put('2', Arrays.asList("a", "b", "c"));
        put('3', Arrays.asList("d", "e", "f"));
        put('4', Arrays.asList("g", "h", "i"));
        put('5', Arrays.asList("j", "k", "l"));
        put('6', Arrays.asList("m", "n", "o"));
        put('7', Arrays.asList("p", "q", "r", "s"));
        put('8', Arrays.asList("t", "u", "v"));
        put('9', Arrays.asList("w", "x", "y", "z"));
    }};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        char[] digitArray = digits.toCharArray();

        return letterCombinations(digitArray, digitArray.length - 1);
    }

    private List<String> letterCombinations(char[] digitArray, int endIndex) {
        if (endIndex == 0) {
            return DICT.get(digitArray[0]);
        }

        List<String> subList = letterCombinations(digitArray, endIndex - 1);
        List<String> suffixList = DICT.get(digitArray[endIndex]);

        List<String> resultList = new ArrayList<>();
        for (String subItem : subList) {
            for (String suffix : suffixList) {
                resultList.add(subItem + suffix);
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        String digits = "22";
        List<String> result = new LetterCombinations().letterCombinations(digits);
        System.out.println(new Gson().toJson(result));
    }
}
