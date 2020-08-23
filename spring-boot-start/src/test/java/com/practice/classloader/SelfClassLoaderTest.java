package com.practice.classloader;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器
 */
public class SelfClassLoaderTest {

    @Test
    public void testLoader() throws Exception{
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                // 截取全限定类名的中的class类名
                String simpleClassName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream resource = getClass().getResourceAsStream(simpleClassName);

                if (resource == null){
                    return super.loadClass(name);
                }

                try {
                    byte[] bytes = new byte[resource.available()];
                    resource.read(bytes);
                    return defineClass(name,bytes,0,bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException();
                }

            }
        };

        Object instance = myClassLoader.loadClass("com.practice.classloader.SelfClassLoaderTest").newInstance();
        System.out.println(instance.getClass());
        System.out.println(instance instanceof SelfClassLoaderTest);
    }
}
