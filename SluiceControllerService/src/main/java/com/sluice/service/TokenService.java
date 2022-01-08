package com.sluice.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sluice.cache.UserInfoCache;
import com.sluice.data.TokenInfo;
import com.sluice.service.request.GetTokenReq;

@Service("tokenService")
public class TokenService {

    @Autowired
    private UserInfoCache userInfoCache;

    public TokenInfo getToken(GetTokenReq getTokenReq) {
        TokenInfo tokenInfo = new TokenInfo();

        if (verify(getTokenReq.getId(), getTokenReq.getSecret())) {
            tokenInfo.setToken(buildTokenString());
            tokenInfo.setExpires(userInfoCache.getExpires());
        }

        return tokenInfo;
    }

    private boolean verify(String id, String secret) {
        String presetSecret;
        try {
            presetSecret = userInfoCache.getUserSecret(id);
        } catch (ExecutionException e) {
            return false;
        }
        return Objects.equals(presetSecret, secret);
    }

    // todo 生成token
    private String buildTokenString() {
        byte[] bytes = Base64.getEncoder().encode(String.valueOf(System.currentTimeMillis()).getBytes());
        return Arrays.toString(bytes);
    }
}
