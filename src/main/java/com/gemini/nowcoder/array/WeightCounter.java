package com.gemini.nowcoder.array;


import java.util.*;

/**
 * HJ41 称砝码
 * https://www.nowcoder.com/practice/f9a4c19050fc477e9e27eb75f3bfd49c?tpId=37&tqId=21264
 * 哈希集合
 *
 * @author 天何
 * @date 2022/7/21
 */
public class WeightCounter {

    public static void main(String[] args) throws Exception {
        // input
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] m = new int[n];
        int[] x = new int[n];
        for (int i = 0; i < n; i++) m[i] = scanner.nextInt();
        for (int i = 0; i < n; i++) x[i] = scanner.nextInt();
        scanner.close();

        int weightNum = 0;
        for (int num : x) weightNum += num;
        int[] weights = new int[weightNum];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < x[i]; j++) {
                weights[index++] = m[i];
            }
        }

        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int weight : weights) {
            List<Integer> list = new ArrayList<>(set);
            for (int item : list) {
                set.add(item + weight);
            }
        }

        System.out.println(set.size());
    }
}
