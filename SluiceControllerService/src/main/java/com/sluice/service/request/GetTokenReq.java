package com.sluice.service.request;

/**
 * @author : missy
 * @date : 2022-01-04 22:28
 */
public class GetTokenReq {

    private String scope;

    private String client_id;

    private String client_secret;

    private String grant_type;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

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

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    @Override
    public String toString() {
        return "GetTokenReq{" +
                "scope='" + scope + '\'' +
                ", client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", grant_type='" + grant_type + '\'' +
                '}';
    }
}
