package com.sluice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sluice.service.DataService;
import com.sluice.service.TokenService;
import com.sluice.service.request.GetDataReq;
import com.sluice.service.request.GetTokenReq;
import com.sluice.service.request.SetDataReq;

import javax.servlet.http.HttpServletRequest;

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
    public Object getData(HttpServletRequest httpServletRequest, @RequestBody GetDataReq getDataReq) {
        return dataService.getData(httpServletRequest, getDataReq);
    }

    @RequestMapping(path = "/setData", method = RequestMethod.POST)
    public Object setData(HttpServletRequest httpServletRequest, @RequestBody SetDataReq setDataReq) {
        return dataService.setData(httpServletRequest, setDataReq);
    }

}