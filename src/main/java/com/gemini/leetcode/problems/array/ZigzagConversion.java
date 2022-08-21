package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/zigzag-conversion/
 * 数组操作
 *
 * @Author Gemini
 * 2022-08-17
 **/
public class ZigzagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        int m = numRows * 2 - 2;

        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            builder.append(s.charAt(index));
            index += m;
        }
        int index1, index2;
        for (int i = 1; i < numRows - 1; i++) {
            index1 = i;
            index2 = m - i;
            while (index1 < s.length() || index2 < s.length()) {
                if (index1 < s.length()) {
                    builder.append(s.charAt(index1));
                    index1 += m;
                }
                if (index2 < s.length()) {
                    builder.append(s.charAt(index2));
                    index2 += m;
                }
            }
        }
        index = numRows - 1;
        while (index < s.length()) {
            builder.append(s.charAt(index));
            index += m;
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        System.out.println(new ZigzagConversion().convert(s, numRows));
    }
}
