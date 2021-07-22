package com.practice.nio.file;

import java.io.File;
import java.io.IOException;

public class FileMain {

    public static void main(String[] args) throws IOException {
        FileStoreService fileStoreService = new FileStoreService();
        File file = new File("I:\\IdeaProject\\awesome\\practice\\src\\main\\resources\\input.txt");
        fileStoreService.storeFile(file, "input.txt");
    }
}
