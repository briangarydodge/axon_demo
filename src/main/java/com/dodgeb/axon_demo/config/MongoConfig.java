package com.dodgeb.axon_demo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

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


//    public @Bean
//    MongoDbFactory mongoDbFactory() throws Exception {
//        return new SimpleMongoDbFactory(new MongoClient(new MongoClientURI(uri, mongoClientOption())), database);
//    }

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(uri, mongoClientOption()));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), database);

//        ((MappingMongoConverter)mongoTemplate.getConverter()).setTypeMapper(new DefaultMongoTypeMapper(null));//removes _class

        return mongoTemplate;
    }

}