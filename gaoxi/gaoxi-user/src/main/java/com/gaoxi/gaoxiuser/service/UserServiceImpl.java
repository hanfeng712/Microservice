package com.gaoxi.gaoxiuser.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.gaoxicommonservicefacade.dao.UserInfo;
import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;
import com.gaoxi.gaoxicommonservicefacade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sifu
 * @version 1.0
 * @date 2017/12/14
 */
@Service(version = "${dubbo.service.version}") //声明这是一个dubbo服务
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfo userInfo;
    @Override
    public String login(String username) {
        return "username:" + username;
    }
    @Override
    public UserInfoData selectUserByKey(int userid){
        UserInfoData userInfoData = userInfo.selectByPrimaryKey(userid);
        return userInfoData;
    }
    @Override
    public UserInfoData getUserByAmount(String username){
        System.out.println("==========================================");
        return userInfo.selectUserByAmount(username);
    }
}
