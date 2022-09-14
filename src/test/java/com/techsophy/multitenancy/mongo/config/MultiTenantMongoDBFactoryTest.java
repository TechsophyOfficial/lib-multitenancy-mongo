package com.techsophy.multitenancy.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MultiTenantMongoDBFactoryTest {
    @Mock
    MongoClient mongoClient;
    @InjectMocks
    MultiTenantMongoDBFactory multiTenantMongoDBFactory;


//    @Test
//    void doGetMongoDatabaseTest(){
//       MongoDatabase response = multiTenantMongoDBFactory.doGetMongoDatabase("abc");
//       Assertions.assertNull(response);
//    }
}
