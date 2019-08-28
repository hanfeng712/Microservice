/**
 * Project Name:springboot2
 * File Name:FrontMapper.java
 * Package Name:cn.java.mapper
 * Date:下午2:27:00
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.gaoxi.gaoxicommonservicefacade.dao;

import org.apache.ibatis.annotations.Select;

/**
 * Description: <br/>
 * Date: 下午2:27:00 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
public interface FrontMapper {

    /**
     * 
     * Description: 判断用户是否登录成功<br/>
     *
     * @author 丁鹏
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT COUNT(*) AS num FROM users WHERE username=#{arg0} AND PASSWORD=#{arg1}")
    int isUserExist(String username, String password);

}
