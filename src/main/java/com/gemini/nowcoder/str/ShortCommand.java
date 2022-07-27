package com.gemini.nowcoder.str;

import java.util.*;

/**
 * HJ66 配置文件恢复
 * https://www.nowcoder.com/practice/ca6ac6ef9538419abf6f883f7d6f6ee5?tpId=37&tqId=21289
 * 字符串水题
 *
 * @author 天何
 * @date 2022/7/26
 */
public class ShortCommand {

    private static final String UNKNOWN_MESSAGE = "unknown command";

    private static final Map<String, String> commandMap = new HashMap<String, String>() {{
        put("reset board", "board fault");
        put("board add", "where to add");
        put("board delete", "no board at all");
        put("reboot backplane", "impossible");
        put("backplane abort", "install first");
    }};

    private static final List<String> KEYWORD1_LIST = Arrays.asList("reset", "board", "reboot", "backplane");
    private static final List<String> KEYWORD2_LIST = Arrays.asList("board", "add", "delete", "backplane", "abort");

    private static String execute(String s) {
        String[] commands = s.split(" ");

        if (commands.length > 2) {
            return UNKNOWN_MESSAGE;
        }

        if (commands.length == 1) {
            if ("reset".startsWith(commands[0])) {
                return "reset what";
            } else {
                return UNKNOWN_MESSAGE;
            }
        }

        List<String> word1List = new ArrayList<>();
        for (String keyword : KEYWORD1_LIST) {
            if (keyword.startsWith(commands[0])) {
                word1List.add(keyword);
            }
        }

        List<String> word2List = new ArrayList<>();
        for (String keyword : KEYWORD2_LIST) {
            if (keyword.startsWith(commands[1])) {
                word2List.add(keyword);
            }
        }

        String command, output = null;
        for (String word1 : word1List) {
            for (String word2 : word2List) {
                command = word1 + " " + word2;
                if (commandMap.containsKey(command)) {
                    if (output != null) return UNKNOWN_MESSAGE;
                    else output = commandMap.get(command);
                }
            }
        }

        return output == null ? UNKNOWN_MESSAGE : output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        while (scanner.hasNext()) {
            s = scanner.nextLine();
            if (s.length() == 0) break;
            System.out.println(execute(s));
        }
        scanner.close();
    }
}
