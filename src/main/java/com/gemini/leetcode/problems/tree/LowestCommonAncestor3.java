package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 * 二叉树（带父节点）
 *
 * @author 天何
 * @date 2022/7/9
 */
public class LowestCommonAncestor3 {

    public Node lowestCommonAncestor(Node p, Node q) {
        if (p == null || q == null) return null;

        List<Node> pList = new ArrayList<>();
        while (p != null) {
            pList.add(p);
            p = p.parent;
        }

        List<Node> qList = new ArrayList<>();
        while (q != null) {
            qList.add(q);
            q = q.parent;
        }

        int i = pList.size() - 1, j = qList.size() - 1;
        if (pList.get(i) != qList.get(j)) return null;

        while (i >= 0 && j >= 0 && pList.get(i) == qList.get(j)) {
            i--;
            j--;
        }

        return pList.get(i + 1);
    }
}
