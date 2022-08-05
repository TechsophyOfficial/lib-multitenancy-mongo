package com.techsophy.multitenancy.mongo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class TenantContext {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        CONTEXT.set(tenantId);
    }

    public static String getTenantId() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}