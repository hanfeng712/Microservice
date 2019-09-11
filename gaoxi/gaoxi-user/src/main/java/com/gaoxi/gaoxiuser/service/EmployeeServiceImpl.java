/**
 * Project Name:springboot2
 * File Name:FrontServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:下午2:33:45
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.gaoxi.gaoxiuser.service;

import com.gaoxi.gaoxicommonservicefacade.common.utils.UUIDUtil;
import com.gaoxi.gaoxicommonservicefacade.dao.UserInfo;
import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;
import com.gaoxi.gaoxicommonservicefacade.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: <br/>
 * Date: 下午2:33:45 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
    private UserInfo userInfoMapper;
    
    @Override
    public List<UserInfoData> getUserByPower(int power,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userInfoMapper.selectByPower(power);
    }
    
    @Override
    public List<UserInfoData> getAllUser(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userInfoMapper.getAllUser();
    }
    
    @Override
    public boolean addUser(String account, String password, int power){
        UserInfoData user = new UserInfoData();
        user.setUsername(account);
        user.setPassword(password);
        user.setPower(power);
        user.setIdnumber(UUIDUtil.generateShortUuid());
        int insert = userInfoMapper.insertSelective(user);
        return insert>0?true:false;
    }

    @Override
    public boolean delUser(Integer userid) {
        int i = userInfoMapper.deleteByPrimaryKey(userid);
        return i>0?true:false;
    }
}
