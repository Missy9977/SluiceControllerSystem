package com.sluice.data;

/**
 * @author : missy
 * @date : 2022-01-04 21:43
 */
public class UserInfo {

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
        return "UserInfo{" + "id='" + id + '\'' + ", secret='" + secret + '\'' + '}';
    }
}
