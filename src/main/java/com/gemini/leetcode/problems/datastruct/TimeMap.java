package com.gemini.leetcode.problems.datastruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/time-based-key-value-store/
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class TimeMap {

    class ValueItem {
        public String value;
        public int tms;

        public ValueItem(String value, int tms) {
            this.value = value;
            this.tms = tms;
        }
    }

    private Map<String, List<ValueItem>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<ValueItem> valueItemList = map.computeIfAbsent(key, k -> new ArrayList<>());
        valueItemList.add(new ValueItem(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        List<ValueItem> valueItemList = map.get(key);
        if (valueItemList.get(0).tms > timestamp) return "";
        int n = valueItemList.size();
        if (valueItemList.get(n - 1).tms <= timestamp) return valueItemList.get(n - 1).value;
        return search(valueItemList, timestamp, 0, n - 1);
    }

    private String search(List<ValueItem> valueItemList, int timestamp, int start, int end) {
        // search in [start,end)
        if (start >= end) return "";
        if (start + 1 == end) return valueItemList.get(start).value;
        int mid = start + (end - start) / 2;
        if (timestamp >= valueItemList.get(mid).tms) return search(valueItemList, timestamp, mid, end);
        else return search(valueItemList, timestamp, start, mid);
    }

}
