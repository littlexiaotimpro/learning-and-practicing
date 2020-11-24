package com.test.xml;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;

public class XPathTest {

    private Document doc;
    private XPath xpath;

    @Before
    public void init() throws Exception {
        // 创建Document对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        String path = this.getClass().getResource("/xpath-test.xml").getPath();
        File file = new File(path);
        doc = db.parse(new FileInputStream(file));

        // 创建XPath对象
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }

    @Test
    public void test() throws Exception {
        Node node = (Node) xpath.evaluate("/rss/bookstore//*", doc, XPathConstants.NODE);
        System.out.println(node.getNodeName() + "--------" + node.getNodeValue());

        String evaluate = xpath.evaluate("/rss/bookstore/book", node);
        System.out.println(evaluate);
    }

}
