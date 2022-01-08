package com.sluice.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sluice.access.util.RestTemplateUtil;
import com.sluice.access.util.URLConnectionUtil;
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

    private static final String GET_TAG_LIST_URL = "http://127.0.0.1:8080/GetTagList";

    private static final String GET_TAG_URL = "http://127.0.0.1:8080/GetTagValue?strVarName=";

    // TODO
    private static final String SET_TAG_URL = "http://127.0.0.1:8080/SetTagValue";

    private static final String REQUEST_PARAM_ENCODING = "gb2312";

    private static final String RESPONSE_PARAM_ENCODING = "gbk";

    public Object getData(GetDataReq getDataReq) {
        String result = null;

        try {
            String encName = URLEncoder.encode(getDataReq.getName(), REQUEST_PARAM_ENCODING);
            String urlString = GET_TAG_URL + encName;

            result = URLConnectionUtil.doGet(urlString, RESPONSE_PARAM_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Object getDataList() {
        RestTemplate restTemplate = RestTemplateUtil.newInstance();
        ResponseEntity<Resource> entity = restTemplate.getForEntity(GET_TAG_LIST_URL, Resource.class);
        String string = null;
        try {
            InputStream in = entity.getBody().getInputStream();
            string = IOUtils.toString(in, RESPONSE_PARAM_ENCODING);
            System.err.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    public Object setData(SetDataReq setDataReq) {

        String reslut = null;

        try {
            String username = null;
            String password = null;
            String dataName = URLEncoder.encode(setDataReq.getName(), REQUEST_PARAM_ENCODING);
            String dataValue = setDataReq.getValue();

            // SetTagValue?UserName=administrator&PassWord=12345678123456781234567812345678&strTagName=TagName&strSetTagValue=SetTagValue
            String urlString = SET_TAG_URL + "?" + "UserName=" + username + "&" + "PassWord" + password + "&"
                + "strTagName" + dataName + "&" + "strSetTagValue" + dataValue;

            reslut = URLConnectionUtil.doGet(urlString, RESPONSE_PARAM_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return reslut;
    }

}
