package com.gemini.nowcoder.datastruct;

import java.util.Scanner;
import java.util.Stack;

/**
 * HJ70 矩阵乘法计算量估算
 * https://www.nowcoder.com/practice/15e41630514445719a942e004edc0a5b?tpId=37&tqId=21293
 * 栈操作
 *
 * @author 天何
 * @date 2022/7/26
 */
public class MatrixMultiCounter {

    static class Node {
        public char symbol;
        public int[] size;

        public Node(char symbol, int[] size) {
            this.symbol = symbol;
            this.size = size;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] size = new int[n][2];
        for (int i = 0; i < n; i++) {
            size[i][0] = scanner.nextInt();
            size[i][1] = scanner.nextInt();
        }
        String s = scanner.next();
        scanner.close();

        Stack<Node> stack = new Stack<>();
        int sum = 0;
        for (char ch : s.toCharArray()) {
            if ('A' <= ch && ch <= 'Z') {
                stack.push(new Node(ch, size[ch - 'A']));
            } else if (ch == '(') {
                stack.push(new Node('(', null));
            } else if (ch == ')') {
                Node node2 = stack.pop();
                Node node1 = stack.pop();
                stack.pop(); // 左括号
                sum += (node1.size[0] * node1.size[1] * node2.size[1]);
                int[] newSize = new int[2];
                newSize[0] = node1.size[0];
                newSize[1] = node2.size[1];
                stack.push(new Node('X', newSize));
            }
        }

        System.out.println(sum);
    }
}
