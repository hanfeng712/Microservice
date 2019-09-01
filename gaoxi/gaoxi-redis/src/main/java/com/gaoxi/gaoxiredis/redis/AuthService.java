package com.gaoxi.gaoxiredis.redis;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.gaoxi.gaoxicommonservicefacade.RedisService.RedisAuthService;
import com.gaoxi.gaoxicommonservicefacade.common.auth.ext.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Service(version = "${dubbo.service.version}") //声明这是一个dubbo服务
public class AuthService implements RedisAuthService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisService redisServiceObj;

    //存储到令牌到redis
    /**
     *
     * @param access_token 用户身份令牌
     * @param content  内容就是AuthToken对象的内容
     * @param ttl 过期时间
     * @return
     */
    public boolean saveToken(String access_token,String content,long ttl){
        System.out.println("===============saveToken==================");
        String key = "user_token:" + access_token;
        stringRedisTemplate.boundValueOps(key).set(content,ttl, TimeUnit.SECONDS);
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire>0;
    }

    //删除token
    public boolean delToken(String access_token){
        String key = "user_token:" + access_token;
        stringRedisTemplate.delete(key);
        return true;
    }

    //从redis查询令牌
    public AuthToken getUserToken(String token){
        String key = "user_token:" + token;
        //从redis中取到令牌信息
        String value = stringRedisTemplate.opsForValue().get(key);
        //转成对象
        try {
            AuthToken authToken = JSON.parseObject(value, AuthToken.class);
            return authToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
