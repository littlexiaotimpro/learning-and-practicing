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
    public void load() throws IOException {
        File file = new File(FILE_LOCATION_PATH);
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
    public void loadBytes() throws IOException {
        File file = new File(FILE_LOCATION_PATH);
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
}
