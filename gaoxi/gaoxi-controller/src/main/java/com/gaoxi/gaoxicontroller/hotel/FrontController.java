/**
 * Project Name:springboot2
 * File Name:FrontController.java
 * Package Name:cn.java.controller.front
 * Date:下午2:15:43
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package com.gaoxi.gaoxicontroller.hotel;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.gaoxicommonservicefacade.service.FrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: <br/>
 * Date: 下午2:15:43 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@Controller
@RequestMapping("/front")
public class FrontController {

    @Reference(version = "1.0.0", loadbalance = "roundrobin")
    private FrontService frontService;

    @RequestMapping("/selectUser.do")
    @ResponseBody
    public int selectUser(String username, String password) {
        return frontService.getUser(username, password);
    }

    // ---------------------视图解析器-------------------------------------
    @RequestMapping("/testView.do")
    public String testView() {
        return "front/testView.jsp";
    }

}
