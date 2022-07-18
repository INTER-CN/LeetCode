package com.gemini.leetcode.problems.datastruct;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/min-stack/
 *
 * @author 天何
 * @date 2022/7/18
 */
public class MinStack {

    class StackNode {
        public int min;
        public int val;
    }

    private Stack<StackNode> stack;

    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int val) {
        StackNode node = new StackNode();
        node.val = val;
        node.min = stack.isEmpty() ? val : Math.min(val, getMin());
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }
}
