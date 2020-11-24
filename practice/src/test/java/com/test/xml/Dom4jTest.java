package com.test.xml;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

public class Dom4jTest {

    private Document document;

    @Before
    public void init() throws Exception {
        String path = this.getClass().getResource("/xpath-test.xml").getPath();
        File file = new File(path);
        document = new SAXReader().read(file);
    }

    @Test
    public void testXPath() {
        Element element = (Element) document.selectSingleNode("//rss/bookstore/book[1]");
        System.out.println(element.getName() + " -> " + element.getStringValue());
    }

    @Test
    public void test() {
        Element rootElement = document.getRootElement();
        for (Iterator<Element> it = rootElement.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            // do something
            System.out.println(element.getName());
            Iterator<Element> elementIterator = element.elementIterator();
            while (elementIterator.hasNext()) {

                Element next = elementIterator.next();
                System.out.println(" -> " + next.getName());
                Iterator<Attribute> attributeIterator = next.attributeIterator();
                while (attributeIterator.hasNext()) {
                    System.out.println("   -> " + attributeIterator.next().getName());
                }
            }
        }
    }

}
