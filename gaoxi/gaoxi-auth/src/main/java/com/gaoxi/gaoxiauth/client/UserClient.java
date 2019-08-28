package com.gaoxi.gaoxiauth.client;

import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;

//import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by Administrator.
 */
public interface UserClient {
    //根据账号查询用户信息
    public UserInfoData getUserext(String username);
}
