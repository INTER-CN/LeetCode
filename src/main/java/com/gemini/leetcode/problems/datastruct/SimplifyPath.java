package com.gemini.leetcode.problems.datastruct;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/simplify-path/
 * 栈
 *
 * @Author Gemini
 * 2022-08-19
 **/
public class SimplifyPath {

    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        int index = 0, start, end;
        String token;
        while (index < path.length()) {
            // read next token
            while (index < path.length() && path.charAt(index) == '/') index++;
            if (index == path.length()) break;
            start = index;
            while (index < path.length() && path.charAt(index) != '/') index++;
            end = index;
            token = path.substring(start, end);

            if (".".equals(token)) {
                continue;
            } else if ("..".equals(token)) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(token);
            }
        }

        if (stack.isEmpty()) return "/";
        String result = "";
        while (!stack.isEmpty()) result = ("/" + stack.pop()) + result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SimplifyPath().simplifyPath("/home//foo/"));
    }
}
