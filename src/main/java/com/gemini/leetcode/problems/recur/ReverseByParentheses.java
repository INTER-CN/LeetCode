package com.gemini.leetcode.problems.recur;

/**
 * https://leetcode.cn/problems/reverse-substrings-between-each-pair-of-parentheses/
 * 递归处理
 *
 * @Author Gemini
 * 2022-08-09
 **/
public class ReverseByParentheses {

    public String reverseParentheses(String s) {
        return reverseParenthesesRecur(s, false);
    }

    private String reverseParenthesesRecur(String s, boolean needReverse) {
        if (s.length() < 2) return s;

        while (true) {
            int[] index = getParenthesesIndex(s);
            if (index[0] == -1) return needReverse ? getReversed(s) : s;

            StringBuilder builder = new StringBuilder();
            builder.append(s, 0, index[0]);
            builder.append(reverseParenthesesRecur(s.substring(index[0] + 1, index[1]), true));
            builder.append(s.substring(index[1] + 1));

            s = builder.toString();
        }
    }

    private int[] getParenthesesIndex(String s) {
        int n = s.length();
        int left = 0;
        while (left < n && s.charAt(left) != '(') left++;

        if (left == n) return new int[]{-1, -1};

        int right = left + 1;
        int count = 0;
        while (right < n) {
            if (s.charAt(right) == ')') {
                if (count == 0) break;
                else count--;
            } else if (s.charAt(right) == '(') {
                count++;
            }
            right++;
        }

        return new int[]{left, right};
    }

    private String getReversed(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < n / 2) {
            swap(chars, i, n - 1 - i);
            i++;
        }
        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String s = "(ed(et(oc))el)";
        System.out.println(new ReverseByParentheses().reverseParentheses(s));
    }
}
