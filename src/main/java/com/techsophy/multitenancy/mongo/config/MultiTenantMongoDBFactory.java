package com.techsophy.multitenancy.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@ComponentScan({"com.techsophy.multitenancy.mongo.*"})
@Configuration
public class MultiTenantMongoDBFactory extends SimpleMongoClientDatabaseFactory {

    @Value("${database.name}")
    private String databaseName;




    public MultiTenantMongoDBFactory(@Qualifier("createMongoClient") MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    @Override
    protected MongoDatabase doGetMongoDatabase(String dbName) {
        return  getMongoClient().getDatabase(getTenantDatabase());
    }



    @Override
    public MongoDatabase getMongoDatabase() {
        return getMongoClient().getDatabase(getTenantDatabase());
    }

    protected String getTenantDatabase() {
        String tenantId = TenantContext.getTenantId();
        if (tenantId != null) {
            return tenantId;
        } else{
            return databaseName;
        }
    }


}