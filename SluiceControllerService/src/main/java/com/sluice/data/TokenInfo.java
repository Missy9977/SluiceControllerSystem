package com.sluice.data;

public class TokenInfo {
    /**
     * token
     */
    private String token;
    /**
     * 有效时长:单位秒
     */
    private Long expires;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "TokenInfo{" + "token='" + token + '\'' + ", expires=" + expires + '}';
    }
}
