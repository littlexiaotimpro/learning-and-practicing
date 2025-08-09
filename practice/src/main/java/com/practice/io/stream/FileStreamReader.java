package com.practice.io.stream;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileStreamReader {

    public static void main(String[] args) {
        try (BufferedInputStream fileA = new BufferedInputStream(Files.newInputStream(Paths.get("D:\\Workspace\\Jetbrains\\idea\\learning-and-practicing\\practice\\src\\main\\dir\\file_1.txt")));
             BufferedInputStream base = new BufferedInputStream(Files.newInputStream(Paths.get("D:\\Workspace\\Jetbrains\\idea\\learning-and-practicing\\practice\\src\\main\\dir\\file_2.txt")));) {
            fileA.mark(fileA.available());
            base.mark(base.available());
            List<String> res = new ArrayList<>();
            collect(base, fileA, "--D", res);
            collect(fileA, base, "--A", res);

            for (String re : res) {
                System.out.println(re);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void collect(BufferedInputStream source, BufferedInputStream target, String tag, List<String> res) throws IOException {

        // 分批读取数据
        while (source.available() > 0) {
            // 取一行数据
            StringBuilder t = new StringBuilder();
            while (source.available() > 0) {
                int read1 = source.read();
                char read = (char) read1;
                if (read != '\n' && read != '\r') {
                    t.append(read);
                } else {
                    break;
                }
            }

            String tt = t.toString();

            if (tt.isEmpty()) {
                continue;
            }

            boolean contain = false;
            StringBuilder ta = new StringBuilder();
            while (target.available() > 0) {
                int read = target.read();
                char readA = (char) read;
                if (readA == '\n' || readA == '\r') {
                    if (ta.toString().equalsIgnoreCase(tt)) {
                        // 相等，结束循环
                        contain = true;
                        break;
                    }
                    ta = new StringBuilder();
                } else {
                    ta.append(readA);
                }
            }

            if (!ta.toString().isEmpty() && ta.toString().equalsIgnoreCase(tt)) {
                contain = true;
            }

            if (!contain) {
                res.add(tt + tag);
            }
            target.reset();
        }
        target.reset();
        source.reset();
    }
}
