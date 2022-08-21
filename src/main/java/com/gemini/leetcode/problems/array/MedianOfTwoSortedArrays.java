package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 * 二分查找
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;

        int k = (n - 1) / 2;
        int a = findKth(nums1, 0, n1 - 1, nums2, 0, n2 - 1, k);
        if (n % 2 == 1) return a;

        int b = findKth(nums1, 0, n1 - 1, nums2, 0, n2 - 1, k + 1);
        return ((double) a + (double) b) / 2.0;
    }

    /**
     * 查找第k小的数，k from 0
     */
    private int findKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        if (k == 0) {
            if (start1 > end1) return nums2[start2];
            if (start2 > end2) return nums1[start1];
            return Math.min(nums1[start1], nums2[start2]);
        }

        if (k == 1) {
            if (start1 > end1) return nums2[start2 + 1];
            if (start2 > end2) return nums1[start1 + 1];
            if (start1 == end1 && start2 == end2) return Math.max(nums1[start1], nums2[start2]);
            if (start1 == end1) {
                if (nums1[start1] < nums2[start2]) return nums2[start2];
                else if (nums1[start1] < nums2[start2 + 1]) return nums1[start1];
                else return nums2[start2 + 1];
            }
            if (start2 == end2) {
                if (nums2[start2] < nums1[start1]) return nums1[start1];
                else if (nums2[start2] < nums1[start1 + 1]) return nums2[start2];
                else return nums1[start1 + 1];
            }
            if (nums1[start1] > nums2[start2 + 1]) return nums2[start2 + 1];
            else if (nums2[start2] > nums1[start1 + 1]) return nums1[start1 + 1];
            else return Math.max(nums1[start1], nums2[start2]);
        }

        if (start1 > end1) return nums2[start2 + k];
        if (start2 > end2) return nums1[start1 + k];

        if (start1 == end1) {
            if (nums1[start1] > nums2[start2 + k]) return nums2[start2 + k];
            else if (nums1[start1] > nums2[start2 + k - 1]) return nums1[start1];
            else return nums2[start2 + k - 1];
        }

        if (start2 == end2) {
            if (nums2[start2] > nums1[start1 + k]) return nums1[start1 + k];
            else if (nums2[start2] > nums1[start1 + k - 1]) return nums2[start2];
            else return nums1[start1 + k - 1];
        }

        int h = k / 2;

        int index1 = Math.min(start1 + h, end1);
        int index2 = Math.min(start2 + h, end2);

        if (nums1[index1] < nums2[index2]) {
            return findKth(nums1, index1, end1, nums2, start2, end2, k - (index1 - start1));
        } else {
            return findKth(nums1, start1, end1, nums2, index2, end2, k - (index2 - start2));
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1};
        int[] nums2 = {2, 3, 4};
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1, nums2));
    }
}
