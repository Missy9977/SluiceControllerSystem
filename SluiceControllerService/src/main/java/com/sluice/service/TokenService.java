package com.sluice.service;

import com.sluice.access.TokenAccess;
import com.sluice.data.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tokenService")
public class TokenService {
    @Autowired
    private TokenAccess tokenAccess;

    public TokenInfo queryToken() {
        return tokenAccess.getToken();
    }
}
