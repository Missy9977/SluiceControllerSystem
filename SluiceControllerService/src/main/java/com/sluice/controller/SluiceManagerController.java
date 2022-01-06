package com.sluice.controller;

import com.sluice.service.DataService;
import com.sluice.service.TokenService;
import com.sluice.service.request.GetDataReq;
import com.sluice.service.request.GetTokenReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("sluiceManagerController")
public class SluiceManagerController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DataService dataService;

    @RequestMapping(path = "/getToken", method = RequestMethod.POST)
    public Object getToken(@RequestBody GetTokenReq getTokenReq) {
        return tokenService.getToken(getTokenReq);
    }

    @RequestMapping(path = "/getDataList", method = RequestMethod.POST)
    public Object getDataList() {
        return dataService.getDataList();
    }

    @RequestMapping(path = "/getData", method = RequestMethod.POST)
    public Object getData(@RequestBody GetDataReq getDataReq) {
        return dataService.getData(getDataReq);
    }


}