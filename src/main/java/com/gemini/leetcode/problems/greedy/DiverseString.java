package com.gemini.leetcode.problems.greedy;

/**
 * https://leetcode.cn/problems/longest-happy-string/
 * 贪心
 *
 * @Author Gemini
 * 2022-08-24
 **/
public class DiverseString {

    private static final int THRESHOLD = 2;

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder builder = new StringBuilder();

        int[] nums = {a, b, c};
        int currentIndex = -1, currentCount = 0, maxIndex;
        while (hasMore(nums, currentIndex, currentCount)) {
            maxIndex = getMaxIndex(nums, currentIndex, currentCount);
            builder.append((char) ('a' + maxIndex));
            nums[maxIndex]--;

            if (maxIndex == currentIndex) {
                currentCount++;
            } else {
                currentIndex = maxIndex;
                currentCount = 1;
            }
        }

        return builder.toString();
    }

    private boolean hasMore(int[] nums, int currentIndex, int currentCount) {
        int count0 = 0;
        for (int num : nums) {
            if (num == 0) count0++;
        }
        if (count0 < 2) {
            return true;
        } else if (count0 == 2) {
            return currentIndex < 0 || nums[currentIndex] == 0 || currentCount < THRESHOLD;
        } else {
            return false;
        }
    }

    private int getMaxIndex(int[] nums, int currentIndex, int currentCount) {
        if (currentIndex < 0 || currentCount < THRESHOLD) {
            if (nums[0] >= nums[1] && nums[0] >= nums[2]) return 0;
            else if (nums[1] >= nums[0] && nums[1] >= nums[2]) return 1;
            else return 2;
        } else if (currentIndex == 0) {
            return nums[1] >= nums[2] ? 1 : 2;
        } else if (currentIndex == 1) {
            return nums[0] >= nums[2] ? 0 : 2;
        } else if (currentIndex == 2) {
            return nums[0] >= nums[1] ? 0 : 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int a = 0, b = 11, c = 8;
        System.out.println(new DiverseString().longestDiverseString(a, b, c));
    }
}
