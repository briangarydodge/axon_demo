package com.dodgeb.axon_demo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${readmodel.database}")
    private String database;

    @Value("${readmodel.uri}")
    private String uri;

    private MongoClientOptions.Builder mongoClientOption() {

        return MongoClientOptions.builder()

                .connectTimeout(10000);
    }

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(uri, mongoClientOption()));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), database);
    }

}