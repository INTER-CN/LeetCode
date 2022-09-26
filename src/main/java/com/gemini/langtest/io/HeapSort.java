package com.gemini.langtest.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author Gemini
 * 2022-08-29
 **/
public class HeapSort {

    private class Item {

        private static final int SCORE_INDEX = 2;

        public int score;
        public String content;

        public Item(String content) {
            this.content = content;
            String[] columns = content.split(",");
            this.score = columns.length > SCORE_INDEX ? Integer.parseInt(columns[SCORE_INDEX]) : 0;
        }
    }

    public void findLargestK(String url, int k) {
        URLConnection connection;
        BufferedReader reader = null;
        try {
            connection = new URL(url).openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // skip header
            reader.readLine();

            // heap sort
            String line;
            int count = 0;
            Item[] heap = new Item[k + 1];
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;
                heapSort(heap, Math.min(count++, k), k, new Item(line));
            }

            // print result
            for (int i = 0; i < k; i++) {
                System.out.println(heap[i].content);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void heapSort(Item[] heap, int index, int k, Item item) {
        heap[index] = item;

        if (index < k - 1) return;

        int lastParentIndex = (k - 2) / 2;
        if (index == k - 1) {
            // build heap
            for (int i = lastParentIndex; i >= 0; i--) {
                adjustHeap(heap, i, lastParentIndex, k);
            }
            return;
        }

        // index == k, adjust heap
        if (item.score <= heap[0].score) return;
        swap(heap, 0, k);
        adjustHeap(heap, 0, lastParentIndex, k);
    }

    private void adjustHeap(Item[] heap, int index, int lastParentIndex, int k) {
        int parentIndex = index;
        int leftIndex, rightIndex;
        while (parentIndex <= lastParentIndex) {
            leftIndex = parentIndex * 2 + 1;
            rightIndex = parentIndex * 2 + 2;
            if (heap[leftIndex].score < heap[parentIndex].score
                && (rightIndex == k || heap[leftIndex].score < heap[rightIndex].score)) {
                swap(heap, parentIndex, leftIndex);
                parentIndex = leftIndex;
            } else if (rightIndex < k && heap[rightIndex].score < heap[parentIndex].score
                && heap[rightIndex].score < heap[leftIndex].score) {
                swap(heap, parentIndex, rightIndex);
                parentIndex = rightIndex;
            } else {
                break;
            }
        }
    }

    private void swap(Item[] items, int i, int j) {
        Item temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    public static void main(String[] args) {
        String url = "https://inter-cn.oss-cn-beijing.aliyuncs.com/test/score.csv";
        int k = 10;
        new HeapSort().findLargestK(url, k);
    }
}
