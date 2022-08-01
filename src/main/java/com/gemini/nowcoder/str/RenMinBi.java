package com.gemini.nowcoder.str;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * HJ95 人民币转换
 * https://www.nowcoder.com/practice/00ffd656b9604d1998e966d555005a4b?tpId=37&tqId=21318
 * 字符串处理
 *
 * @author 天何
 * @date 2022/7/31
 */
public class RenMinBi {

    // 一亿
    private static final long ONE_YI = 100000000L;

    // 个
    private static final int INDEX_SINGLE = 7;
    // 十
    private static final int INDEX_SHI = 6;
    // 百
    private static final int INDEX_BAI = 5;
    // 千
    private static final int INDEX_QIAN = 4;
    // 万
    private static final int INDEX_WAN = 3;
    // 十万
    private static final int INDEX_SHIWAN = 2;
    // 百万
    private static final int INDEX_BAIWAN = 1;
    // 千万
    private static final int INDEX_QIANWAN = 0;

    private static final Map<Integer, String> DIGIT_MAP = new HashMap<Integer, String>() {{
        put(1, "壹");
        put(2, "贰");
        put(3, "叁");
        put(4, "肆");
        put(5, "伍");
        put(6, "陆");
        put(7, "柒");
        put(8, "捌");
        put(9, "玖");
    }};

    private static final Map<Integer, String> DIGIT_MAP_SHI = new HashMap<Integer, String>() {{
        put(1, "");
        put(2, "贰");
        put(3, "叁");
        put(4, "肆");
        put(5, "伍");
        put(6, "陆");
        put(7, "柒");
        put(8, "捌");
        put(9, "玖");
    }};

    /**
     * 读一亿以内的数
     */
    private static String readYi(long n) {
        String prefix = "";
        if (n >= ONE_YI) {
            prefix = readYi(n / ONE_YI);
        }

        long v = n % ONE_YI;

        int[] digits = new int[8];
        for (int i = INDEX_SINGLE; i >= INDEX_QIANWAN; i--) {
            digits[i] = (int) (v % 10);
            v /= 10;
        }

        String wan = readWan(digits[INDEX_QIANWAN], digits[INDEX_BAIWAN], digits[INDEX_SHIWAN], digits[INDEX_WAN]);
        String single = readWan(digits[INDEX_QIAN], digits[INDEX_BAI], digits[INDEX_SHI], digits[INDEX_SINGLE]);

        StringBuilder builder = new StringBuilder();
        if (prefix.length() > 0) builder.append(prefix + "亿");
        if (wan.length() > 0) builder.append(wan + "万");
        if (single.length() > 0) builder.append(single);

        return builder.toString();
    }

    private static String readWan(int qian, int bai, int shi, int ge) {
        // 16种情况
        if (qian == 0 && bai == 0 && shi == 0 && ge == 0) {
            return "";
        } else if (qian == 0 && bai == 0 && shi == 0 && ge != 0) {
            return DIGIT_MAP.get(ge);
        } else if (qian == 0 && bai == 0 && shi != 0 && ge == 0) {
            return DIGIT_MAP_SHI.get(shi) + "拾";
        } else if (qian == 0 && bai == 0 && shi != 0 && ge != 0) {
            return DIGIT_MAP_SHI.get(shi) + "拾" + DIGIT_MAP.get(ge);
        } else if (qian == 0 && bai != 0 && shi == 0 && ge == 0) {
            return DIGIT_MAP.get(bai) + "佰";
        } else if (qian == 0 && bai != 0 && shi == 0 && ge != 0) {
            return DIGIT_MAP.get(bai) + "佰" + "零" + DIGIT_MAP.get(ge);
        } else if (qian == 0 && bai != 0 && shi != 0 && ge == 0) {
            return DIGIT_MAP.get(bai) + "佰" + DIGIT_MAP_SHI.get(shi) + "拾";
        } else if (qian == 0 && bai != 0 && shi != 0 && ge != 0) {
            return DIGIT_MAP.get(bai) + "佰" + DIGIT_MAP_SHI.get(shi) + "拾" + DIGIT_MAP.get(ge);
        } else if (qian != 0 && bai == 0 && shi == 0 && ge == 0) {
            return DIGIT_MAP.get(qian) + "仟";
        } else if (qian != 0 && bai == 0 && shi == 0 && ge != 0) {
            return DIGIT_MAP.get(qian) + "仟" + "零" + DIGIT_MAP.get(ge);
        } else if (qian != 0 && bai == 0 && shi != 0 && ge == 0) {
            return DIGIT_MAP.get(qian) + "仟" + "零" + DIGIT_MAP_SHI.get(shi) + "拾";
        } else if (qian != 0 && bai == 0 && shi != 0 && ge != 0) {
            return DIGIT_MAP.get(qian) + "仟" + "零" + DIGIT_MAP_SHI.get(shi) + "拾" + DIGIT_MAP.get(ge);
        } else if (qian != 0 && bai != 0 && shi == 0 && ge == 0) {
            return DIGIT_MAP.get(qian) + "仟" + DIGIT_MAP.get(bai) + "佰";
        } else if (qian != 0 && bai != 0 && shi == 0 && ge != 0) {
            return DIGIT_MAP.get(qian) + "仟" + DIGIT_MAP.get(bai) + "佰" + "零" + DIGIT_MAP.get(ge);
        } else if (qian != 0 && bai != 0 && shi != 0 && ge == 0) {
            return DIGIT_MAP.get(qian) + "仟" + DIGIT_MAP.get(bai) + "佰" + DIGIT_MAP_SHI.get(shi) + "拾";
        } else {
            return DIGIT_MAP.get(qian) + "仟" + DIGIT_MAP.get(bai) + "佰" + DIGIT_MAP_SHI.get(shi) + "拾"
                + DIGIT_MAP.get(ge);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double v = scanner.nextDouble();
        scanner.close();

        StringBuilder builder = new StringBuilder();
        builder.append("人民币");

        String intPart = readYi((long) v);
        if (intPart.length() > 0) builder.append(intPart + "元");

        int fraction = (int) (Math.round(v * 100.0) % 100);
        boolean noFraction = fraction == 0;
        if (noFraction) {
            builder.append("整");
            System.out.println(builder);
            return;
        }

        int jiao = fraction / 10;
        int fen = fraction % 10;

        if (jiao > 0) builder.append(DIGIT_MAP.get(jiao) + "角");
        if (fen > 0) builder.append(DIGIT_MAP.get(fen) + "分");

        System.out.println(builder);
    }
}
