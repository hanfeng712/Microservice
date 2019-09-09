package com.gaoxi.gaoxicommonservicefacade.service;

import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;

/**
 * @author sifu
 * @version 1.0
 * @date 2017/12/14
 */
public interface UserService {
    public String login(String username);
    public UserInfoData selectUserByKey(int userid);
    public UserInfoData getUserByAmount(String username);
    public int insertSelective(UserInfoData user);
}
