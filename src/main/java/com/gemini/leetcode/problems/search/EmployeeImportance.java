package com.gemini.leetcode.problems.search;

import com.gemini.leetcode.model.Employee;

import java.util.*;

/**
 * https://leetcode.cn/problems/employee-importance/
 * 哈希表 + 树
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class EmployeeImportance {

    private class Node {
        public int importance;
        public List<Node> list;

        public Node(int importance) {
            this.importance = importance;
            this.list = new LinkedList<>();
        }
    }

    public int getImportance(List<Employee> employees, int id) {
        // init tree
        Map<Integer, Node> map = new HashMap<>();
        for (Employee employee : employees) {
            Node node = map.get(employee.id);
            if (node == null) {
                node = new Node(employee.importance);
                map.put(employee.id, node);
            } else if (node.importance == Integer.MIN_VALUE) {
                node.importance = employee.importance;
            }
            for (Integer subordinate : employee.subordinates) {
                Node childNode = map.get(subordinate);
                if (childNode == null) {
                    childNode = new Node(Integer.MIN_VALUE);
                    map.put(subordinate, childNode);
                }
                node.list.add(childNode);
            }
        }

        // search by BFS
        Queue<Node> queue = new LinkedList<>();
        Node initNode = map.get(id);
        int result = initNode.importance;
        queue.add(initNode);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node childNode : node.list) {
                result += childNode.importance;
                queue.add(childNode);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, 5, Arrays.asList(2, 3)),
            new Employee(2, 3, Arrays.asList()),
            new Employee(3, 3, Arrays.asList())
        );
        int id = 1;
        System.out.println(new EmployeeImportance().getImportance(employees, id));
    }
}
