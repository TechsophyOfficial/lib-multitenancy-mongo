package com.techsophy.multitenancy.mongo.config;


import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class MultiTenantMongoAppConfigTest {

    @Value("${databaseName:techsophy}")
    String databaseName;
    @InjectMocks
    MultiTenantMongoAppConfig multiTenantMongoAppConfig;

    @BeforeEach
    void fun(){
        ReflectionTestUtils.setField(multiTenantMongoAppConfig, // inject into this object
                "databaseName", // assign to this field
                "techsophy"); // object to be injected
        ReflectionTestUtils.setField(multiTenantMongoAppConfig, // inject into this object
                "databaseUrl", // assign to this field
                "mongodb+srv://nandini:Nandini%40abc123@cluster0.sp7dq.mongodb.net/test?retryWrites=false");
    }

    @Test
    void databaseNameTest(){
        String response = multiTenantMongoAppConfig.databaseName();
        Assertions.assertEquals("techsophy",response);
        // Assertions.assertNull(response);
    }
    @Test
    void createMongoClientTest(){
        // Assertions.assertThrows(NullPointerException.class,()->multiTenantMongoAppConfig.createMongoClient());
        Assertions.assertNotNull(multiTenantMongoAppConfig.createMongoClient());
    }
}