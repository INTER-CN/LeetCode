package com.gemini.leetcode.problems.datastruct;

import com.gemini.leetcode.model.NestedInteger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/flatten-nested-list-iterator/
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class NestedIterator implements Iterator<Integer> {

    private List<Integer> flattenedList;
    private Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenedList = new LinkedList<>();
        flattenList(flattenedList, nestedList);
        iterator = flattenedList.iterator();
    }

    private void flattenList(List<Integer> flattenedList, List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) return;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) flattenedList.add(nestedInteger.getInteger());
            else flattenList(flattenedList, nestedInteger.getList());
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}
