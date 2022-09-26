package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/next-closest-time/
 * 模拟
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class NextClosestTime {

    public String nextClosestTime(String time) {
        boolean[] flag = new boolean[10];
        flag[time.charAt(0) - '0'] = true;
        flag[time.charAt(1) - '0'] = true;
        flag[time.charAt(3) - '0'] = true;
        flag[time.charAt(4) - '0'] = true;

        String next = time;
        while (true) {
            next = nextMinute(next);
            if (flag[next.charAt(0) - '0'] && flag[next.charAt(1) - '0']
                && flag[next.charAt(3) - '0'] && flag[next.charAt(4) - '0']) return next;
        }
    }

    private String nextMinute(String time) {
        char[] arr = time.toCharArray();
        if (time.charAt(4) != '9') {
            arr[4] = (char) (time.charAt(4) + 1);
            return new String(arr);
        }
        if (time.charAt(3) != '5') {
            arr[4] = '0';
            arr[3] = (char) (time.charAt(3) + 1);
            return new String(arr);
        }
        if (time.startsWith("23")) return "00:00";
        if (time.charAt(1) != '9') {
            arr[4] = '0';
            arr[3] = '0';
            arr[1] = (char) (time.charAt(1) + 1);
            return new String(arr);
        }
        if (time.equals("09:59")) {
            return "10:00";
        } else {
            // 19:59
            return "20:00";
        }
    }

    public static void main(String[] args) {
        String time = "23:59";
        System.out.println(new NextClosestTime().nextClosestTime(time));
    }
}
