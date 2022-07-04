package com.gemini.leetcode.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/course-schedule/
 * 拓扑排序
 *
 * @author 天何
 * @date 2022/7/4
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // 邻接表
        Map<Integer, List<Integer>> dependencyMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int former = prerequisite[0];
            int latter = prerequisite[1];
            List<Integer> latterList = dependencyMap.computeIfAbsent(former, k -> new ArrayList<>());
            latterList.add(latter);
        }

        // 反向遍历，移除出度为0的节点
        boolean hasChanges = true;
        boolean[] removeFlag = new boolean[numCourses];
        int removeCount = 0;
        while (hasChanges) {
            hasChanges = false;
            // 找到出度为0的节点
            List<Integer> removeList = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                if (removeFlag[i] == false) {
                    List<Integer> latterList = dependencyMap.get(i);
                    if (latterList == null || latterList.size() == 0) {
                        removeList.add(i);
                        removeFlag[i] = true;
                        removeCount++;
                        hasChanges = true;
                    }
                }
            }
            if (removeCount == numCourses) return true;

            // 遍历出度不为0的列表，删除上述节点
            for (List<Integer> latterList : dependencyMap.values()) {
                latterList.removeAll(removeList);
            }
        }

        return removeCount == numCourses;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {
            {0, 1},
            {1, 0}
        };
        System.out.println(new CourseSchedule().canFinish(numCourses, prerequisites));
    }
}
