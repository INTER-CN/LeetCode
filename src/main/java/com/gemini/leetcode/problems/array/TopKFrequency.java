package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/top-k-frequent-elements/
 * 哈希表 + 堆排序
 *
 * @author 天何
 * @date 2022/7/20
 */
public class TopKFrequency {

    class FrequencyItem {
        public int frequency;
        public List<Integer> valueList;

        public FrequencyItem(int frequency, List<Integer> valueList) {
            this.frequency = frequency;
            this.valueList = valueList;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int frequency = map.getOrDefault(num, 0);
            map.put(num, frequency + 1);
        }

        Map<Integer, FrequencyItem> frequencyMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getKey();
            int frequency = entry.getValue();

            if (frequencyMap.containsKey(frequency)) {
                FrequencyItem item = frequencyMap.get(frequency);
                item.valueList.add(value);
            } else {
                List<Integer> valueList = new ArrayList<>();
                valueList.add(value);
                frequencyMap.put(frequency, new FrequencyItem(frequency, valueList));
            }
        }

        List<FrequencyItem> itemList = frequencyMap.values().stream().collect(Collectors.toList());
        int n = itemList.size();
        FrequencyItem[] items = new FrequencyItem[n];
        for (int i = 0; i < n; i++) items[i] = itemList.get(i);

        // 堆排序
        List<Integer> resultList = new ArrayList<>();
        int j, j1, j2;
        for (int i = n - 1; i >= 0; i--) {
            if (i > 0) {
                j = (i - 1) / 2;
                while (j >= 0) {
                    j1 = 2 * j + 1;
                    j2 = 2 * j + 2;
                    if (items[j].frequency < items[j1].frequency) swap(items, j, j1);
                    if (j2 <= i && items[j].frequency < items[j2].frequency) swap(items, j, j2);
                    j--;
                }
                swap(items, 0, i);
            }

            // 排序后的元素加入结果列表
            for (Integer value : items[i].valueList) {
                resultList.add(value);
            }
            if (resultList.size() >= k) {
                int[] results = new int[resultList.size()];
                for (int x = 0; x < results.length; x++) results[x] = resultList.get(x);
                return results;
            }
        }

        return null;
    }

    private void swap(FrequencyItem[] items, int i, int j) {
        FrequencyItem item = items[i];
        items[i] = items[j];
        items[j] = item;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = new TopKFrequency().topKFrequent(nums, k);
        System.out.println(new Gson().toJson(result));
    }
}
