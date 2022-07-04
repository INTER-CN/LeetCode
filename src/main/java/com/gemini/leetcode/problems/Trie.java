package com.gemini.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/implement-trie-prefix-tree/
 * 树形数据结构
 *
 * @author 天何
 * @date 2022/7/4
 */
public class Trie {

    class TrieNode {
        char val;
        List<TrieNode> children;
        boolean wordEnd;

        public TrieNode(char val) {
            this.val = val;
            this.children = new ArrayList<>();
            this.wordEnd = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode('a');
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        char[] chars = word.toCharArray();

        TrieNode current = root;
        for (char target : chars) {
            TrieNode child = searchChildren(current, target);
            if (child == null) {
                child = new TrieNode(target);
                current.children.add(child);
            }
            current = child;
        }

        current.wordEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = searchEnd(word);
        return node != null && node.wordEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchEnd(prefix);
        return node != null;
    }

    private TrieNode searchChildren(TrieNode node, char target) {
        if (node == null || node.children == null || node.children.size() == 0) {
            return null;
        }

        for (TrieNode child : node.children) {
            if (child.val == target) {
                return child;
            }
        }

        return null;
    }

    private TrieNode searchEnd(String word) {
        if (word == null || word.length() == 0) {
            return null;
        }

        char[] chars = word.toCharArray();

        TrieNode current = root;
        for (char target : chars) {
            TrieNode child = searchChildren(current, target);
            if (child == null) return null;
            current = child;
        }

        return current;
    }

    public static void main(String[] args) {
        Trie obj = new Trie();

        System.out.println(obj.search("app"));
        System.out.println(obj.search("apple"));
        System.out.println(obj.search("apply"));
        System.out.println(obj.search("appl"));
        System.out.println();

        obj.insert("app");
        System.out.println(obj.search("app"));
        System.out.println(obj.search("apple"));
        System.out.println(obj.search("apply"));
        System.out.println(obj.search("appl"));
        System.out.println();

        obj.insert("apple");
        System.out.println(obj.search("app"));
        System.out.println(obj.search("apple"));
        System.out.println(obj.search("apply"));
        System.out.println(obj.search("appl"));
        System.out.println();

        obj.insert("apply");
        System.out.println(obj.search("app"));
        System.out.println(obj.search("apple"));
        System.out.println(obj.search("apply"));
        System.out.println(obj.search("appl"));
        System.out.println();

        obj.insert("appl");
        System.out.println(obj.search("app"));
        System.out.println(obj.search("apple"));
        System.out.println(obj.search("apply"));
        System.out.println(obj.search("appl"));
        System.out.println();
    }

}
