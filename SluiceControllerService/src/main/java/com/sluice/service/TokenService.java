package com.sluice.service;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.sluice.cache.TokenCache;
import com.sluice.cache.UserInfoCache;
import com.sluice.data.TokenInfo;
import com.sluice.service.request.GetTokenReq;

@Service("tokenService")
public class TokenService {

    private static final Log LOGGER = LogFactory.getLog(TokenService.class);

    @Autowired
    private UserInfoCache userInfoCache;

    @Autowired
    private TokenCache tokenCache;

    public TokenInfo getToken(GetTokenReq getTokenReq) {
        LOGGER.info("=== Start to do getToken(), and the req is " + getTokenReq);

        TokenInfo tokenInfo = new TokenInfo();

        if (verify(getTokenReq.getId(), getTokenReq.getSecret())) {
            String tokenString = buildTokenString(getTokenReq.getId());
            tokenInfo.setToken(tokenString);
            tokenInfo.setExpires(tokenCache.getExpires());
            tokenCache.put(getTokenReq.getId(), tokenString);
        } else {
            LOGGER.error("=== the id or secret is error! please input them again");
        }

        LOGGER.info("=== End to do getToken(), and the result is " + tokenInfo);
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

    private String buildTokenString(String id) {
        Random random = new Random(System.currentTimeMillis());
        int nextInt = random.nextInt();
        String source = id + nextInt;
        return DigestUtils.md5DigestAsHex(source.getBytes());
    }
}
