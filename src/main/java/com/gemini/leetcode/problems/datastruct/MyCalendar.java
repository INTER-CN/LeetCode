package com.gemini.leetcode.problems.datastruct;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/my-calendar-i/
 * 二分查找
 *
 * @Author Gemini
 * 2022-08-28
 **/
public class MyCalendar {

    private class Event {
        public int start;
        public int end;

        public Event(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private List<Event> list;

    private int leftmost;

    private int rightmost;

    public MyCalendar() {
        this.list = new LinkedList<>();
        this.leftmost = -1;
        this.rightmost = -1;
    }

    public boolean book(int start, int end) {
        if (list.isEmpty()) {
            list.add(new Event(start, end));
            leftmost = start;
            rightmost = end;
            return true;
        }

        if (start >= rightmost) {
            list.add(new Event(start, end));
            rightmost = end;
            return true;
        }

        if (end <= leftmost) {
            list.add(0, new Event(start, end));
            leftmost = start;
            return true;
        }

        int left = 0, right = list.size() - 1;
        while (left <= right) {
            if (left == right) return false;
            if (left + 1 == right) {
                if (list.get(left).end <= start && end <= list.get(right).start) {
                    list.add(right, new Event(start, end));
                    return true;
                } else {
                    return false;
                }
            }
            int mid = left + (right - left) / 2;
            if (list.get(left).end <= start && end <= list.get(mid).start) right = mid;
            else if (list.get(mid).end <= start && end <= list.get(right).start) left = mid;
            else return false;
        }

        return false;
    }
}
