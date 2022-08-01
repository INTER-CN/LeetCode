package com.gemini.leetcode.problems.greedy;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/task-scheduler/
 * 线性扫描
 *
 * @author 天何
 * @date 2022/7/30
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;

        int[] count = new int[26];
        for (char task : tasks) count[task - 'A']++;

        int[] cooldown = new int[26];
        int remainingTast = tasks.length;

        List<Integer> list = new LinkedList<>();
        while (remainingTast > 0) {
            int index = getIndexOfMaxCount(count, cooldown);
            list.add(index);
            coolItDown(cooldown, index);
            if (index >= 0) {
                cooldown[index] = n;
                count[index]--;
                remainingTast--;
            }
        }

        return list.size();
    }

    private int getIndexOfMaxCount(int[] count, int[] cooldown) {
        int index = -1, max = 0;
        for (int i = 0; i < count.length; i++) {
            if (cooldown[i] == 0 && count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        return index;
    }

    private void coolItDown(int[] cooldown, int exceptIndex) {
        for (int i = 0; i < cooldown.length; i++) {
            if (i == exceptIndex) continue;
            if (cooldown[i] > 0) cooldown[i]--;
        }
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;
        System.out.println(new TaskScheduler().leastInterval(tasks, n));
    }
}
