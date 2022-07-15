package com.gemini.nowcoder.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * HJ33 整数与IP地址间的转换
 * https://www.nowcoder.com/practice/66ca0e28f90c42a196afd78cc9c496ea?tpId=37&tqId=21256
 * 字符串水题
 *
 * @author 天何
 * @date 2022/7/13
 */
public class IPConverter {

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ipString = br.readLine();
        String numString = br.readLine();
        br.close();

        String[] ips = ipString.split("\\.");
        System.out.println(
            Long.parseLong(ips[0]) * 256 * 256 * 256
            + Long.parseLong(ips[1]) * 256 * 256
            + Long.parseLong(ips[2]) * 256
            + Long.parseLong(ips[3])
        );

        long num = Long.parseLong(numString);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 4; i++) {
            stack.push((int) (num % 256));
            num /= 256;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(stack.pop());
        builder.append(".");
        builder.append(stack.pop());
        builder.append(".");
        builder.append(stack.pop());
        builder.append(".");
        builder.append(stack.pop());

        System.out.println(builder);
    }
}
