package com.gemini.nowcoder.datastruct;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * HJ19 简单错误记录
 * https://www.nowcoder.com/practice/2baa6aba39214d6ea91a2e03dff3fbeb?tpId=37&tqId=21242
 * 有序哈希表
 *
 * @author 天何
 * @date 2022/7/31
 */
public class SimpleErrorRecorder {

    private static final int LENGTH_LIMIT = 16;
    private static final int RECORD_LIMIT = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line;
        int slashIndex, spaceIndex;
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) break;
            slashIndex = line.lastIndexOf('\\');
            if (slashIndex >= 0) {
                line = line.substring(slashIndex + 1);
            }
            spaceIndex = line.lastIndexOf(' ');
            if (spaceIndex < 0) continue;
            if (spaceIndex > LENGTH_LIMIT) {
                line = line.substring(spaceIndex - LENGTH_LIMIT);
            }
            int count = map.getOrDefault(line, 0);
            map.put(line, ++count);
        }

        int recordToSkip = (map.size() > RECORD_LIMIT) ? map.size() - RECORD_LIMIT : 0;
        int recordCount = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            recordCount++;
            if (recordCount <= recordToSkip) continue;
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        scanner.close();
    }
}
