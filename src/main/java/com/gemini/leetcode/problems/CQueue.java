package com.gemini.leetcode.problems;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 用两个栈实现队列
 *
 * @author 天何
 * @date 2022/7/5
 */
public class CQueue {

    Stack<Integer> stackA;
    Stack<Integer> stackB;

    public CQueue() {
        this.stackA = new Stack<>();
        this.stackB = new Stack<>();
    }

    public void appendTail(int value) {
        stackA.push(value);
    }

    public int deleteHead() {
        if (stackB.isEmpty()) {
            while (!stackA.isEmpty()) stackB.push(stackA.pop());
        }

        return stackB.isEmpty() ? -1 : stackB.pop();
    }

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }
}
