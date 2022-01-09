package com.sluice.cache;

import java.util.concurrent.TimeUnit;

import com.sluice.service.TokenService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @author : missy
 * @date : 2022-01-04 22:03
 */
@Component("tokenCache")
public class TokenCache {

    private static final Log LOGGER = LogFactory.getLog(TokenCache.class);

    private static final long EXPIRES = 7200;

    private final Cache<String, String> cache =
        CacheBuilder.newBuilder().initialCapacity(8).expireAfterAccess(EXPIRES, TimeUnit.SECONDS).build();

    public boolean exist(String token) {
        return cache.asMap().containsValue(token);
    }

    public void put(String id, String token) {
        cache.put(id, token);
        LOGGER.info("=== tokenCache is: " + cache.asMap());
    }

    public long getExpires() {
        return EXPIRES;
    }
}
