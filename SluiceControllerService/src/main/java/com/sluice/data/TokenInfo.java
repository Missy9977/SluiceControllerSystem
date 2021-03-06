package com.sluice.data;

public class TokenInfo {
    /**
     * token
     */
    private String access_token;
    /**
     * 有效时长:单位秒
     */
    private Long expires_in;

    /**
     * 类型
     */
    private String token_type;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @Override
    public String toString() {
        return "TokenInfo{" + "access_token='" + access_token + '\'' + ", expires_in=" + expires_in + ", token_type='"
            + token_type + '\'' + '}';
    }
}
