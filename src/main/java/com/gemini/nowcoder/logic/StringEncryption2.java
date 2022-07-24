package com.gemini.nowcoder.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * HJ36 字符串加密
 * https://www.nowcoder.com/practice/e4af1fe682b54459b2a211df91a91cf3?tpId=37&tqId=21259
 * 字符串处理
 * 规则复杂，实现简单
 *
 * @author 天何
 * @date 2022/7/21
 */
public class StringEncryption2 {

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String key = br.readLine();
        String s = br.readLine();
        br.close();

        // 初始化映射表
        char[] dict = new char[26];
        int index = 0;
        Set<Character> charSet = new HashSet<>();
        for (char ch : key.toCharArray()) {
            if (!charSet.contains(ch)) {
                charSet.add(ch);
                dict[index++] = ch;
            }
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (!charSet.contains(ch)) dict[index++] = ch;
        }

        // 生成密文
        char[] enc = new char[s.length()];
        for (int i = 0; i < enc.length; i++) {
            enc[i] = dict[s.charAt(i) - 'a'];
        }

        System.out.println(new String(enc));
    }
}
