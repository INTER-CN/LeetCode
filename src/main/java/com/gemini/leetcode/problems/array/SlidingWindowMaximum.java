package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/sliding-window-maximum/
 * 大根堆（超时）
 * 双端队列
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class SlidingWindowMaximum {

    private class Item {
        public int value;
        public int index;

        public Item(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) return nums;

        LinkedList<Item> list = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!list.isEmpty() && list.peekLast().value < nums[i]) list.pollLast();
            list.add(new Item(nums[i], i));
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        result[0] = list.peekFirst().value;
        int resultIndex = 1;

        int left = 1, right = k;
        while (right < n) {
            if (list.peekFirst().index < left) list.pollFirst();
            while (!list.isEmpty() && list.peekLast().value < nums[right]) list.pollLast();
            list.add(new Item(nums[right], right));
            result[resultIndex++] = list.peekFirst().value;

            left++;
            right++;
        }

        return result;
    }

    /**
     * 超时
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (k == 1) return nums;

        // init heap
        int n = nums.length;
        int[] heap = new int[n];
        for (int i = 0; i < k; i++) heap[i] = i;
        int heapSize = k;
        adjustHeap(nums, heap, heapSize);

        int[] result = new int[n - k + 1];
        result[0] = nums[heap[0]];
        int resultIndex = 1;

        // slide window
        int left = 1, right = k;
        while (right < n) {
            heap[heapSize++] = right;
//            adjustHeap(nums, heap, heapSize);
            if (nums[heap[heapSize - 1]] > nums[heap[0]]) swap(heap, 0, heapSize - 1);
            while (heap[0] < left) {
                swap(heap, 0, heapSize - 1);
                heapSize--;
                adjustHeap(nums, heap, heapSize);
            }
            result[resultIndex++] = nums[heap[0]];

            left++;
            right++;
        }

        return result;
    }

    private void adjustHeap(int[] nums, int[] heap, int heapSize) {
        int index = heapSize / 2 - 1;

        int left, right;
        while (index >= 0) {
            left = index * 2 + 1;
            right = index * 2 + 2;
            if (nums[heap[index]] < nums[heap[left]]) swap(heap, index, left);
            if (right < heapSize && nums[heap[index]] < nums[heap[right]]) swap(heap, index, right);
            index--;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = new SlidingWindowMaximum().maxSlidingWindow(nums, k);
        System.out.println(new Gson().toJson(result));
    }
}
