package com.gemini.leetcode.problems.datastruct;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/longest-absolute-file-path/
 * 字符串处理 + 栈
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class LongestAbsolutePath {

    public int lengthLongestPath(String input) {
        int indent;
        String[] lines = input.split("\n");
        int currentLength = 0;
        int maxLength = 0;

        Stack<String> stack = new Stack<>();
        for (String line : lines) {
            indent = 0;
            while (indent < line.length() && line.charAt(indent) == '\t') indent++;
            if (indent == line.length()) return 0;

            String name = line.substring(indent);
            while (stack.size() > indent) currentLength -= stack.pop().length();
            stack.push(name);
            currentLength += name.length();

            if (isFile(name)) {
                int length = currentLength + stack.size() - 1;
                if (length > maxLength) maxLength = length;
            }
        }

        return maxLength;
    }

    private boolean isFile(String name) {
        return name.contains(".");
    }

    public static void main(String[] args) {
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(new LongestAbsolutePath().lengthLongestPath(input));
    }
}
