package com.gemini.leetcode.problems.datastruct;

import com.google.gson.Gson;

import java.util.*;

/**
 * https://leetcode.cn/problems/evaluate-division/
 * 图
 *
 * @author 天何
 * @date 2022/7/25
 */
public class EvaluateDivision {

    class Node {
        public int startIndex;
        public int currentIndex;
        public double value;

        public Node(int startIndex, int currentIndex, double value) {
            this.startIndex = startIndex;
            this.currentIndex = currentIndex;
            this.value = value;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 构造变量和索引的关系
        Map<String, Integer> indexMap = new HashMap<>();
        int n = 0;
        for (List<String> equation : equations) {
            if (!indexMap.containsKey(equation.get(0))) {
                indexMap.put(equation.get(0), n++);
            }
            if (!indexMap.containsKey(equation.get(1))) {
                indexMap.put(equation.get(1), n++);
            }
        }

        // 初始化邻接表
        double[][] graph = new double[n][n];
        for (int i = 0; i < n; i++) graph[i][i] = 1.0;
        int x, y;
        for (int i = 0; i < equations.size(); i++) {
            x = indexMap.get(equations.get(i).get(0));
            y = indexMap.get(equations.get(i).get(1));
            graph[x][y] = values[i];
            graph[y][x] = 1.0 / values[i];
        }

        // 一边遍历一边填值
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String s1 = queries.get(i).get(0);
            String s2 = queries.get(i).get(1);
            if (!indexMap.containsKey(s1) || !indexMap.containsKey(s2)) {
                result[i] = -1.0;
                continue;
            }

            x = indexMap.get(s1);
            y = indexMap.get(s2);

            if (graph[x][y] > 0.0) {
                result[i] = graph[x][y];
                continue;
            }

            // 从x开始，遍历到y为止
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(x, x, 1.0));
            boolean reached = false;
            boolean[] flag = new boolean[n];
            flag[x] = true;
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                int fromIndex = node.currentIndex;
                for (int toIndex = 0; toIndex < n; toIndex++) {
                    if (toIndex == x) continue;
                    if (fromIndex == toIndex) continue;
                    if (graph[fromIndex][toIndex] == 0.0) continue;
                    if (flag[toIndex]) continue;
                    double currentValue = node.value * graph[fromIndex][toIndex];
                    queue.add(new Node(x, toIndex, currentValue));
                    graph[x][toIndex] = currentValue;
                    flag[toIndex] = true;
                    if (toIndex == y) {
                        result[i] = currentValue;
                        reached = true;
                        break;
                    }
                }
                if (reached) break;
            }
            if (!reached) result[i] = -1.0;
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("c", "d"));
        double[] values = {1.0, 1.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("d", "c"));
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "d"));
        double[] result = new EvaluateDivision().calcEquation(equations, values, queries);
        System.out.println(new Gson().toJson(result));
    }
}
