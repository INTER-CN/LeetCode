package com.gemini.leetcode.model;

import java.util.List;

/**
 * @Author Gemini
 * 2022-09-01
 **/
public class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;

    public Employee(int id, int importance, List<Integer> subordinates) {
        this.id = id;
        this.importance = importance;
        this.subordinates = subordinates;
    }
}
