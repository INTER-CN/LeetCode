package com.gemini.leetcode.problems.datastruct;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/
 * 归并排序
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class CountSmallerNumbers {

    class Item {
        public int value;
        public int index;
        public int smallerCount;

        public Item(int value, int index) {
            this.value = value;
            this.index = index;
            this.smallerCount = 0;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) items[i] = new Item(nums[i], i);
        Item[] tempArea = new Item[n];

        mergeSort(items, tempArea, 0, n - 1);

        Integer[] counts = new Integer[n];
        for (Item item : items) counts[item.index] = item.smallerCount;

        return Arrays.asList(counts);
    }

    private void mergeSort(Item[] items, Item[] tempArea, int start, int end) {
        if (start >= end) return;
        if (start + 1 == end) {
            if (items[start].value > items[end].value) {
                items[start].smallerCount++;
                Item tempItem = items[start];
                items[start] = items[end];
                items[end] = tempItem;
            }
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(items, tempArea, start, mid);
        mergeSort(items, tempArea, mid + 1, end);

        int left = start, right = mid + 1, index = start;
        while (left <= mid && right <= end) {
            if (items[left].value <= items[right].value) {
                items[left].smallerCount += (right - mid - 1);
                tempArea[index++] = items[left++];
            } else {
                tempArea[index++] = items[right++];
            }
        }
        while (left <= mid) {
            items[left].smallerCount += (right - mid - 1);
            tempArea[index++] = items[left++];
        }
        while (right <= end) {
            tempArea[index++] = items[right++];
        }
        // write back
        for (int i = start; i <= end; i++) items[i] = tempArea[i];
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        System.out.println(new CountSmallerNumbers().countSmaller(nums));
    }
}
