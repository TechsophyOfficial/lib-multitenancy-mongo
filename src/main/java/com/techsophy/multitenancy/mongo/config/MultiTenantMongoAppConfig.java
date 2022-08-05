package com.techsophy.multitenancy.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiTenantMongoAppConfig  {

    @Value("${database.url}")
    private  String DATABASE_URL;


    @Value("${database.name}")
    private String DATABASE_NAME;


    @Bean
    public String databaseName() {
        return DATABASE_NAME;
    }

    @Bean
    public MongoClient createMongoClient() {
        final ConnectionString connectionString = new ConnectionString(DATABASE_URL);
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

}