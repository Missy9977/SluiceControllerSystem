package com.sluice.service;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sluice.data.UserInfo;

/**
 * @author : missy
 * @date : 2022-01-04 22:13
 */
@Component("userInfoUtil")
public class UserInfoUtil {
    private static final Log LOGGER = LogFactory.getLog(DataService.class);

    private static final String FILENAME = "keys.json";

    public String getSecret(String id) {
        List<UserInfo> userInfoList = getKeys();

        if (userInfoList != null) {
            for (UserInfo userInfo : userInfoList) {
                if (userInfo.getId().equals(id)) {
                    return userInfo.getSecret();
                }
            }
        }

        return null;
    }

    private List<UserInfo> getKeys() {
        List<UserInfo> userInfoList = null;

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILENAME)) {
            JSONObject jsonObject = JSONObject.parseObject(IOUtils.toString(is, "utf-8"));
            String keys = jsonObject.getString("keys");
            userInfoList = JSONArray.parseArray(keys, UserInfo.class);
        } catch (Exception e) {
            LOGGER.error(FILENAME + "文件读取异常" + e);
        }

        return userInfoList;
    }
}
