package com.gemini.nowcoder.str;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * HJ59 找出字符串中第一个只出现一次的字符
 * https://www.nowcoder.com/practice/e896d0f82f1246a3aa7b232ce38029d4?tpId=37&tqId=21282
 * 字符串处理
 *
 * @author 天何
 * @date 2022/7/24
 */
public class FirstUnrepeatedChar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        scanner.close();

        // value: 1-只出现一次, 2-出现多次
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (char ch : s.toCharArray()) {
            Integer value = map.get(ch);
            if (null == value) {
                map.put(ch, 1);
            } else if (1 == value) {
                map.put(ch, 2);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey());
                return;
            }
        }

        System.out.println("-1");
    }
}
