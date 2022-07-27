package com.gemini.nowcoder.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * HJ82 将真分数分解为埃及分数
 * https://www.nowcoder.com/practice/e0480b2c6aa24bfba0935ffcca3ccb7b?tpId=37&tqId=21305
 * 数学题
 *
 * @author 天何
 * @date 2022/7/26
 */
public class EgyptianFraction {

    private static void printEgyptianFraction(String s) {
        String[] nums = s.split("/");
        // 分子
        long molecule = Long.parseLong(nums[0]);
        // 分母
        long denominator = Long.parseLong(nums[1]);

        List<Long> denomList = new LinkedList<>();
        long n = 2L;
        while (molecule > 0) {
            while (n * molecule < denominator) n++;
            denomList.add(n);
            molecule = n * molecule - denominator;
            denominator = denominator * n;

            if (molecule == 0) break;

            if (denominator % molecule == 0) {
                denomList.add(denominator / molecule);
                break;
            }

            if (denominator % (molecule - 1) == 0) {
                denomList.add(denominator / (molecule - 1));
                denomList.add(denominator);
                break;
            }
        }

        System.out.print("1/" + denomList.get(0));
        for (int i = 1; i < denomList.size(); i++) {
            System.out.print("+1/" + denomList.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            printEgyptianFraction(s);
        }
    }
}
