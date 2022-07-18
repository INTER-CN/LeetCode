package com.gemini.leetcode.problems.logic;

import java.util.*;

/**
 * https://leetcode.cn/problems/meeting-rooms-ii/
 * 区间操作
 *
 * @author 天何
 * @date 2022/7/18
 */
public class MeetingRooms2 {

    public int minMeetingRooms(int[][] intervals) {
        // 端点排序
        Set<Integer> pointSet = new HashSet<>();
        for (int[] interval : intervals) {
            pointSet.add(interval[0]);
            pointSet.add(interval[1]);
        }
        List<Integer> pointList = new ArrayList<>(pointSet);

        int[] points = new int[pointList.size()];
        for (int i = 0; i < points.length; i++) points[i] = pointList.get(i);
        Arrays.sort(points);

        // 生成原子区间
        int[][] atomicIntervals = new int[points.length - 1][2];
        for (int i = 0; i <points.length - 1; i++) {
            atomicIntervals[i][0] = points[i];
            atomicIntervals[i][1] = points[i + 1];
        }

        // 判断每个原子区间覆盖的区间数
        int roomNum = 1, count;
        for (int[] atomicInterval : atomicIntervals) {
            count = 0;
            for (int[] interval : intervals) {
                if (intersect(interval, atomicInterval)) count++;
            }
            if (count > roomNum) roomNum = count;
        }

        return roomNum;
    }

    private boolean intersect(int[] interval1, int[] interval2) {
        boolean noIntersect = interval1[0] >= interval2[1] || interval2[0] >= interval1[1];
        return !noIntersect;
    }

    public static void main(String[] args) {
        int[][] intervals = {
            {0, 30},
            {5, 10},
            {15, 20}
        };
        System.out.println(new MeetingRooms2().minMeetingRooms(intervals));
    }
}
