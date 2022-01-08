package com.sluice.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.sluice.access.util.RestTemplateUtil;
import com.sluice.access.util.URLConnectionUtil;
import com.sluice.cache.PropertyCache;
import com.sluice.data.KvInfo;
import com.sluice.service.request.GetDataReq;
import com.sluice.service.request.SetDataReq;

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
     * url + interface;http://192.168.31.15:8081
     */
    private static final String GET_TAG_LIST = "/GetTagList";

    private static final String GET_TAG = "/GetTagValue";

    private static final String SET_TAG = "/SetTagValue";

    private static final String KV_HOST = "kv.host";
    private static final String KV_USERNAME = "kv.username";
    private static final String KV_PASSWORD = "kv.password";

    private static final String REQUEST_PARAM_ENCODING = "gb2312";

    private static final String RESPONSE_PARAM_ENCODING = "gbk";

    @Autowired
    private PropertyCache propertyCache;

    public Object getData(GetDataReq getDataReq) {
        LOGGER.info("=== Start to do getData(), and the req is " + getDataReq);

        KvInfo kvInfo = null;

        try {
            String encName = URLEncoder.encode(getDataReq.getName(), REQUEST_PARAM_ENCODING);
            String urlString = GET_TAG + "?strVarName=" + encName;

            LOGGER.info("=== start to send http request for " + urlString);

            String result = URLConnectionUtil.doGet(urlString, RESPONSE_PARAM_ENCODING);

            LOGGER.info("=== get the result from http request, result : " + result);

            kvInfo = JSONObject.parseObject(result, KvInfo.class);

        } catch (Exception e) {
            LOGGER.error("=== Failed to do getData()");
            LOGGER.error(e);
        }

        LOGGER.info("=== End to do getData(), and the result is " + kvInfo);
        return kvInfo;
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
            result = IOUtils.toString(in, RESPONSE_PARAM_ENCODING);
            LOGGER.info("=== get the result from http request, result : " + result);
        } catch (IOException e) {
            LOGGER.error("=== Failed to do getDataList()");
            LOGGER.error(e);
        }

        LOGGER.info("=== End to do getDataList(), and the result is " + result);
        return result;
    }

    public Object setData(SetDataReq setDataReq) {
        LOGGER.info("=== Start to do setData(), and the req is " + setDataReq);

        String result = null;

        try {
            String username = propertyCache.getPropertyValue(KV_USERNAME);
            String password = propertyCache.getPropertyValue(KV_PASSWORD);
            String dataName = URLEncoder.encode(setDataReq.getName(), REQUEST_PARAM_ENCODING);
            String dataValue = setDataReq.getValue();

            // SetTagValue?UserName=administrator&PassWord=12345678123456781234567812345678&strTagName=TagName&strSetTagValue=SetTagValue
            String urlString = propertyCache.getPropertyValue(KV_HOST) + SET_TAG + "?" + "UserName=" + username + "&"
                + "PassWord" + password + "&" + "strTagName" + dataName + "&" + "strSetTagValue" + dataValue;

            LOGGER.info("=== start to send http request for " + urlString);

            result = URLConnectionUtil.doGet(urlString, RESPONSE_PARAM_ENCODING);

            LOGGER.info("=== get the result from http request, result : " + result);
        } catch (Exception e) {
            LOGGER.error("=== Failed to do setData()");
            LOGGER.error(e);
        }

        LOGGER.info("=== End to do setData(), and the result is " + result);
        return result;
    }

}
