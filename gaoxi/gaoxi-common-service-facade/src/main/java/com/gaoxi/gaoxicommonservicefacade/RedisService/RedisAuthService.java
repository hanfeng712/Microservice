package com.gaoxi.gaoxicommonservicefacade.RedisService;

import com.gaoxi.gaoxicommonservicefacade.common.auth.ext.AuthToken;

public interface RedisAuthService {
    public boolean saveToken(String access_token, String content, long ttl);
    public boolean delToken(String access_token);
    public AuthToken getUserToken(String token);
    public String getUserTokenString(String token);
}
