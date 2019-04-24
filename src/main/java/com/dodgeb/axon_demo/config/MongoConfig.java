package com.dodgeb.axon_demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoConfig {

    @Value("${readmodel.database}")
    private String database;

    @Value("${readmodel.uri}")
    private String uri;

    @Value("${readmodel.timeout.connect}")
    private int connectTimeout;

    private MongoClientOptions.Builder mongoClientOption() {
        return MongoClientOptions.builder()
                .connectTimeout(connectTimeout);
    }

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(uri, mongoClientOption()));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), database);
    }

}