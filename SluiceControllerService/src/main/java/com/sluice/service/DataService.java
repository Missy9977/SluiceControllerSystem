package com.sluice.service;

import com.sluice.access.util.RestTemplateUtil;
import com.sluice.access.util.URLConnectionUtil;
import com.sluice.service.request.GetDataReq;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : missy
 * @date : 2022-01-06 19:20
 */
@Service("dataService")
public class DataService {

    private static final String GET_TAG_LIST_URL = "http://127.0.0.1:8080/GetTagList";

    private static final String GET_TAG_URL = "http://127.0.0.1:8080/GetTagValue?strVarName=";

    private static final String REQUEST_PARAM_ENCODING = "gb2312";

    private static final String RESPONSE_PARAM_ENCODING = "gbk";

    public Object getData(GetDataReq getDataReq) {
        String reslut = null;

        try {
            String encName = URLEncoder.encode(getDataReq.getName(), REQUEST_PARAM_ENCODING);
            String urlString = GET_TAG_URL + encName;

            reslut = URLConnectionUtil.doGet(urlString, RESPONSE_PARAM_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reslut;
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
}
