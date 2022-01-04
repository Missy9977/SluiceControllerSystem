package com.sluice.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sluice.service.UserInfoUtil;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : missy
 * @date : 2022-01-04 22:03
 */
@Component("userInfoCache")
public class UserInfoCache {

    private final Cache<String, String> userInfoCache = CacheBuilder.newBuilder().initialCapacity(30)
            .expireAfterAccess(30,
                    TimeUnit.MINUTES).build();

    @Autowired
    private UserInfoUtil userInfoUtil;

    public String getUserSecret(String id) throws ExecutionException {
        return userInfoCache.get(id, () -> userInfoUtil.getSecret(id));
    }
}
