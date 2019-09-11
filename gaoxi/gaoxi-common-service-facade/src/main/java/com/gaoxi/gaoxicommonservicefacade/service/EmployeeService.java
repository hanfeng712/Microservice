/**
 * Project Name:springboot2
 * File Name:FrontService.java
 * Package Name:cn.java.service.impl
 * Date:下午2:36:36
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.gaoxi.gaoxicommonservicefacade.service;

import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;

import java.util.List;

/**
 * Description: <br/>
 * Date: 下午2:36:36 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
public interface EmployeeService {
	
    public List<UserInfoData> getUserByPower(int power, int pageNum, int pageSize);
    /**
     * 添加用户
     * @param account 用户名
     * @param password  密码
     * @param power 权限
     * @return  是否添加成功
     */
    public boolean addUser(String account, String password, int power);

    /**
     * 删除用户
     * @param userid 账户名
     * @return 是否删除成功
     */
    public boolean delUser(Integer userid);
    /**
     * 获得所有的用户
     * @return  返回List集合
     */
    public List<UserInfoData> getAllUser(int pageNum, int pageSize);
}
