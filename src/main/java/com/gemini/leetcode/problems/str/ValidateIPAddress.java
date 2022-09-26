package com.gemini.leetcode.problems.str;

/**
 * https://leetcode.cn/problems/validate-ip-address/
 * 字符串处理
 *
 * @Author Gemini
 * 2022-09-14
 **/
public class ValidateIPAddress {

    public String validIPAddress(String queryIP) {
        if (validIPv4(queryIP)) return "IPv4";
        if (validIPv6(queryIP)) return "IPv6";
        return "Neither";
    }

    private boolean validIPv4(String ip) {
        String[] segments = ip.split("\\.", -1);
        if (segments.length != 4) return false;

        for (String segment : segments) {
            if (!validIPv4Segment(segment)) return false;
        }

        return true;
    }

    private boolean validIPv4Segment(String s) {
        int n = s.length();
        if (n == 0 || n > 3) return false;

        // leading zeros
        if (s.startsWith("0") && n > 1) return false;

        int x;
        try {
            x = Integer.parseInt(s);
            return 0 <= x && x <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validIPv6(String ip) {
        String[] segments = ip.split(":", -1);
        if (segments.length != 8) return false;

        for (String segment : segments) {
            if (!validIPv6Segment(segment)) return false;
        }

        return true;
    }

    private boolean validIPv6Segment(String s) {
        int n = s.length();
        if (n == 0 || n > 4) return false;

        char c;
        for (int i = 0; i < n; i++) {
            c = s.charAt(i);
            if (!validHexChar(c)) return false;
        }

        return true;
    }

    private boolean validHexChar(char c) {
        return '0' <= c && c <= '9' || 'a' <= c && c <= 'f' || 'A' <= c && c <= 'F';
    }

    public static void main(String[] args) {
        String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        System.out.println(new ValidateIPAddress().validIPAddress(ip));
    }
}
