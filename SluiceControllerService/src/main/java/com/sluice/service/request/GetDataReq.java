package com.sluice.service.request;

import java.util.List;

/**
 * @author : missy
 * @date : 2022-01-06 19:20
 */
public class GetDataReq {

    private List<String> names;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "GetDataReq{" + "names=" + names + '}';
    }
}
