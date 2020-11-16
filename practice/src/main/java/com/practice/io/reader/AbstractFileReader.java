package com.practice.io.reader;

import java.io.IOException;

public abstract class AbstractFileReader {

    protected static final String RESOURCE_LOCATION = "input-file.properties";

    protected static final String FILE_LOCATION = "classpath:/input.txt";

    protected static final String FILE_LOCATION_PATH = "I:\\IdeaProject\\awesome\\practice\\src\\main\\resources\\input.txt";

    protected abstract void load(ClassLoader classLoader);

    protected abstract void load() throws IOException;

    protected abstract void loadBytes() throws IOException;
}
