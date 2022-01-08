package com.sluice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private Properties properties = new Properties();

    public void readProperties (String fileName) {
        InputStream is = PropertyUtil.class.getResourceAsStream(fileName);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyValue(String key) {
        return (String) properties.get(key);
    }
}
