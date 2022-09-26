package com.gemini.leetcode.problems.search;

import java.util.*;

/**
 * https://leetcode.cn/problems/longest-string-chain/
 * 有向图遍历
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class LongestStringChain {

    private class GraphNode {
        public String word;
        public int index;
        public List<GraphNode> nextList;

        public GraphNode(String word, int index) {
            this.word = word;
            this.index = index;
            this.nextList = new LinkedList<>();
        }
    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));

        int n = words.length;

        // build the nodes of graph
        Map<String, GraphNode> map = new HashMap<>();
        GraphNode[] nodes = new GraphNode[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            GraphNode graphNode = new GraphNode(word, i);
            map.put(word, graphNode);
            nodes[i] = graphNode;
        }

        // build the edges of graph
        for (int i = n - 1; i >= 0; i--) {
            String word = words[i];
            GraphNode inNode = map.get(word);
            List<String> predecessors = getPredecessors(word);
            for (String predecessor : predecessors) {
                GraphNode outNode = map.get(predecessor);
                if (outNode == null) continue;
                outNode.nextList.add(inNode);
            }
        }

        // traverse graph
        int maxLength = 1;
        boolean[] flag = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (flag[i]) continue;
            Queue<GraphNode> queue = new LinkedList<>();
            flag[nodes[i].index] = true;
            int startLength = nodes[i].word.length();
            int endLength = startLength, chainLength;
            queue.add(nodes[i]);
            while (!queue.isEmpty()) {
                GraphNode graphNode = queue.poll();
                for (GraphNode next : graphNode.nextList) {
                    if (flag[next.index]) continue;
                    endLength = next.word.length();
                    flag[next.index] = true;
                    queue.add(next);
                }
            }
            chainLength = endLength - startLength + 1;
            if (chainLength > maxLength) maxLength = chainLength;
        }

        return maxLength;
    }

    private List<String> getPredecessors(String word) {
        if (word.length() < 2) return Collections.emptyList();

        List<String> list = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            list.add(word.substring(0, i) + word.substring(i + 1));
        }

        return list;
    }

    public static void main(String[] args) {
        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println(new LongestStringChain().longestStrChain(words));
    }
}
