/**
 * Project Name:springboot2
 * File Name:FrontServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:下午2:33:45
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.gaoxi.gaoxiuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.mapper.FrontMapper;
import cn.java.service.FrontService;

/**
 * Description: <br/>
 * Date: 下午2:33:45 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Service
public class FrontServiceImpl implements FrontService {
    @Autowired
    private FrontMapper frontMapper;

    /**
     * 简单描述该方法的实现功能（可选）.
     * 
     * @see cn.java.service.impl.FrontService#getUser(String,
     *      String)
     */
    @Override
    public int getUser(String username, String password) {
        return frontMapper.isUserExist(username, password);
    }
}
