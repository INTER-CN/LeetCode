package com.gemini.langtest.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @Author Gemini
 * 2022-08-29
 **/
public class FileUtil {

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/jianbinteng/Jianbin/files/big_data.txt"));
        Random random = new Random();

        for (int i = 1; i <= 100_000_000; i++) {
            writer.write(Integer.toString(random.nextInt(1_000_000_000)));
            writer.newLine();
            if (i % 1_000_000 == 0) {
                writer.flush();
                System.out.println("progress: " + (i / 1_000_000));
            }
        }

        writer.close();
    }
}
