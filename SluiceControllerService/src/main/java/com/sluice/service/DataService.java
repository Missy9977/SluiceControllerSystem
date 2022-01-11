package com.sluice.service;

import com.alibaba.fastjson.JSONObject;
import com.sluice.access.util.RestTemplateUtil;
import com.sluice.access.util.URLConnectionUtil;
import com.sluice.cache.PropertyCache;
import com.sluice.cache.TokenCache;
import com.sluice.data.BaseInfo;
import com.sluice.data.KvInfo;
import com.sluice.data.KvRemoteInfo;
import com.sluice.service.request.GetDataReq;
import com.sluice.service.request.SetDataReq;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * 现阶段，组态王Restul WebService服务暂只提供GET请求。对应的开放四个接口，请求方式分别如下：
 *
 * 1、http:\\服务器IP:8080\GetTagList：该请求方式用来获取变量列表，获取到的是json字符串。包含变量名、变量ID、变量类型、变量值。
 *
 * 2、http:\\服务器IP:8080\GetTagValue?strTagName=TagName(仅为举例，不一定非得写成strTagName)：该请求方式用来获取变量，获取到的是json字符串。包含变量名、变量ID、变量类型、变量值。
 *
 * 3、http:\\ServerIP:8089\SetTagValue?UserName=administrator&PassWord=12345678123456781234567812345678&strTagName=TagName&strSetTagValue=SetTagValue
 * 
 * @author : missy
 * @date : 2022-01-06 19:20
 */
@Service("dataService")
public class DataService {

    private static final Log LOGGER = LogFactory.getLog(DataService.class);

    /**
     * url + interface;http://127.0.0.1:8280
     */
    private static final String GET_TAG_LIST = "/GetTagList";

    private static final String GET_TAG = "/GetTagValue";

    private static final String SET_TAG = "/SetTagValue";

    private static final String KV_HOST = "kv.host";

    private static final String KV_USERNAME = "kv.username";

    private static final String KV_PASSWORD = "kv.password";

    private static final BaseInfo ERROR_MSG = new BaseInfo("Token is error");

    @Autowired
    private PropertyCache propertyCache;

    @Autowired
    private TokenCache tokenCache;

    public Object getData(HttpServletRequest httpServletRequest, GetDataReq getDataReq) {
        LOGGER.info("=== Start to do getData(), and the req is " + getDataReq);
        if (!checkToken(httpServletRequest)) {
            LOGGER.error("===  The token is not exist");
            return ERROR_MSG;
        }

        List<String> names = getDataReq.getNames();

        List<KvInfo> kvInfos = new ArrayList<>();

        for (String name : names) {
            try {
                String encName = URLEncoder.encode(name, "gb2312");
                String urlString = propertyCache.getPropertyValue(KV_HOST) + GET_TAG + "?strVarName=" + encName;

                LOGGER.info("=== start to send http request for " + urlString);

                String result = URLConnectionUtil.doGet(urlString, "gbk");

                LOGGER.info("=== get the result from http request, result : " + result);

                KvInfo kvInfo = parseKvInfo(result);

                kvInfos.add(kvInfo);
            } catch (Exception e) {
                LOGGER.error("=== Failed to do getData()");
                LOGGER.error(e);
            }
        }

        LOGGER.info("=== End to do getData(), and the result is " + kvInfos);
        return kvInfos;
    }

    public Object getDataList() {
        LOGGER.info("=== Start to do getDataList(), and the req is null");

        String url = propertyCache.getPropertyValue(KV_HOST) + GET_TAG_LIST;

        LOGGER.info("=== start to send http request for " + url);

        RestTemplate restTemplate = RestTemplateUtil.newInstance();
        ResponseEntity<Resource> entity = restTemplate.getForEntity(url, Resource.class);

        String result = null;

        try {
            InputStream in = entity.getBody().getInputStream();
            result = IOUtils.toString(in, "gbk");
            LOGGER.info("=== get the result from http request, result : " + result);
        } catch (IOException e) {
            LOGGER.error("=== Failed to do getDataList()");
            LOGGER.error(e);
        }

        LOGGER.info("=== End to do getDataList(), and the result is " + result);
        return result;
    }

    public Object setData(HttpServletRequest httpServletRequest, SetDataReq setDataReq) {
        LOGGER.info("=== Start to do setData(), and the req is " + setDataReq);

        if (!checkToken(httpServletRequest)) {
            LOGGER.error("===  The token is not exist");
            return ERROR_MSG;
        }

        String result = null;
        KvInfo kvInfo;

        try {
            String usernamePro = propertyCache.getPropertyValue(KV_USERNAME);
            String username = URLEncoder.encode(usernamePro, "utf-8");
            String passwordPro = propertyCache.getPropertyValue(KV_PASSWORD);
            String password = DigestUtils.md5DigestAsHex(passwordPro.getBytes());
            String dataName = URLEncoder.encode(setDataReq.getName(), "utf-8");
            String dataValue = setDataReq.getValue();

            // SetTagValue?UserName=administrator&PassWord=12345678123456781234567812345678&strTagName=TagName&strSetTagValue=SetTagValue
            String urlString = propertyCache.getPropertyValue(KV_HOST) + SET_TAG + "?" + "UserName=" + username + "&"
                + "PassWord=" + password + "&" + "strTagName=" + dataName + "&" + "strSetTagValue=" + dataValue;

            LOGGER.info("=== start to send http request for " + urlString);

            result = URLConnectionUtil.doGet(urlString, "utf-8");

            LOGGER.info("=== get the result from http request, result : " + result);

            kvInfo = parseKvInfo(result);

        } catch (Exception e) {
            LOGGER.error("=== Failed to do setData()");
            LOGGER.error(e);
            return result;
        }

        LOGGER.info("=== End to do setData(), and the kvInfo is " + kvInfo);
        return kvInfo;
    }

    private boolean checkToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("authorization");
        LOGGER.info("=== Get the token from request header, bearerToken is " + bearerToken);
        if (bearerToken == null) {
            return false;
        }
        String token = bearerToken.substring(7);
        return tokenCache.exist(token);
    }

    private KvInfo parseKvInfo(String result) {
        KvRemoteInfo kvRemoteInfo = JSONObject.parseObject(result, KvRemoteInfo.class);
        KvInfo kvInfo = new KvInfo();
        kvInfo.setId(kvRemoteInfo.getnVarID());
        kvInfo.setName(kvRemoteInfo.getStrVarName());
        kvInfo.setValue(kvRemoteInfo.getVarValue());
        kvInfo.setType(kvRemoteInfo.getnVarType());
        return kvInfo;
    }

}
