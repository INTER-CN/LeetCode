package com.gemini.leetcode.problems.datastruct;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/
 * 哈希表
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class RandomizedSet {

    private static final int MAX_SIZE = 200_000;

    private Map<Integer, Integer> map;
    private int[] array;
    private int size;

    public RandomizedSet() {
        this.map = new HashMap<>();
        this.array = new int[MAX_SIZE];
        this.size = 0;
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, size);
        array[size++] = val;
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        array[index] = array[--size];
        map.put(array[index], index);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        int randomIndex = (int) (Math.random() * size);
        return array[randomIndex];
    }
}
