package com.gemini.leetcode.problems.datastruct;

import java.util.*;

/**
 * https://leetcode.cn/problems/alien-dictionary/
 * 有向图
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class AlienDictionary {

    class Node {
        public char letter;
        public int in;
        public int out;
        public Set<Node> nextSet;

        public Node(char letter) {
            this.letter = letter;
            this.in = 0;
            this.out = 0;
            this.nextSet = new HashSet<>();
        }
    }

    public String alienOrder(String[] words) {
        // 检测是否有子串问题
        // If the first min(s.length, t.length) letters are the same,
        // then s is smaller if and only if s.length < t.length.
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].startsWith(words[i + 1]) && words[i].length() > words[i + 1].length()) return "";
        }

        // 初始化图节点
        Node[] nodes = new Node[26];
        int index;
        int letterCount = 0;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                index = c - 'a';
                if (nodes[index] == null) {
                    nodes[index] = new Node(c);
                    letterCount++;
                }
            }
        }
        List<String> wordList = Arrays.asList(words);

        // 递归构造图
        buildOrder(nodes, wordList);

        // 广度优先遍历
        StringBuilder builder = new StringBuilder();
        boolean hasProgress;
        int visitCount = 0;
        while (true) {
            hasProgress = false;
            for (int i = 0; i < 26; i++) {
                if (nodes[i] != null && nodes[i].in == 0) {
                    for (Node nextNode : nodes[i].nextSet) nextNode.in--;
                    builder.append(nodes[i].letter);
                    nodes[i] = null;
                    visitCount++;
                    hasProgress = true;
                    if (visitCount == letterCount) return builder.toString();
                }
            }
            if (!hasProgress) return "";
        }
    }

    private void buildOrder(Node[] nodes, List<String> wordList) {
        if (wordList == null || wordList.size() < 2) return;

        // 取首字母排序
        char pre, cur = 0;
        Map<Character, List<String>> subWordListMap = new HashMap<>();
        for (String word : wordList) {
            pre = cur;
            cur = word.charAt(0);
            int preIndex = pre - 'a';
            int curIndex = cur - 'a';
            if (pre != 0 && pre != cur && !nodes[preIndex].nextSet.contains(nodes[curIndex])) {
                nodes[preIndex].nextSet.add(nodes[curIndex]);
                nodes[preIndex].out++;
                nodes[curIndex].in++;
            }
            if (word.length() > 1) {
                List<String> subWordList = subWordListMap.computeIfAbsent(cur, k -> new LinkedList<>());
                subWordList.add(word.substring(1));
            }
        }

        // 递归处理子串
        for (List<String> subWordList : subWordListMap.values()) {
            buildOrder(nodes, subWordList);
        }
    }

    public static void main(String[] args) {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(new AlienDictionary().alienOrder(words));
    }
}
