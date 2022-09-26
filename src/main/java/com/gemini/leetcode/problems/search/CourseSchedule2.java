package com.gemini.leetcode.problems.search;

import com.google.gson.Gson;

import java.util.*;

/**
 * https://leetcode.cn/problems/course-schedule-ii/
 * 拓扑排序
 * 反向遍历，逐层移除出度为0的节点
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 邻接表
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> list = map.computeIfAbsent(prerequisite[1], k -> new LinkedList<>());
            list.add(prerequisite[0]);
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] flag = new boolean[numCourses];
        while (stack.size() < numCourses) {
            List<Integer> removeList = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (flag[i]) continue;
                if (!map.getOrDefault(i, Collections.emptyList()).isEmpty()) continue;
                removeList.add(i);
            }
            if (removeList.isEmpty()) return new int[0];
            for (int i : removeList) {
                stack.push(i);
                flag[i] = true;
            }
            for (List<Integer> list : map.values()) list.removeAll(removeList);

        }

        int[] result = new int[numCourses];
        int index = 0;
        while (!stack.isEmpty()) result[index++] = stack.pop();

        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] result = new CourseSchedule2().findOrder(n, prerequisites);
        System.out.println(new Gson().toJson(result));
    }
}
