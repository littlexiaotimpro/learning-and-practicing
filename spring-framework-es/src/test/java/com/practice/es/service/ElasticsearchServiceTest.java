package com.practice.es.service;

import com.practice.es.config.ElasticsearchConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

import static org.junit.Assert.*;

public class ElasticsearchServiceTest {

    private AnnotationConfigApplicationContext context;
    private ElasticsearchService elasticsearchService;

    @Before
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext(ElasticsearchConfiguration.class);
        elasticsearchService = context.getBean("elasticsearchService", ElasticsearchService.class);
    }

    @After
    public void tearDown() {
        context.close();
    }

    @Test
    public void createIndex() throws IOException {
        boolean index = elasticsearchService.createIndex();
        assertTrue("创建索引失败！", index);
    }

    @Test
    public void getIndex() throws IOException {
        boolean index = elasticsearchService.getIndex();
        assertTrue("索引不存在！", index);
    }

    @Test
    public void deleteIndex() throws IOException {
        boolean index = elasticsearchService.deleteIndex();
        assertTrue("删除索引失败！", index);
    }

    @Test
    public void save() throws IOException {
        elasticsearchService.save();
    }

    @Test
    public void query() {
        elasticsearchService.query();
    }

    @Test
    public void queryPage() {
        elasticsearchService.queryPage();
    }

    @Test
    public void delete() {
        elasticsearchService.delete();
    }

    @Test
    public void update() {
        elasticsearchService.update();
    }
}