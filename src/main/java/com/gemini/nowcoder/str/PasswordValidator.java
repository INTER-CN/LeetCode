package com.gemini.nowcoder.str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * HJ20 密码验证合格程序
 * https://www.nowcoder.com/practice/184edec193864f0985ad2684fbc86841?tpId=37&tqId=21243
 * 字符串处理
 *
 * @author 天何
 * @date 2022/7/11
 */
public class PasswordValidator {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            line = reader.readLine();
            if (line == null || line.length() == 0) break;

            System.out.println(validPassword(line) ? "OK" : "NG");
        }
        reader.close();
    }

    private static boolean validPassword(String s) {
        // 密码长度
        if (s.length() <= 8) return false;

        // 字符种类
        if (s.contains(" ")) return false;
        boolean hasUppercase = false, hasLowercase = false, hasDigit = false, hasOthers = false;
        int count = 0;
        for (char ch : s.toCharArray()) {
            if ('0' <= ch && ch <= '9') {
                if (!hasDigit) {
                    hasDigit = true;
                    count++;
                }
            } else if ('a' <= ch && ch <= 'z') {
                if (!hasLowercase) {
                    hasLowercase = true;
                    count++;
                }
            } else if ('A' <= ch && ch <= 'Z') {
                if (!hasUppercase) {
                    hasUppercase = true;
                    count++;
                }
            } else {
                if (!hasOthers) {
                    hasOthers = true;
                    count++;
                }
            }
        }
        if (count < 3) return false;

        // 重复子串校验
        // map: 子串与起始索引的映射关系
        Map<String, Integer> map = new HashMap<>();
        String sub;
        for (int len = 3; len < s.length() / 2; len++) {
            for (int i = 0; i + len <= s.length(); i++) {
                sub = s.substring(i, i + len);
                if (map.containsKey(sub) && map.get(sub) + len <= i) return false;
                map.put(sub, i);
            }
        }

        return true;
    }
}
