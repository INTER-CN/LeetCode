package com.gemini.nowcoder.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * HJ38 求小球落地5次后所经历的路程和第5次反弹的高度
 * https://www.nowcoder.com/practice/2f6f9339d151410583459847ecc98446?tpId=37&tqId=21261
 * 数学水题
 *
 * @author 天何
 * @date 2022/7/21
 */
public class Rebounce {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        br.close();

        int n = Integer.parseInt(str);
        System.out.println(n * 2.875);
        System.out.println(n * 0.03125);
    }
}
