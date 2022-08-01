package com.gemini.nowcoder.easy;

import java.util.*;

/**
 * HJ3 明明的随机数
 * https://www.nowcoder.com/practice/3245215fffb84b7b81285493eae92ff0?tpId=37&tqId=21226
 * "较难"的水题
 *
 * @author 天何
 * @date 2022/7/27
 */
public class MingMing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) set.add(scanner.nextInt());
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        for (Integer item : list) {
            System.out.println(item);
        }
    }
}
