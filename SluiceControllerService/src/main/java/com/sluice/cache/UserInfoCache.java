package com.sluice.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sluice.service.UserInfoUtil;

/**
 * @author : missy
 * @date : 2022-01-04 22:03
 */
@Component("userInfoCache")
public class UserInfoCache {

    private static final long EXPIRES = 7200;

    private final Cache<String, String> cache =
        CacheBuilder.newBuilder().initialCapacity(30).expireAfterAccess(EXPIRES, TimeUnit.SECONDS).build();

    @Autowired
    private UserInfoUtil userInfoUtil;

    public String getUserSecret(String id) throws ExecutionException {
        return cache.get(id, () -> userInfoUtil.getSecret(id));
    }

    public long getExpires() {
        return EXPIRES;
    }
}
