package com.gemini.leetcode.problems;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/merge-intervals/
 * 逻辑处理，复用插入单个区间的代码
 *
 * @author 天何
 * @date 2022/7/7
 */
public class MergeIntervals {

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    class Overlap {
        int index;
        String type;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            int[][] result = new int[1][2];
            result[0] = newInterval;
            return result;
        }

        int length = intervals.length;

        // 新区间在左侧
        if (intervals[0][LEFT] > newInterval[RIGHT]) {
            int[][] result = new int[length + 1][2];
            result[0] = newInterval;
            for (int i = 0; i < length; i++) result[i + 1] = intervals[i];
            return result;
        }

        // 新区间在右侧
        if (intervals[length - 1][RIGHT] < newInterval[LEFT]) {
            int[][] result = new int[length + 1][2];
            for (int i = 0; i < length; i++) result[i] = intervals[i];
            result[length] = newInterval;
            return result;
        }

        Overlap leftOverlap = getLeftOverlap(intervals, newInterval[LEFT], 0, length - 1);
        Overlap rightOverlap = getRightOverlap(intervals, newInterval[RIGHT], 0, length - 1);
        int leftIndex = leftOverlap.index;
        int rightIndex = rightOverlap.index;
        String leftType = leftOverlap.type;
        String rightType = rightOverlap.type;

        if (leftIndex - 1 == rightIndex && "out".equals(leftType) && "out".equals(rightType)) {
            // 新区间独立于已有区间
            int[][] result = new int[length + 1][2];
            int resultIndex = 0;
            for (int i = 0; i < leftIndex; i++) result[resultIndex++] = intervals[i];
            result[resultIndex++] = newInterval;
            for (int i = rightIndex + 1; i < length; i++) result[resultIndex++] = intervals[i];
            return result;
        } else if (leftIndex == rightIndex && "in".equals(leftType) && "in".equals(rightType)) {
            // 新区间左右端点都位于同一区间内部
            return intervals;
        } else {
            int[][] result = new int[length - (rightIndex - leftIndex)][2];
            int resultIndex = 0;
            for (int i = 0; i < leftIndex; i++) result[resultIndex++] = intervals[i];
            int[] mergeInterval = new int[2];
            if ("in".equals(leftType) && "in".equals(rightType)) {
                // 新区间左右端点都位于已有区间内部
                mergeInterval[LEFT] = intervals[leftIndex][LEFT];
                mergeInterval[RIGHT] = intervals[rightIndex][RIGHT];
            } else if ("in".equals(leftType) && "out".equals(rightType)) {
                // 新区间左端点位于区间内，右端点位于区间外
                mergeInterval[LEFT] = intervals[leftIndex][LEFT];
                mergeInterval[RIGHT] = newInterval[RIGHT];
            } else if ("out".equals(leftType) && "in".equals(rightType)) {
                // 新区间左端点位于区间外，右端点位于区间内
                mergeInterval[LEFT] = newInterval[LEFT];
                mergeInterval[RIGHT] = intervals[rightIndex][RIGHT];
            } else {
                // 新区间左右端点都位于已有区间外部
                mergeInterval = newInterval;
            }
            result[resultIndex++] = mergeInterval;
            for (int i = rightIndex + 1; i < length; i++) result[resultIndex++] = intervals[i];
            return result;
        }
    }

    private Overlap getLeftOverlap(int[][] intervals, int value, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            Overlap overlap = new Overlap();
            overlap.index = startIndex;
            overlap.type = intervals[startIndex][LEFT] > value ? "out" : "in";
            return overlap;
        }

        if (startIndex + 1 == endIndex) {
            Overlap overlap = new Overlap();
            overlap.index = intervals[startIndex][RIGHT] < value ? endIndex : startIndex;
            overlap.type = (intervals[startIndex][LEFT] <= value && value <= intervals[startIndex][RIGHT]
                || intervals[endIndex][LEFT] <= value && value <= intervals[endIndex][RIGHT])
                ? "in" : "out";
            return overlap;
        }

        int midIndex = startIndex + (endIndex - startIndex) / 2;

        return (intervals[midIndex][RIGHT] < value)
            ? getLeftOverlap(intervals, value, midIndex, endIndex)
            : getLeftOverlap(intervals, value, startIndex, midIndex);
    }

    private Overlap getRightOverlap(int[][] intervals, int value, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            Overlap overlap = new Overlap();
            overlap.index = startIndex;
            overlap.type = intervals[startIndex][RIGHT] < value ? "out" : "in";
            return overlap;
        }

        if (startIndex + 1 == endIndex) {
            Overlap overlap = new Overlap();
            overlap.index = intervals[endIndex][LEFT] > value ? startIndex : endIndex;
            overlap.type = (intervals[startIndex][LEFT] <= value && value <= intervals[startIndex][RIGHT]
                || intervals[endIndex][LEFT] <= value && value <= intervals[endIndex][RIGHT])
                ? "in" : "out";
            return overlap;
        }

        int midIndex = startIndex + (endIndex - startIndex) / 2;

        return (intervals[midIndex][LEFT] > value)
            ? getRightOverlap(intervals, value, startIndex, midIndex)
            : getRightOverlap(intervals, value, midIndex, endIndex);
    }


    public int[][] merge(int[][] intervals) {
        int[][] mergedIntervals = new int[1][2];
        mergedIntervals[0] = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            mergedIntervals = insert(mergedIntervals, intervals[i]);
        }
        return mergedIntervals;
    }

    public static void main(String[] args) {
        int[][] intervals = {
            {1, 3},
            {2, 6},
            {8, 10},
            {15, 18}
        };
        int[][] result = new MergeIntervals().merge(intervals);
        System.out.println(new Gson().toJson(result));
    }
}
