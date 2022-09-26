package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/string-compression/
 * 数组线性扫描
 *
 * @Author Gemini
 * 2022-09-02
 **/
public class StringCompression {

    public int compress(char[] chars) {
        int n = chars.length;
        if (n < 2) return n;

        // [left,right)
        int left;
        int right = 0;
        int len;
        int count = 0;
        int index = 0;
        while (right < n) {
            left = right;
            while (right < n && chars[left] == chars[right]) right++;
            chars[index++] = chars[left];
            count++;
            len = right - left;
            if (len > 1) {
                char[] digits = lengthChars(len);
                for (char digit : digits) chars[index++] = digit;
                count += digits.length;
            }
        }

        return count;
    }

    private char[] lengthChars(int length) {
        char[] result;
        if (length < 10) {
            result = new char[1];
            result[0] = (char) ('0' + length);
        } else if (length < 100) {
            result = new char[2];
            result[0] = (char) ('0' + (length / 10));
            result[1] = (char) ('0' + (length % 10));
        } else if (length < 1000) {
            result = new char[3];
            result[0] = (char) ('0' + (length / 100));
            result[1] = (char) ('0' + ((length / 10) % 10));
            result[2] = (char) ('0' + (length % 10));
        } else {
            result = new char[4];
            result[0] = (char) ('0' + (length / 1000));
            result[1] = (char) ('0' + ((length / 100) % 10));
            result[2] = (char) ('0' + ((length / 10) % 10));
            result[3] = (char) ('0' + (length % 10));
        }
        return result;
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        System.out.println(new StringCompression().compress(chars));
        System.out.println(new Gson().toJson(chars));
    }
}
