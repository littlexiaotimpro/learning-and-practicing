package com.practice.io;

import com.practice.io.reader.FileReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        try {
            fileReader.load();
            fileReader.loadBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
