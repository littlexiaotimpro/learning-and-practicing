package com.practice.es.service;

import com.alibaba.fastjson.JSON;
import com.practice.es.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * es服务接口，提供数据操作
 *
 * @since 2022/4/7
 */
@Service
@Slf4j
public class ElasticsearchService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    public boolean createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }

    public boolean getIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("user");
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    public boolean deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }

    public void save() throws IOException {
        User user = new User("Jack", "man", 20);
        IndexRequest request = new IndexRequest("user");
        // 文档id，不指定会有默认id
        request.id("1")
                // 分片超时时间
                .timeout(TimeValue.timeValueSeconds(1))
                // 将文档源设置为索引，user对象转化成json字符串
                .source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        log.info("响应结果: {}", response.toString());
        log.info("响应状态: {}", response.status());
    }

    public void query() {

    }

    public void queryPage() {
    }

    public void delete() {
    }

    public void update() {
    }

}
