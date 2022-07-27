package com.gemini.nowcoder.datastruct;

import java.util.*;

/**
 * HJ77 火车进站
 * https://www.nowcoder.com/practice/97ba57c35e9f4749826dc3befaeae109?tpId=37&tqId=21300
 * 栈操作 + 递归
 *
 * @author 天何
 * @date 2022/7/26
 */
public class StackOperations {

    private static List<String> result;

    private static void recursiveOperation(List<Integer> list, Stack<Integer> stack, int n, int[] nums) {

        int index = list.size() + stack.size();

        if (list.size() == n) {
            StringBuilder builder = new StringBuilder();
            for (Integer item : list) builder.append(item + " ");
            result.add(builder.toString().trim());
            return;
        }

        if (!stack.isEmpty()) {
            Stack<Integer> newStack = copy(stack);
            Integer item = newStack.pop();
            List<Integer> newList = new LinkedList<>(list);
            newList.add(item);
            recursiveOperation(newList, newStack, n, nums);
        }

        if (index < n) {
            Stack<Integer> newStack = copy(stack);
            List<Integer> newList = new LinkedList<>(list);
            newStack.push(nums[index]);
            recursiveOperation(newList, newStack, n, nums);
        }
    }

    private static Stack<Integer> copy(Stack<Integer> stack) {
        if (stack.isEmpty()) return new Stack<>();
        Stack<Integer> reverseStack = new Stack<>();
        while (!stack.isEmpty()) reverseStack.push(stack.pop());
        Stack<Integer> newStack = new Stack<>();
        while (!reverseStack.isEmpty()) {
            Integer item = reverseStack.pop();
            stack.push(item);
            newStack.push(item);
        }
        return newStack;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = scanner.nextInt();
        scanner.close();

        result = new LinkedList<>();

        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        recursiveOperation(list, stack, n, nums);

        Collections.sort(result);
        for (String item : result) {
            System.out.println(item);
        }
    }
}
