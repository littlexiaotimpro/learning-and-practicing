package com.practice.io.reader;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;

public class FileReader extends AbstractFileReader {

    @Override
    public void load(ClassLoader classLoader) {
        try {
            Enumeration<URL> urls = (classLoader != null ?
                    classLoader.getResources(RESOURCE_LOCATION) :
                    ClassLoader.getSystemResources(RESOURCE_LOCATION));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                System.out.println(url.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(String filepath) throws IOException {
        File file = new File(filepath == null ? FILE_LOCATION_PATH : filepath);
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            is = new FileInputStream(file);
            bis = new BufferedInputStream(is);
            int read;
            while ((read = bis.read()) != -1) {
                System.out.print((char) read);
            }
            System.out.println("\n");
        } finally {
            if (bis != null) {
                bis.close();
                is.close();
            }
        }
    }

    @Override
    public void loadBytes(String filepath) throws IOException {
        File file = new File(filepath == null ? FILE_LOCATION_PATH : filepath);
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            is = new FileInputStream(file);
            bis = new BufferedInputStream(is);
            int available = bis.available();
            byte[] res = new byte[available];
            int read = bis.read(res);
            System.out.println(available + " : " + read);
            System.out.println(Arrays.toString(res));
        } finally {
            if (bis != null) {
                bis.close();
                is.close();
            }
        }
    }

    @Override
    public void loadAllFile(String dirPath) throws IOException {
        File file = new File(dirPath == null ? FILE_DIR : dirPath);
        if (file.isDirectory()) {
            String[] list = file.list();
            System.out.println(Arrays.toString(list));
            File[] files = file.listFiles();
            assert files != null;
            for (File f : files) {
                String absolutePath = f.getAbsolutePath();
                System.out.println(f.getParent());
                System.out.println(f.getPath());
                System.out.println(absolutePath);
                load(absolutePath);
            }
        }
    }
}
