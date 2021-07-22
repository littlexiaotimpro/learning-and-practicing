package com.practice.nio.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileStoreService {

    // 文件在本地存储的地址
    private final Path fileStorageLocation;

    public FileStoreService() {
        this.fileStorageLocation = Paths.get("./practice/src/main/dir/uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public void storeFile(File file, String fileName) throws IOException {
        System.out.println(fileStorageLocation);
        // TODO 文件存储
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        final FileInputStream fileInputStream = new FileInputStream(file);
        Files.copy(fileInputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
    }

}
