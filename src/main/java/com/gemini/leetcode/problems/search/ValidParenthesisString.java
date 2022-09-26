package com.gemini.leetcode.problems.search;

/**
 * https://leetcode.cn/problems/valid-parenthesis-string/
 * DFS + 递归 —— 超时
 * 动态规划
 *
 * @Author Gemini
 * 2022-09-15
 **/
public class ValidParenthesisString {

    public boolean checkValidString(String s) {
//        return validate(s, 0);

        int n = s.length();

        int leftMax = 0;
        int leftMin = 0;

        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    leftMax++;
                    leftMin++;
                    break;
                case ')':
                    if (leftMax == 0) return false;
                    leftMax--;
                    if (leftMin > 0) leftMin--;
                    break;
                case '*':
                    leftMax++;
                    if (leftMin > 0) leftMin--;
                    break;
                default:
                    return false;
            }
        }

        return leftMin == 0;
    }

    private boolean validate(String s, int leftCount) {
        if (s.length() == 0) return leftCount == 0;

        char c = s.charAt(0);
        String sub = s.substring(1);
        switch (c) {
            case '(':
                return validate(sub, leftCount + 1);
            case ')':
                return leftCount > 0 && validate(sub, leftCount - 1);
            case '*':
                return validate(sub, leftCount) || validate(sub, leftCount + 1)
                    || leftCount > 0 && validate(sub, leftCount - 1);
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        String s = "(*)";
        System.out.println(new ValidParenthesisString().checkValidString(s));
    }
}
