package com.sluice.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component("propertyUtil")
public class PropertyUtil {
    private static final Log LOGGER = LogFactory.getLog(PropertyUtil.class);

    private static final String FILENAME = "kv.properties";

    private final Properties properties = new Properties();

    public PropertyUtil() {
        readProperties(FILENAME);
    }

    public void readProperties(String fileName) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILENAME)) {
            properties.load(new InputStreamReader(is, StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOGGER.error("=== Failed to load properties file,filename is " + fileName);
            LOGGER.error(e);
        }
    }

    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}
