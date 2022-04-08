package com.practice.es.config;

import com.practice.common.factory.DefaultYamlPropertySourceFactory;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * es配置类
 *
 * @since 2022/4/7
 */
@Configuration
@EnableConfigurationProperties({ElasticsearchRestClientProperties.class})
@PropertySource(value = {"classpath:/application.yml"},
        factory = DefaultYamlPropertySourceFactory.class)
@ComponentScan(basePackages = {"com.practice.es.service"})
public class ElasticsearchConfiguration {

    @Bean
    RestClientBuilder restClientBuilder(ElasticsearchRestClientProperties properties) {
        HttpHost[] hosts = properties.getUris().stream().map(HttpHost::create).toArray(HttpHost[]::new);
        return RestClient.builder(hosts);
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }

}
