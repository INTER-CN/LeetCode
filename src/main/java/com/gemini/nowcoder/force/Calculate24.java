package com.gemini.nowcoder.force;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * HJ67 24点游戏算法
 * https://www.nowcoder.com/practice/fbc417f314f745b1978fc751a54ac8cb?tpId=37&tqId=21290
 * BFS + 暴力
 *
 * @author 天何
 * @date 2022/7/26
 */
public class Calculate24 {

    private static final double TARGET_VALUE = 24.0;

    private static boolean calculateBySequence(double w, double x, double y, double z) {
        Queue<Double> queue = new LinkedList<>();
        Queue<Double> levelQueue = new LinkedList<>();
        queue.add(w);
        double[] arr = {x, y, z};
        for (double v : arr) {
            while (!queue.isEmpty()) levelQueue.add(queue.poll());
            while (!levelQueue.isEmpty()) {
                Double value = levelQueue.poll();
                queue.add(value + v);
                queue.add(value - v);
                queue.add(value * v);
                queue.add(value / v);
                queue.add(v - value);
                queue.add(v / value);
            }
        }

        while (!queue.isEmpty()) {
            Double value = queue.poll();
            if (value == TARGET_VALUE) return true;
        }

        return false;
    }

    private static boolean calculateBy2Pairs(double w, double x, double y, double z) {
        double result;

        result = (w + x) * (y + z);
        if (result == TARGET_VALUE) return true;
        result = (w + x) / (y + z);
        if (result == TARGET_VALUE) return true;

        result = (w + x) * (y - z);
        if (result == TARGET_VALUE) return true;
        result = (w + x) / (y - z);
        if (result == TARGET_VALUE) return true;

        result = (w - x) * (y - z);
        if (result == TARGET_VALUE) return true;
        result = (w - x) / (y - z);
        if (result == TARGET_VALUE) return true;

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();
        scanner.close();

        if (calculateBySequence(a, b, c, d)) { System.out.println(true); return; }
        if (calculateBySequence(a, b, d, c)) { System.out.println(true); return; }
        if (calculateBySequence(a, c, b, d)) { System.out.println(true); return; }
        if (calculateBySequence(a, c, d, b)) { System.out.println(true); return; }
        if (calculateBySequence(a, d, b, c)) { System.out.println(true); return; }
        if (calculateBySequence(a, d, c, b)) { System.out.println(true); return; }
        if (calculateBySequence(b, a, c, d)) { System.out.println(true); return; }
        if (calculateBySequence(b, a, d, c)) { System.out.println(true); return; }
        if (calculateBySequence(b, c, a, d)) { System.out.println(true); return; }
        if (calculateBySequence(b, c, d, a)) { System.out.println(true); return; }
        if (calculateBySequence(b, d, a, c)) { System.out.println(true); return; }
        if (calculateBySequence(b, d, c, a)) { System.out.println(true); return; }
        if (calculateBySequence(c, a, b, d)) { System.out.println(true); return; }
        if (calculateBySequence(c, a, d, b)) { System.out.println(true); return; }
        if (calculateBySequence(c, b, a, d)) { System.out.println(true); return; }
        if (calculateBySequence(c, b, d, a)) { System.out.println(true); return; }
        if (calculateBySequence(c, d, a, b)) { System.out.println(true); return; }
        if (calculateBySequence(c, d, b, a)) { System.out.println(true); return; }
        if (calculateBySequence(d, a, b, c)) { System.out.println(true); return; }
        if (calculateBySequence(d, a, c, b)) { System.out.println(true); return; }
        if (calculateBySequence(d, b, a, c)) { System.out.println(true); return; }
        if (calculateBySequence(d, b, c, a)) { System.out.println(true); return; }
        if (calculateBySequence(d, c, a, b)) { System.out.println(true); return; }
        if (calculateBySequence(d, c, b, a)) { System.out.println(true); return; }

        if (calculateBy2Pairs(a, b, c, d)) { System.out.println(true); return; }
        if (calculateBy2Pairs(a, b, d, c)) { System.out.println(true); return; }
        if (calculateBy2Pairs(a, c, b, d)) { System.out.println(true); return; }
        if (calculateBy2Pairs(a, c, d, b)) { System.out.println(true); return; }
        if (calculateBy2Pairs(a, d, b, c)) { System.out.println(true); return; }
        if (calculateBy2Pairs(a, d, c, b)) { System.out.println(true); return; }
        if (calculateBy2Pairs(b, a, c, d)) { System.out.println(true); return; }
        if (calculateBy2Pairs(b, a, d, c)) { System.out.println(true); return; }
        if (calculateBy2Pairs(b, c, a, d)) { System.out.println(true); return; }
        if (calculateBy2Pairs(b, c, d, a)) { System.out.println(true); return; }
        if (calculateBy2Pairs(b, d, a, c)) { System.out.println(true); return; }
        if (calculateBy2Pairs(b, d, c, a)) { System.out.println(true); return; }
        if (calculateBy2Pairs(c, a, b, d)) { System.out.println(true); return; }
        if (calculateBy2Pairs(c, a, d, b)) { System.out.println(true); return; }
        if (calculateBy2Pairs(c, b, a, d)) { System.out.println(true); return; }
        if (calculateBy2Pairs(c, b, d, a)) { System.out.println(true); return; }
        if (calculateBy2Pairs(c, d, a, b)) { System.out.println(true); return; }
        if (calculateBy2Pairs(c, d, b, a)) { System.out.println(true); return; }
        if (calculateBy2Pairs(d, a, b, c)) { System.out.println(true); return; }
        if (calculateBy2Pairs(d, a, c, b)) { System.out.println(true); return; }
        if (calculateBy2Pairs(d, b, a, c)) { System.out.println(true); return; }
        if (calculateBy2Pairs(d, b, c, a)) { System.out.println(true); return; }
        if (calculateBy2Pairs(d, c, a, b)) { System.out.println(true); return; }
        if (calculateBy2Pairs(d, c, b, a)) { System.out.println(true); return; }

        System.out.println(false);
    }
}
