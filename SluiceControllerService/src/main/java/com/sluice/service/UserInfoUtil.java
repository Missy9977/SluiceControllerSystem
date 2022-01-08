package com.sluice.service;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
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

    private static final String KEYS_URL = "keys.json";

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

        try (InputStream is = this.getClass().getResourceAsStream(KEYS_URL)) {
            JSONObject jsonObject = JSONObject.parseObject(IOUtils.toString(is, "utf-8"));
            String keys = jsonObject.getString("keys");
            userInfoList = JSONArray.parseArray(keys, UserInfo.class);
        } catch (Exception e) {
            System.err.println(KEYS_URL + "文件读取异常" + e);
        }

        return userInfoList;
    }
}
