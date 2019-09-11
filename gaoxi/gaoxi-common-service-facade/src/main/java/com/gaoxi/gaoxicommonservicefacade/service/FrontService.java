/**
 * Project Name:springboot2
 * File Name:FrontService.java
 * Package Name:cn.java.service.impl
 * Date:下午2:36:36
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.gaoxi.gaoxicommonservicefacade.service;

/**
 * Description: <br/>
 * Date: 下午2:36:36 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
public interface FrontService {

    /**
     * 
     * Description: 判断用户是否登录成功<br/>
     *
     * @author 丁鹏
     * @param username
     * @param password
     * @return
     */
    int getUser(String username, String password);

}
