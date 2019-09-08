package com.gaoxi.gaoxicontroller.utils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.gaoxi.gaoxicommonservicefacade.RedisService.RedisAuthService;
import com.gaoxi.gaoxicommonservicefacade.common.auth.ext.AuthToken;
import com.gaoxi.gaoxicommonservicefacade.common.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class AuthService {

    @Reference(version = "1.0.0", loadbalance = "roundrobin")
    private RedisAuthService redisAuthService;
    //从头取出jwt令牌
    public String getJwtFromHeader(HttpServletRequest request){
        //取出头信息
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isEmpty(authorization)){
            return null;
        }
        if(!authorization.startsWith("Bearer ")){
            return null;
        }
        //取到jwt令牌
        String jwt = authorization.substring(7);
        return jwt;


    }
    //从cookie取出token
    //查询身份令牌
    public String getTokenFromCookie(HttpServletRequest request){
        Map<String, String> cookieMap = CookieUtil.readCookie(request, "uid");
        System.out.println(cookieMap);
        String access_token = cookieMap.get("uid");
        if(StringUtils.isEmpty(access_token)){
            return null;
        }
        return access_token;
    }

    //查询令牌的有效期
    public boolean getExpire(String access_token){
        String key = "user_token:"+access_token;
        String value = redisAuthService.getUserTokenString(key);
        if (value==null){
            return false;
        }

        try {
            AuthToken authToken = JSON.parseObject(value, AuthToken.class);
        } catch (Exception e) {
        }
        return true;
    }
}
