package com.sluice.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sluice.util.PropertyUtil;

/**
 * @author : missy
 * @date : 2022-01-04 22:03
 */
@Component("propertyCache")
public class PropertyCache {

    private static final long EXPIRES = 7;

    private final Cache<String, String> propertyCache =
        CacheBuilder.newBuilder().initialCapacity(30).expireAfterAccess(EXPIRES, TimeUnit.DAYS).build();

    @Autowired
    private PropertyUtil propertyUtil;

    public String getPropertyValue(String key) throws ExecutionException {
        return propertyCache.get(key, () -> propertyUtil.getPropertyValue(key));
    }

    public long getExpires() {
        return EXPIRES;
    }
}
