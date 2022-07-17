package com.gemini.leetcode.problems.recur;

import com.google.gson.Gson;

import java.util.*;

/**
 * https://leetcode.cn/problems/generate-parentheses/
 * 递归
 *
 * @author 天何
 * @date 2022/7/16
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        return generateParenthesisRecur("", n, n);
    }

    public List<String> generateParenthesisRecur(String prefix, int leftNum, int rightNum) {
        if (leftNum == rightNum) {
            return generateParenthesisRecur(prefix + "(", leftNum - 1, rightNum);
        }

        if (leftNum == 0) {
            StringBuilder builder = new StringBuilder(prefix);
            for (int i = 0; i < rightNum; i++) builder.append(")");
            List<String> resultList = new ArrayList<>();
            resultList.add(builder.toString());
            return resultList;
        }

        List<String> leftList = generateParenthesisRecur(prefix + "(", leftNum - 1, rightNum);
        List<String> rightList = generateParenthesisRecur(prefix + ")", leftNum, rightNum - 1);
        List<String> resultList = new ArrayList<>();
        resultList.addAll(leftList);
        resultList.addAll(rightList);

        return resultList;
    }

    public static void main(String[] args) {
        int num = 8;
        List<String> list = new GenerateParentheses().generateParenthesisRecur("", num, num);
        System.out.println(new Gson().toJson(list));
    }
}
