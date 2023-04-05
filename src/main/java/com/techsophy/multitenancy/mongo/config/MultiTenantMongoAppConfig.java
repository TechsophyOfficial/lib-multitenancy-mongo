package com.techsophy.multitenancy.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MultiTenantMongoAppConfig  {

    @Value("${database.url}")
    private  String databaseUrl;


    @Value("${database.name}")
    private String databaseName;


    @Bean
    public String databaseName() {
        return databaseName;
    }

    @Bean
    public MongoClient createMongoClient() {
        final ConnectionString connectionString = new ConnectionString(databaseUrl);
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTransactionManager transactionManager(MongoTemplate mongoTemplate)
    {
        return new MongoTransactionManager(mongoTemplate.getMongoDbFactory());
    }
}