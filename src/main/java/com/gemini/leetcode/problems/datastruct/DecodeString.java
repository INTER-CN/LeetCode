package com.gemini.leetcode.problems.datastruct;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/decode-string/
 * 栈
 * 设计自定义栈节点，保存每个token的值和类型，当遇到右括号时出栈进行解码
 *
 * @author 天何
 * @date 2022/7/5
 */
public class DecodeString {

    class Token {
        public String value;
        public char type;

        public Token(String value, char type) {
            this.value = value;
            this.type = type;
        }
    }

    public String decodeString(String s) {
        Stack<Token> stack = new Stack<>();

        int index = 0;
        Token token = getOneToken(s, index);
        while (token != null) {
            if (token.type == 'D') {
                // 入栈
                stack.push(token);
            } else if (token.type == 'S') {
                // 如果栈顶是字符串，则合并，否则直接入栈
                if (!stack.empty() && stack.peek().type == 'S') {
                    Token preToken = stack.pop();
                    stack.push(new Token(preToken.value + token.value, 'S'));
                } else {
                    stack.push(token);
                }
            } else if (token.type == 'R') {
                // 出栈，计算，入栈
                Token sToken = stack.pop();
                Token dToken = stack.pop();
                StringBuilder sb = new StringBuilder();
                int count = Integer.parseInt(dToken.value);
                for (int i = 0; i < count; i++) sb.append(sToken.value);
                String decodedValue = sb.toString();
                // 如果栈顶是字符串，则合并，否则直接入栈
                if (!stack.empty() && stack.peek().type == 'S') {
                    Token preToken = stack.pop();
                    stack.push(new Token(preToken.value + decodedValue, 'S'));
                } else {
                    stack.push(new Token(decodedValue, 'S'));
                }
            }

            index += token.value.length();
            token = getOneToken(s, index);
        }

        return stack.pop().value;
    }

    private Token getOneToken(String s, int startIndex) {
        if (startIndex >= s.length()) {
            return null;
        }

        char startChar = s.charAt(startIndex);
        if (startChar == '[') {
            return new Token("[", 'L');
        } else if (startChar == ']') {
            return new Token("]", 'R');
        } else if ('0' <= startChar && startChar <= '9') {
            int index = startIndex + 1;
            while (index < s.length() && '0' <= s.charAt(index) && s.charAt(index) <= '9') index++;
            return new Token(s.substring(startIndex, index), 'D');
        } else if ('a' <= startChar && startChar <= 'z') {
            int index = startIndex + 1;
            while (index < s.length() && 'a' <= s.charAt(index) && s.charAt(index) <= 'z') index++;
            return new Token(s.substring(startIndex, index), 'S');
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String s = "x3[a2[c]de]y";
        System.out.println(new DecodeString().decodeString(s));
    }
}
