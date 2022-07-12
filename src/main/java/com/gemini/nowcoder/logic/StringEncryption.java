package com.gemini.nowcoder.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * HJ29 字符串加解密
 * https://www.nowcoder.com/practice/2aa32b378a024755a3f251e75cbf233a?tpId=37&tqId=21252
 * 字符串水题
 *
 * @author 天何
 * @date 2022/7/11
 */
public class StringEncryption {

    private static Map<Character, Character> encMap = new HashMap<>();
    private static Map<Character, Character> decMap = new HashMap<>();

    static {
        encMap.put('a', 'B'); decMap.put('B', 'a');
        encMap.put('b', 'C'); decMap.put('C', 'b');
        encMap.put('c', 'D'); decMap.put('D', 'c');
        encMap.put('d', 'E'); decMap.put('E', 'd');
        encMap.put('e', 'F'); decMap.put('F', 'e');
        encMap.put('f', 'G'); decMap.put('G', 'f');
        encMap.put('g', 'H'); decMap.put('H', 'g');
        encMap.put('h', 'I'); decMap.put('I', 'h');
        encMap.put('i', 'J'); decMap.put('J', 'i');
        encMap.put('j', 'K'); decMap.put('K', 'j');
        encMap.put('k', 'L'); decMap.put('L', 'k');
        encMap.put('l', 'M'); decMap.put('M', 'l');
        encMap.put('m', 'N'); decMap.put('N', 'm');
        encMap.put('n', 'O'); decMap.put('O', 'n');
        encMap.put('o', 'P'); decMap.put('P', 'o');
        encMap.put('p', 'Q'); decMap.put('Q', 'p');
        encMap.put('q', 'R'); decMap.put('R', 'q');
        encMap.put('r', 'S'); decMap.put('S', 'r');
        encMap.put('s', 'T'); decMap.put('T', 's');
        encMap.put('t', 'U'); decMap.put('U', 't');
        encMap.put('u', 'V'); decMap.put('V', 'u');
        encMap.put('v', 'W'); decMap.put('W', 'v');
        encMap.put('w', 'X'); decMap.put('X', 'w');
        encMap.put('x', 'Y'); decMap.put('Y', 'x');
        encMap.put('y', 'Z'); decMap.put('Z', 'y');
        encMap.put('z', 'A'); decMap.put('A', 'z');
        encMap.put('A', 'b'); decMap.put('b', 'A');
        encMap.put('B', 'c'); decMap.put('c', 'B');
        encMap.put('C', 'd'); decMap.put('d', 'C');
        encMap.put('D', 'e'); decMap.put('e', 'D');
        encMap.put('E', 'f'); decMap.put('f', 'E');
        encMap.put('F', 'g'); decMap.put('g', 'F');
        encMap.put('G', 'h'); decMap.put('h', 'G');
        encMap.put('H', 'i'); decMap.put('i', 'H');
        encMap.put('I', 'j'); decMap.put('j', 'I');
        encMap.put('J', 'k'); decMap.put('k', 'J');
        encMap.put('K', 'l'); decMap.put('l', 'K');
        encMap.put('L', 'm'); decMap.put('m', 'L');
        encMap.put('M', 'n'); decMap.put('n', 'M');
        encMap.put('N', 'o'); decMap.put('o', 'N');
        encMap.put('O', 'p'); decMap.put('p', 'O');
        encMap.put('P', 'q'); decMap.put('q', 'P');
        encMap.put('Q', 'r'); decMap.put('r', 'Q');
        encMap.put('R', 's'); decMap.put('s', 'R');
        encMap.put('S', 't'); decMap.put('t', 'S');
        encMap.put('T', 'u'); decMap.put('u', 'T');
        encMap.put('U', 'v'); decMap.put('v', 'U');
        encMap.put('V', 'w'); decMap.put('w', 'V');
        encMap.put('W', 'x'); decMap.put('x', 'W');
        encMap.put('X', 'y'); decMap.put('y', 'X');
        encMap.put('Y', 'z'); decMap.put('z', 'Y');
        encMap.put('Z', 'a'); decMap.put('a', 'Z');
        encMap.put('0', '1'); decMap.put('1', '0');
        encMap.put('1', '2'); decMap.put('2', '1');
        encMap.put('2', '3'); decMap.put('3', '2');
        encMap.put('3', '4'); decMap.put('4', '3');
        encMap.put('4', '5'); decMap.put('5', '4');
        encMap.put('5', '6'); decMap.put('6', '5');
        encMap.put('6', '7'); decMap.put('7', '6');
        encMap.put('7', '8'); decMap.put('8', '7');
        encMap.put('8', '9'); decMap.put('9', '8');
        encMap.put('9', '0'); decMap.put('0', '9');
    }

    public static void main(String []args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String encString = br.readLine();
        String decString = br.readLine();
        br.close();

        StringBuilder builder = new StringBuilder();
        for (char ch : encString.toCharArray()) {
            builder.append(encMap.get(ch));
        }
        System.out.println(builder);

        builder = new StringBuilder();
        for (char ch : decString.toCharArray()) {
            builder.append(decMap.get(ch));
        }
        System.out.println(builder);
    }
}
