package com.gemini.nowcoder.datastruct;

import java.util.Scanner;
import java.util.Stack;

/**
 * HJ50 四则运算
 * https://www.nowcoder.com/practice/9999764a61484d819056f807d2a91f1e?tpId=37&tqId=21273
 * 栈操作
 *
 * @author 天何
 * @date 2022/7/21
 */
public class Calculator {

    static class Token {
        public int val;
        public char op;
        public int type;
        public int length;

        public Token(int val, char op, int type, int length) {
            this.val = val;
            this.op = op;
            this.type = type;
            this.length = length;
        }
    }

    private static final int NUMBER = 1;
    private static final int OPERATOR = 2;

    private static Token getNextToken(String s, int index, boolean lastTokenIsNumber) {
        if (index >= s.length()) return null;
        char initChar = s.charAt(index);

        if (lastTokenIsNumber && initChar == '-') {
            return new Token(0, '-', OPERATOR, 1);
        }

        if (initChar == '-' || initChar >= '0' && initChar <= '9') {
            int end = index + 1;
            while (end < s.length() && s.charAt(end) >= '0' && s.charAt(end) <= '9') end++;
            int val = Integer.parseInt(s.substring(index, end));
            return new Token(val, ' ', NUMBER, end - index);
        }

        return new Token(0, initChar, OPERATOR, 1);
    }

    private static void calculate(Stack<Token> stack, char terminator) {
        if (stack.size() == 1) return;

        // 括号内部只有加减法
        Stack<Token> adderStack = new Stack<>();
        while (!stack.isEmpty() && stack.peek().op != terminator) {
            adderStack.push(stack.pop());
        }
        int val = calculateAdder(adderStack);

        if (stack.isEmpty()) {
            stack.push(new Token(val, ' ', NUMBER, 0));
        } else {
            stack.pop();
            if (!stack.isEmpty() && stack.peek().op == '*') {
                stack.pop();
                Token numToken = stack.pop();
                int result = numToken.val * val;
                stack.push(new Token(result, ' ', NUMBER, 0));
            } else if (!stack.isEmpty() && stack.peek().op == '/') {
                stack.pop();
                Token numToken = stack.pop();
                int result = numToken.val / val;
                stack.push(new Token(result, ' ', NUMBER, 0));
            } else {
                stack.push(new Token(val, ' ', NUMBER, 0));
            }
        }
    }

    private static int calculateAdder(Stack<Token> stack) {
        Token top = stack.pop();
        int result = top.val;
        while (!stack.isEmpty()) {
            Token opToken = stack.pop();
            Token numToken = stack.pop();
            if (opToken.op == '+') {
                result += numToken.val;
            } else if (opToken.op == '-') {
                result -= numToken.val;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // input
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        scanner.close();

        int index = 0;
        Token token = getNextToken(s, index, false);
        Stack<Token> stack = new Stack<>();
        while (token != null) {
            if (token.type == NUMBER) {
                // 数字：如果栈顶是乘或者除，则计算后入栈，否则直接入栈
                if (!stack.isEmpty() && stack.peek().op == '*') {
                    stack.pop();
                    Token numToken = stack.pop();
                    int result = numToken.val * token.val;
                    stack.push(new Token(result, ' ', NUMBER, 0));
                } else if (!stack.isEmpty() && stack.peek().op == '/') {
                    stack.pop();
                    Token numToken = stack.pop();
                    int result = numToken.val / token.val;
                    stack.push(new Token(result, ' ', NUMBER, 0));
                } else {
                    stack.push(token);
                }
            } else {
                // 符号：加减乘除和左括号直接入栈，右括号进行合并计算
                switch (token.op) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '(':
                    case '[':
                    case '{':
                        stack.push(token);
                        break;
                    case ')':
                        calculate(stack, '(');
                        break;
                    case ']':
                        calculate(stack, '[');
                        break;
                    case '}':
                        calculate(stack, '{');
                        break;
                    default:
                        break;
                }
            }

            index += token.length;
            token = getNextToken(s, index, !stack.isEmpty() && stack.peek().type == NUMBER);
        }

        calculate(stack, '^');

        System.out.println(stack.pop().val);
    }
}
