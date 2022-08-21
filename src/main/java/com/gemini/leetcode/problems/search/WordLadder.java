package com.gemini.leetcode.problems.search;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-ladder/
 * BFS，广度优先遍历
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class WordLadder {

    class Node {
        public String word;
        public List<Node> nodeList;

        public Node(String word) {
            this.word = word;
            this.nodeList = new LinkedList<>();
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Collections.sort(wordList);

        Node beginNode = new Node(beginWord);

        // 构造邻接表
        int n = wordList.size();
        Node[] nodes = new Node[n];
        boolean hasEndWord = false;
        for (int i = 0; i < n; i++) {
            String word = wordList.get(i);
            if (!hasEndWord && word.equals(endWord)) hasEndWord = true;
            nodes[i] = new Node(word);
            if (adjacentNode(word, beginWord)) {
                beginNode.nodeList.add(nodes[i]);
                nodes[i].nodeList.add(beginNode);
            }
        }
        if (!hasEndWord) return 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (adjacentNode(nodes[i].word, nodes[j].word)) {
                    nodes[i].nodeList.add(nodes[j]);
                    nodes[j].nodeList.add(nodes[i]);
                }
            }
        }

        // 广度优先遍历
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> levelQueue = new LinkedList<>();
        Set<String> wordSet = new HashSet<>();
        queue.add(beginNode);
        int len = 0;
        while (!queue.isEmpty()) {
            // 取出该层所有节点
            while (!queue.isEmpty()) levelQueue.add(queue.poll());

            // 遍历节点
            while (!levelQueue.isEmpty()) {
                Node node = levelQueue.poll();
                wordSet.add(node.word);
                for (Node adjNode : node.nodeList) {
                    if (adjNode.word.equals(endWord)) return len + 2;
                    if (wordSet.contains(adjNode.word)) continue;
                    queue.add(adjNode);
                }
            }

            len++;
        }

        return 0;
    }

    private boolean adjacentNode(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) diffCount++;
            if (diffCount > 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(new WordLadder().ladderLength(beginWord, endWord, wordList));
    }
}
