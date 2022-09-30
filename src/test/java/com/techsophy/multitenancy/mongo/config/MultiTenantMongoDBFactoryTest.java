package com.techsophy.multitenancy.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;
import com.mongodb.internal.async.client.AsyncClientSession;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MultiTenantMongoDBFactoryTest{


    @Mock
    private MongoDatabase mockDB;
    @Mock
    MongoClient mongoClient;

    @InjectMocks
    SimpleMongoClientDatabaseFactory simpleMongoClientDatabaseFactory = Mockito.mock(SimpleMongoClientDatabaseFactory.class);

    MultiTenantMongoDBFactory multiTenantMongoDBFactory = Mockito.mock(MultiTenantMongoDBFactory.class);

    @Test
    void getMongoDatabaseTest(){
        when(multiTenantMongoDBFactory.getMongoDatabase()).thenReturn(mockDB);
        when(mongoClient.getDatabase(anyString())).thenReturn(mockDB);
        Assertions.assertEquals(new MultiTenantMongoDBFactory(mongoClient,"abc").getMongoDatabase(),mockDB);
        Assertions.assertEquals(multiTenantMongoDBFactory.getMongoDatabase(),mockDB);
    }

    @Test
    void MultiTenantMongoDBFactoryTest(){
        Assertions.assertNotNull(new MultiTenantMongoDBFactory(mongoClient,"abc"));
           }

    @Test
    void SimpleMongoClientDatabaseFactoryTest(){

        Assertions.assertNotNull(new SimpleMongoClientDatabaseFactory(mongoClient,"abc"));
    }

    @Test
    void  getTenantDatabase(){
        TenantContext.setTenantId("abc");
        when(multiTenantMongoDBFactory.getTenantDatabase()).thenReturn(TenantContext.getTenantId());
        Assertions.assertEquals("abc", multiTenantMongoDBFactory.getTenantDatabase());
        Assertions.assertNotNull(multiTenantMongoDBFactory.getTenantDatabase());
        Assertions.assertNull(new MultiTenantMongoDBFactory(mongoClient,"abc").doGetMongoDatabase("abc"));
    }

    @Test
    void  doGetMongoDatabase(){
        TenantContext.setTenantId("abc");
        when(mongoClient.getDatabase(anyString())).thenReturn(mockDB);
        Assertions.assertEquals(new MultiTenantMongoDBFactory(mongoClient,"abc").doGetMongoDatabase("abc"),mockDB);
    }

    @Test
    void doGetMongoDatabaseNull(){
        TenantContext.setTenantId(null);
        when(mongoClient.getDatabase(null)).thenReturn(mockDB);
        Assertions.assertEquals(new MultiTenantMongoDBFactory(mongoClient,"abc").doGetMongoDatabase("abc"),mockDB);
    }

    @Test
    void tenantContextRemove(){
        TenantContext.setTenantId("abc");
        TenantContext.clear();
        Assertions.assertEquals(null, TenantContext.getTenantId());
    }
}
