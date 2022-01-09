package com.sluice.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sluice.service.DataService;
import com.sluice.service.PropertyUtil;

/**
 * @author : missy
 * @date : 2022-01-04 22:03
 */
@Component("propertyCache")
public class PropertyCache {

    private static final Log LOGGER = LogFactory.getLog(DataService.class);

    private static final long EXPIRES = 7;

    private final Cache<String, String> cache =
        CacheBuilder.newBuilder().initialCapacity(30).expireAfterAccess(EXPIRES, TimeUnit.DAYS).build();

    @Autowired
    private PropertyUtil propertyUtil;

    public String getPropertyValue(String key) {
        try {
            return cache.get(key, () -> propertyUtil.getPropertyValue(key));
        } catch (ExecutionException e) {
            LOGGER.error("=== Failed to get properties for " + key);
            LOGGER.error(e);
        }
        return null;
    }

}
