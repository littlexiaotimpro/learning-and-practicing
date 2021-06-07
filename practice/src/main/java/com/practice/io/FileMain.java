package com.practice.io;

import com.practice.io.reader.FileReader;

import java.io.IOException;

public class FileMain {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        try {
            fileReader.loadDir(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
