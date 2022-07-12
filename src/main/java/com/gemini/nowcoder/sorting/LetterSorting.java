package com.gemini.nowcoder.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * HJ26 字符串排序
 * https://www.nowcoder.com/practice/5190a1db6f4f4ddb92fd9c365c944584?tpId=37&tqId=21249
 * 排序
 *
 * @author 天何
 * @date 2022/7/11
 */
public class LetterSorting {

    static class SortingItem {
        public char letter;
        public int sortingOrder;

        public SortingItem(char letter) {
            this.letter = letter;
            this.sortingOrder = ('A' <= letter && letter <= 'Z') ? (letter - 'A') : (letter - 'a');
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        br.close();

        // 字符串长度为m，英文字母个数为n
        int m = s.length();
        boolean[] flag = new boolean[m];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            char ch = s.charAt(i);
            if ('a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z') {
                builder.append(ch);
                flag[i] = true;
            }
        }

        // 组装数据结构
        String letterStr = builder.toString();
        int n = letterStr.length();
        SortingItem[] items = new SortingItem[n], buffer = new SortingItem[n];
        for (int i = 0; i < n; i++) {
            items[i] = new SortingItem(letterStr.charAt(i));
        }

        // 归并排序，O(nlogn)且稳定的排序算法
        mergeSort(items, buffer, 0, n - 1);

        // 写回排序后的数据
        builder = new StringBuilder();
        int index = 0;
        for (int i = 0; i < m; i++) {
            if (flag[i]) builder.append(items[index++].letter);
            else builder.append(s.charAt(i));
        }

        System.out.println(builder.toString());
    }

    private static void mergeSort(SortingItem[] items, SortingItem[] buffer, int start, int end) {
        if (start >= end) return;

        if (start + 1 == end) {
            if (items[start].sortingOrder > items[end].sortingOrder) {
                buffer[start] = items[start];
                buffer[end] = items[end];
                items[start] = buffer[end];
                items[end] = buffer[start];
            }
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(items, buffer, start, mid);
        mergeSort(items, buffer, mid + 1, end);

        int i = start, j = mid + 1, index = start;
        while (i <= mid && j <= end) {
            if (items[i].sortingOrder <= items[j].sortingOrder) {
                buffer[index++] = items[i++];
            } else {
                buffer[index++] = items[j++];
            }
        }
        while (i <= mid) buffer[index++] = items[i++];
        while (j <= end) buffer[index++] = items[j++];

        for (index = start; index <= end; index++) items[index] = buffer[index];
    }

}
