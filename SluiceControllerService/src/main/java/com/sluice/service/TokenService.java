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

@Service("tokenService")
public class TokenService {

    private static final Log LOGGER = LogFactory.getLog(TokenService.class);

    @Autowired
    private UserInfoCache userInfoCache;

    @Autowired
    private TokenCache tokenCache;

    public TokenInfo getToken(String id, String secret) {
        LOGGER.info("=== Start to do getToken(), and the id is " + id + ",secret is " + secret);

        TokenInfo tokenInfo = new TokenInfo();

        if (verify(id, secret)) {
            String tokenString = buildTokenString(id);
            tokenInfo.setAccess_token(tokenString);
            tokenInfo.setExpires_in(tokenCache.getExpires());
            tokenInfo.setToken_type("Bearer");
            tokenCache.put(id, tokenString);
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
