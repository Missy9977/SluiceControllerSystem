package com.sluice.service;

import com.sluice.data.TokenInfo;
import org.springframework.stereotype.Service;

@Service("tokenService")
public class TokenService {
    public TokenInfo queryToken() {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setAccess_token("qwertyuiop");
        return tokenInfo;
    }
}
