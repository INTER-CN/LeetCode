package com.gemini.nowcoder.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * HJ17 坐标移动
 * https://www.nowcoder.com/practice/119bcca3befb405fbe58abe9c532eb29?tpId=37&tqId=21240
 * 字符串解析
 *
 * @author 天何
 * @date 2022/7/11
 */
public class CoordinateMovement {

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        reader.close();

        // 分隔命令
        String[] actions = str.split(";");

        // 遍历并移动
        int x = 0, y = 0;
        for (String action : actions) {
            int[] moves = extractAction(action);
            if (moves != null) {
                x += moves[0];
                y += moves[1];
            }
        }

        System.out.println(x + "," + y);
    }

    private static int[] extractAction(String action) {
        if (action == null || action.length() < 2) return null;
        if (!action.startsWith("W") && !action.startsWith("A") && !action.startsWith("S") && !action.startsWith("D"))
            return null;
        String numStr = action.substring(1);
        try {
            int num = Integer.parseInt(numStr);
            switch (action.charAt(0)) {
                case 'W':
                    return new int[]{0, num};
                case 'S':
                    return new int[]{0, -num};
                case 'A':
                    return new int[]{-num, 0};
                case 'D':
                    return new int[]{num, 0};
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
