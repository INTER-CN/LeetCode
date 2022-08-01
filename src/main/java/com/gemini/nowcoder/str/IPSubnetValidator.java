package com.gemini.nowcoder.str;

import java.util.Scanner;

/**
 * HJ39 判断两个IP是否属于同一子网
 * https://www.nowcoder.com/practice/34a597ee15eb4fa2b956f4c595f03218?tpId=37&tqId=21262
 * 字符串处理
 *
 * @author 天何
 * @date 2022/7/31
 */
public class IPSubnetValidator {

    /**
     * 把IP地址或子网掩码转换成数值，如果非法，则返回-1
     */
    private static long calculateIPValue(String s, boolean isMask) {
        String[] segments = s.split("\\.");
        if (segments.length != 4) return -1;

        int[] nums = new int[4];
        try {
            for (int i = 0; i < 4; i++) nums[i] = Integer.parseInt(segments[i]);
        } catch (NumberFormatException e) {
            return -1;
        }

        for (int i = 0; i < 4; i++) {
            if (nums[i] < 0 || nums[i] > 255) return -1;
        }

        long value = nums[0] * 256 * 256 * 256L + nums[1] * 256 * 256L + nums[2] * 256L + nums[3];

        if (isMask) {
            long maskValue = value;
            int digitCount = 0;
            while (maskValue % 2 == 0) {
                maskValue /= 2;
                digitCount++;
            }
            while (maskValue % 2 == 1) {
                maskValue /= 2;
                digitCount++;
            }
            if (maskValue != 0 || digitCount != 32) return -1;
        }

        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String mask = scanner.nextLine();
        String ip1 = scanner.nextLine();
        String ip2 = scanner.nextLine();
        scanner.close();

        long maskValue = calculateIPValue(mask, true);
        if (maskValue < 0) {
            System.out.println(1);
            return;
        }
        long ip1Value = calculateIPValue(ip1, false);
        if (ip1Value < 0) {
            System.out.println(1);
            return;
        }
        long ip2Value = calculateIPValue(ip2, false);
        if (ip2Value < 0) {
            System.out.println(1);
            return;
        }

        long maskIp1 = maskValue & ip1Value;
        long maskIp2 = maskValue & ip2Value;

        System.out.println(maskIp1 == maskIp2 ? 0 : 2);
    }
}
