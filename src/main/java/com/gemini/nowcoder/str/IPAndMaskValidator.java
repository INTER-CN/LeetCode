package com.gemini.nowcoder.str;

import java.util.Scanner;

/**
 * HJ18 识别有效的IP地址和掩码并进行分类统计
 * https://www.nowcoder.com/practice/de538edd6f7e4bc3a5689723a7435682?tpId=37&tqId=21241
 * 字符串处理
 *
 * @author 天何
 * @date 2022/7/27
 */
public class IPAndMaskValidator {

    private static final int A = 0;
    private static final int B = 1;
    private static final int C = 2;
    private static final int D = 3;
    private static final int E = 4;
    private static final int X = 5;

    static class IPInfo {
        public boolean isValid;
        public int type;
        public boolean isPrivate;

        public IPInfo(boolean isValid) {
            this.isValid = isValid;
        }
    }

    private static IPInfo validateIP(String s) {
        if (s == null || s.length() < 7) return new IPInfo(false);
        String[] segments = s.split("\\.");
        if (segments.length != 4) return new IPInfo(false);

        int[] nums = new int[4];
        try {
            for (int i = 0; i < 4; i++) nums[i] = Integer.parseInt(segments[i]);
        } catch (NumberFormatException e) {
            return new IPInfo(false);
        }

        for (int num : nums) {
            if (num < 0 || num > 255) return new IPInfo(false);
        }

        IPInfo ipInfo = new IPInfo(true);
        if (1 <= nums[0] && nums[0] <= 126) {
            ipInfo.type = A;
        } else if (128 <= nums[0] && nums[0] <= 191) {
            ipInfo.type = B;
        } else if (192 <= nums[0] && nums[0] <= 223) {
            ipInfo.type = C;
        } else if (224 <= nums[0] && nums[0] <= 239) {
            ipInfo.type = D;
        } else if (240 <= nums[0] && nums[0] <= 255) {
            ipInfo.type = E;
        } else {
            ipInfo.type = X;
        }
        ipInfo.isPrivate = isPrivateIP(nums);

        return ipInfo;
    }

    private static boolean isPrivateIP(int[] nums) {
        if (nums[0] == 10) return true;
        if (nums[0] == 172 && 16 <= nums[1] && nums[1] <= 31) return true;
        if (nums[0] == 192 && nums[1] == 168) return true;
        return false;
    }

    private static boolean validateMask(String s) {
        if (s == null || s.length() < 7) return false;
        String[] segments = s.split("\\.");
        if (segments.length != 4) return false;

        int[] nums = new int[4];
        try {
            for (int i = 0; i < 4; i++) nums[i] = Integer.parseInt(segments[i]);
        } catch (NumberFormatException e) {
            return false;
        }

        for (int num : nums) {
            if (num < 0 || num > 255) return false;
        }

        StringBuilder builder = new StringBuilder();
        for (int num : nums) {
            String s1 = Integer.toBinaryString(num);
            builder.append(String.format("%8s", s1).replace(" ", "0"));
        }

        String binaryMask = builder.toString();
        if (binaryMask.startsWith("0")) return false;
        int index = 0;
        while (index < binaryMask.length() && binaryMask.charAt(index) == '1') index++;
        if (index == binaryMask.length()) return false;
        while (index < binaryMask.length() && binaryMask.charAt(index) == '0') index++;
        return index == binaryMask.length();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] typeCount = new int[E + 1];
        int errorCount = 0;
        int privateIPCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() == 0) break;
            String[] segments = line.split("~");
            IPInfo ipInfo = validateIP(segments[0]);
            boolean validMask = validateMask(segments[1]);

            // X类IP地址忽略
            if (ipInfo.type == X) continue;

            if (!ipInfo.isValid || !validMask) {
                errorCount++;
            } else {
                if (A <= ipInfo.type && ipInfo.type <= E) {
                    typeCount[ipInfo.type]++;
                }
                if (ipInfo.isPrivate) {
                    privateIPCount++;
                }
            }
        }

        scanner.close();

        for (int i = A; i <= E; i++) System.out.print(typeCount[i] + " ");
        System.out.println(errorCount + " " + privateIPCount);
    }
}
