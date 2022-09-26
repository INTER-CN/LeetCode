package com.gemini.leetcode.problems.stock;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * https://leetcode.cn/problems/stock-price-fluctuation/
 * 有序哈希表
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class StockPrice {

    private HashMap<Integer, Integer> map;
    private TreeMap<Integer, Integer> priceMap;
    private int maxTms;

    public StockPrice() {
        map = new HashMap<>();
        priceMap = new TreeMap<>();
        maxTms = 0;
    }

    public void update(int timestamp, int price) {
        if (map.containsKey(timestamp)) {
            int oldPrice = map.get(timestamp);
            if (oldPrice == price) return;
            int count = priceMap.get(oldPrice);
            if (count == 1) {
                priceMap.remove(oldPrice);
            } else {
                priceMap.put(oldPrice, count - 1);
            }
        }
        map.put(timestamp, price);
        int count = priceMap.getOrDefault(price, 0);
        priceMap.put(price, count + 1);
        if (timestamp > maxTms) maxTms = timestamp;
    }

    public int current() {
        return map.get(maxTms);
    }

    public int maximum() {
        return priceMap.lastKey();
    }

    public int minimum() {
        return priceMap.firstKey();
    }
}
