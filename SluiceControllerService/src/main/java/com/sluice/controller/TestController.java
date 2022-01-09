package com.sluice.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

import com.sluice.service.request.GetDataReq;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sluice.service.DataService;
import com.sluice.service.TokenService;

/**
 * 测试类
 */
@RestController
public class TestController {
    private static final Log LOGGER = LogFactory.getLog(TestController.class);

    @Autowired
    private DataService dataService;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public Object test() {
        LOGGER.info("=== Start to do test() of TestController");
        String dataList = (String)dataService.getDataList();
        try {
            String name = getSomeOne(dataList);
            if (name != null) {
                GetDataReq req = new GetDataReq();
                req.setName(name);
                dataService.getData(req);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }

        LOGGER.info("=== end to do test() of TestController");
        return dataList;
    }

    private String getSomeOne(String dataList) throws IOException {
        Reader reader = new StringReader(dataList);
        BufferedReader br = new BufferedReader(reader);

        // "strVarName" : "手动切换"
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("strVarName")) {
                String[] strings = line.split("\"");
                LOGGER.info("array size is " + strings.length);
                LOGGER.info(Arrays.toString(strings));
                return strings[3].trim();
            }
        }

        LOGGER.error("=== Failed to getSomeOne name!");
        return null;
    }
}
