package com.gemini.nowcoder.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * HJ27 查找兄弟单词
 * https://www.nowcoder.com/practice/03ba8aeeef73400ca7a37a5f3370fe68?tpId=37&tqId=21250
 * 字符串操作
 *
 * @author 天何
 * @date 2022/7/11
 */
public class FindBrotherWord {

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        br.close();

        String[] items = s.split(" ");
        int n = Integer.parseInt(items[0]);
        String word = items[n + 1];
        int k = Integer.parseInt(items[n + 2]);

        ArrayList<String> brotherList = new ArrayList<>();
        String wordBigBrother = findBigBrother(word);
        for (int i = 1; i <= n; i++) {
            if (wordBigBrother.equals(findBigBrother(items[i])) && !word.equals(items[i])) {
                brotherList.add(items[i]);
            }
        }

        Collections.sort(brotherList);

        System.out.println(brotherList.size());
        if (k <= brotherList.size()) System.out.println(brotherList.get(k - 1));
    }

    /**
     * 对单词中的字母排序
     */
    private static String findBigBrother(String word) {
        ArrayList<Character> charList = new ArrayList<>();
        for (char ch : word.toCharArray()) charList.add(ch);
        Collections.sort(charList);
        StringBuilder builder = new StringBuilder();
        for (Character ch : charList) builder.append(ch);
        return builder.toString();
    }
}
