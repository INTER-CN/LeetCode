package com.gemini.nowcoder.search;

import java.util.Scanner;

/**
 * HJ107 求解立方根
 * https://www.nowcoder.com/practice/caf35ae421194a1090c22fe223357dca?tpId=37&tqId=21330
 * 二分法
 *
 * @author 天何
 * @date 2022/7/26
 */
public class CubicRoot {

    private static final double MAX_X = 2.8;

    private static final double PRECISION = 0.0001;

    private static double getCubicRoot(double y, double min, double max) {
        double x = (min + max) / 2;
        double yy = x * x * x;
        if (Math.abs(yy - y) < PRECISION) return Math.round(x * 10) / 10.0;
        else if (yy > y) return getCubicRoot(y, min, x);
        else return getCubicRoot(y, x, max);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double y = scanner.nextDouble();
        scanner.close();

        if (y == 0.0) {
            System.out.println(0.0);
            return;
        }

        int sign = y > 0.0 ? 1 : -1;

        y = Math.abs(y);
        if (y == 1.0) {
            System.out.println(sign * 1.0);
            return;
        }

        double x, yy;

        if (y > 1.0) {
            System.out.println(sign * getCubicRoot(y, 1.0, MAX_X));
        } else {
            System.out.println(sign * getCubicRoot(y, 0.0, 1.0));
        }
    }
}
