package com.gemini.leetcode.problems.recur;

/**
 * https://leetcode.cn/problems/integer-to-roman/
 * 递归处理
 *
 * @Author Gemini
 * 2022-08-17
 **/
public class Int2Roman {

    public String intToRoman(int num) {
        if (num < 10) return getSingleValue(num);
        if (num < 100) return getTensValue(num / 10) + intToRoman(num % 10);
        if (num < 1000) return getHundredsValue(num / 100) + intToRoman(num % 100);
        return getThousandsValue(num / 1000) + intToRoman(num % 1000);
    }

    private String getSingleValue(int num) {
        switch (num) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            default:
                return "";
        }
    }

    private String getTensValue(int num) {
        switch (num) {
            case 1:
                return "X";
            case 2:
                return "XX";
            case 3:
                return "XXX";
            case 4:
                return "XL";
            case 5:
                return "L";
            case 6:
                return "LX";
            case 7:
                return "LXX";
            case 8:
                return "LXXX";
            case 9:
                return "XC";
            default:
                return "";
        }
    }

    private String getHundredsValue(int num) {
        switch (num) {
            case 1:
                return "C";
            case 2:
                return "CC";
            case 3:
                return "CCC";
            case 4:
                return "CD";
            case 5:
                return "D";
            case 6:
                return "DC";
            case 7:
                return "DCC";
            case 8:
                return "DCCC";
            case 9:
                return "CM";
            default:
                return "";
        }
    }

    private String getThousandsValue(int num) {
        switch (num) {
            case 1:
                return "M";
            case 2:
                return "MM";
            case 3:
                return "MMM";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Int2Roman().intToRoman(1994));
    }
}
