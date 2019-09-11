package com.gaoxi.gaoxicontroller.hotel;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.gaoxicommonservicefacade.common.api.auth.AdminControllerApi;
import com.gaoxi.gaoxicommonservicefacade.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping()
public class AdminController extends AdminControllerApi {
    @Reference(version = "1.0.0", loadbalance = "roundrobin")
    private UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(String name,String value){
        System.out.println("=============hello=============");
        return "success";
    }
    @RequestMapping(value = "/Login")
    @ResponseBody
    public String Login(@RequestParam String name, @RequestParam String password) {
        String ret = this.userService.login(name);
        System.out.println(ret);
        return ret;
    }
}