package com.practice.nio.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStoreService {

    // 文件在本地存储的地址
    private final Path fileStorageLocation;

    public FileStoreService() {
        this.fileStorageLocation = Paths.get("./uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public void storeFile() {
        System.out.println(fileStorageLocation);
        // TODO 文件存储
    }

}
