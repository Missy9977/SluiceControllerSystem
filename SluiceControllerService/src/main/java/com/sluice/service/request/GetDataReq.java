package com.sluice.service.request;

/**
 * @author : missy
 * @date : 2022-01-06 19:20
 */
public class GetDataReq {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetDataReq{" +
                "name='" + name + '\'' +
                '}';
    }
}
