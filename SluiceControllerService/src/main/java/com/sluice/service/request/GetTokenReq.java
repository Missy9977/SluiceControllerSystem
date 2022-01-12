package com.sluice.service.request;

/**
 * @author : missy
 * @date : 2022-01-04 22:28
 */
public class GetTokenReq {

    private String client_id;

    private String client_secret;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    @Override
    public String toString() {
        return "GetTokenReq{" + "client_id='" + client_id + '\'' + ", client_secret='" + client_secret + '\'' + '}';
    }
}
