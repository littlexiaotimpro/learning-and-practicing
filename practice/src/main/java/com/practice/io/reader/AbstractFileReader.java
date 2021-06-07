package com.practice.io.reader;

import java.io.IOException;

public abstract class AbstractFileReader {

    protected static final String RESOURCE_LOCATION = "input-file.properties";

    protected static final String FILE_LOCATION = "classpath:/input.txt";

    protected static final String FILE_LOCATION_PATH = "I:\\IdeaProject\\awesome\\practice\\src\\main\\resources\\input.txt";

    protected static final String FILE_DIR = "I:\\IdeaProject\\awesome\\practice\\src\\main\\dir";

    protected abstract void load(ClassLoader classLoader);

    protected abstract void load(String filepath) throws IOException;

    protected abstract void loadBytes(String filepath) throws IOException;

    protected abstract void loadDir(String dirPath) throws IOException;
}
