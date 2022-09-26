package com.gemini.leetcode.problems.greedy;

/**
 * https://leetcode.cn/problems/sentence-screen-fitting/
 * 贪心 + 模拟
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class SentenceScreenFitting {

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int n = sentence.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            if (sentence[i].length() > cols) return 0;
            nums[i] = sentence[i].length();
        }

        int x = 0, y = 0;
        int count = 0;
        while (x < rows && y < cols) {
            int index = 0;
            while (index < n) {
                if (y + nums[index] <= cols) {
                    y += (nums[index] + 1);
                } else {
                    // new line
                    x++;
                    y = nums[index] + 1;
                }
                if (y >= cols) {
                    y = 0;
                    x++;
                }
                index++;
                if (x >= rows) break;
            }
            if (index < n) break;
            if (x >= rows && y > 0) break;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        String[] sentence = {"hello", "leetcode"};
        int rows = 1;
        int cols = 10;
        System.out.println(new SentenceScreenFitting().wordsTyping(sentence, rows, cols));
    }
}
