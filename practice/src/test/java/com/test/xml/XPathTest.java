package com.test.xml;

import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        /*
         * /  -> 从根节点开始查找元素节点
         * // -> 从匹配选择的当前节点选择文档中的节点，而不考虑它们的位置
         * .  -> 当前元素节点
         * .. -> 当前元素节点的父节点
         * @  -> 选取属性，如：//book[@lang]或//book[@lang='']，获取含有lang属性（或指定属性值）的节点
         * *  -> 通配符
         * |  -> 获取若干路径下的元素节点
         */
        NodeList nodes = (NodeList) xpath.evaluate("//sequenceFlow/conditionExpression", doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            String textContent = item.getTextContent();
            HashMap hashMap = new HashMap();
            hashMap.put("count", 0);
            hashMap.put("target","f");
            System.out.println(item.getNodeName() + "--------" + expressionParsing(textContent, hashMap));
        }
    }

    /**
     * 解析表达式
     */
    private Boolean expressionParsing(String skipExpress, Map map) {
        if (StringUtils.isBlank(skipExpress) && map.isEmpty()) {
            return false;
        }
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        TemplateParserContext templateParserContext = skipExpress.startsWith("$")
                ? new TemplateParserContext("${", "}")
                : null;
        MapAccessor propertyAccessor = new MapAccessor();
        context.setVariables(map);
        context.setPropertyAccessors(Collections.singletonList(propertyAccessor));
        // Spring 的表达式解析接口实现
        SpelExpression expression = (SpelExpression) parser.parseExpression(skipExpress, templateParserContext);
        expression.setEvaluationContext(context);
        return expression.getValue(map, Boolean.class);
    }

}
