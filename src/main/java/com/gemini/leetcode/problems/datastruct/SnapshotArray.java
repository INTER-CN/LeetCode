package com.gemini.leetcode.problems.datastruct;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/snapshot-array/
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class SnapshotArray {

    private class Item {
        public int currentValue;
        public List<Integer> valueList;
        public List<Integer> versionList;

        public Item() {
            this.currentValue = 0;
            this.valueList = new ArrayList<>();
            this.versionList = new ArrayList<>();
        }

        public int getLatestVersion() {
            return versionList.get(versionList.size() - 1);
        }
    }

    private Item[] array;
    private int n;
    private int snapId;

    public SnapshotArray(int length) {
        this.array = new Item[length];
        this.n = length;
        this.snapId = 0;
        for (int i = 0; i < this.n; i++) {
            array[i] = new Item();
        }
    }

    public void set(int index, int val) {
        Item item = array[index];
        if (val == item.currentValue) return;

        item.valueList.add(item.currentValue);
        item.versionList.add(snapId - 1);
        item.currentValue = val;
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snapId) {
        Item item = array[index];
        if (item.versionList.isEmpty() || snapId > item.getLatestVersion()) return item.currentValue;

        int left = 0, right = item.versionList.size() - 1;
        while (true) {
            if (left == right) return item.valueList.get(left);
            if (left + 1 == right) {
                if (snapId <= item.versionList.get(left)) return item.valueList.get(left);
                else return item.valueList.get(right);
            }

            int mid = left + (right - left) / 2;
            if (item.versionList.get(mid - 1) < snapId && snapId <= item.versionList.get(mid))
                return item.valueList.get(mid);
            else if (item.versionList.get(mid) < snapId) left = mid + 1;
            else right = mid - 1;
        }
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray(1);
        snapshotArray.snap();
        snapshotArray.set(0, 10);
        snapshotArray.snap();
        snapshotArray.snap();
        snapshotArray.snap();
        snapshotArray.set(0, 20);
        snapshotArray.snap();
        snapshotArray.snap();
        snapshotArray.set(0, 30);
        snapshotArray.snap();
        System.out.println(snapshotArray.get(0, 0));
        System.out.println(snapshotArray.get(0, 1));
        System.out.println(snapshotArray.get(0, 2));
        System.out.println(snapshotArray.get(0, 3));
        System.out.println(snapshotArray.get(0, 4));
        System.out.println(snapshotArray.get(0, 5));
        System.out.println(snapshotArray.get(0, 6));
    }
}
