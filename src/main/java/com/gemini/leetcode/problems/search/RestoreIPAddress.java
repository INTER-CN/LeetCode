package com.gemini.leetcode.problems.search;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/restore-ip-addresses/
 * 深度优先搜索
 *
 * @Author Gemini
 * 2022-08-22
 **/
public class RestoreIPAddress {

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new LinkedList<>();
        List<String> segmentList = new LinkedList<>();
        restoreIpAddressesByDFS(result, segmentList, s);
        return result;
    }

    private void restoreIpAddressesByDFS(List<String> result, List<String> segmentList, String s) {
        int num;
        if (segmentList.size() == 3) {
            if (s.length() > 3 || s.length() == 0) return;
            if (s.length() > 1 && s.charAt(0) == '0') return;
            num = Integer.parseInt(s);
            if (num > 255) return;
            segmentList.add(s);
            result.add(String.join(".", segmentList));
            return;
        }

        // get 1 digit
        if (s.length() > 3 - segmentList.size()) {
            List<String> segmentList1 = new LinkedList<>(segmentList);
            segmentList1.add(s.substring(0, 1));
            restoreIpAddressesByDFS(result, segmentList1, s.substring(1));
        }

        // get 2 digit
        if (s.length() > 4 - segmentList.size() && s.charAt(0) != '0') {
            List<String> segmentList2 = new LinkedList<>(segmentList);
            segmentList2.add(s.substring(0, 2));
            restoreIpAddressesByDFS(result, segmentList2, s.substring(2));
        }

        // get 3 digit
        if (s.length() > 5 - segmentList.size() && (s.charAt(0) == '1' || s.charAt(0) == '2')) {
            num = Integer.parseInt(s.substring(0, 3));
            if (num < 256) {
                List<String> segmentList3 = new LinkedList<>(segmentList);
                segmentList3.add(s.substring(0, 3));
                restoreIpAddressesByDFS(result, segmentList3, s.substring(3));
            }
        }
    }

    public static void main(String[] args) {
        String s = "101023";
        System.out.println(new RestoreIPAddress().restoreIpAddresses(s));
    }
}
