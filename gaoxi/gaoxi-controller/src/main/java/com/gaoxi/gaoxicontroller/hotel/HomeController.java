/**
 * Project Name:springboot2
 * File Name:HomeController.java
 * Package Name:cn.java.controller.front
 * Date:下午3:39:40
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.gaoxi.gaoxicontroller.hotel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: <br/>
 * Date: 下午3:39:40 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String toIndex() {// 配置默认访问首页
        return "redirect:/index.jsp";
    }
    @RequestMapping(value = "/testAction")
    @ResponseBody
    public String testAction() {
    	return "success";
    }
}
