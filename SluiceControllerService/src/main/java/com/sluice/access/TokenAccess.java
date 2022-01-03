package com.sluice.access;

import com.sluice.data.TokenInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("tokenAccess")
public class TokenAccess {

    public TokenInfo getToken() {
        String url = null;

        ParamInfo paramInfo = new ParamInfo();
        paramInfo.setClient_id("123");

        RestTemplate restTemplate = new RestTemplate();
        TokenInfo tokenInfo = restTemplate.postForObject(url, paramInfo, TokenInfo.class);
        return tokenInfo;
    }

    private static class ParamInfo{
        String scope;

        String client_id;

        String client_secret;

        String grant_type;

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
            return "ParamInfo{" +
                    "scope='" + scope + '\'' +
                    ", client_id='" + client_id + '\'' +
                    ", client_secret='" + client_secret + '\'' +
                    ", grant_type='" + grant_type + '\'' +
                    '}';
        }
    }
}
