package com.gemini.leetcode.problems.datastruct;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/encode-and-decode-strings/
 * 序列化和反序列化
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null) return null;
        if (strs.size() == 0) return "";
        StringBuilder builder = new StringBuilder();
        for (String s : strs) {
            builder.append(s.length());
            builder.append("_");
            builder.append(s);
        }
        return builder.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        if (s == null) return null;
        if (s.length() == 0) return Collections.emptyList();

        int index, len;
        List<String> result = new LinkedList<>();
        while (s.length() > 0) {
            index = s.indexOf('_');
            len = Integer.parseInt(s.substring(0, index));
            result.add(s.substring(index + 1, index + 1 + len));
            s = s.substring(index + 1 + len);
        }

        return result;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        List<String> strs = Arrays.asList("Hello", "World");
        String s = codec.encode(strs);
        System.out.println(s);
        List<String> result = codec.decode(s);
        System.out.println(new Gson().toJson(result));
    }
}
