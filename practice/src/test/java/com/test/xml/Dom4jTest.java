package com.test.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

public class Dom4jTest {

    @Test
    public void test() {
        File file = new File("filePath");
        try {
            Document document = new SAXReader().read(file);
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
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
