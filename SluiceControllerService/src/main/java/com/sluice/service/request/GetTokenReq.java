package com.sluice.service.request;

/**
 * @author : missy
 * @date : 2022-01-04 22:28
 */
public class GetTokenReq {

    private String id;

    private String secret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "GetTokenReq{" + "id='" + id + '\'' + ", secret='" + secret + '\'' + '}';
    }
}
