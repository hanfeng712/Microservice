package com.gaoxi.gaoxicontroller.hotel;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;
import com.gaoxi.gaoxicommonservicefacade.service.EmployeeService;
import com.gaoxi.gaoxicontroller.utils.State;
import com.gaoxi.gaoxicontroller.utils.StateSignal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/EmployeeManager")
public class EmployeeController {
    @Reference(version = "1.0.0", loadbalance = "roundrobin")
    EmployeeService employeeService;
    
    /**
     * 获取对应权限的用户列表
     * @param power
     * @return
     */
    @RequestMapping(value = "/getUserByPower.do")
    public Map getUserByPower(@RequestParam int power,@RequestParam int pageNum,@RequestParam int pageSize){
        List<UserInfoData> Users = employeeService.getUserByPower(power,pageNum,pageSize);
        System.out.println(Users);
        StateSignal signal = new StateSignal();
        if(Users!=null){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
            signal.put("List",Users);
            signal.put("pageNum",pageNum);
            signal.put("pageSize",pageSize);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return  signal.getResult();
    }
    
    /**
     * 添加用户(已测试)
     * @param useraccount  用户名
     * @param password  密码
     * @param power     权限
     * @return
     */
    @RequestMapping(value = "/addUser.do")
    public Map addUser(@RequestParam String useraccount, @RequestParam String password, @RequestParam String power){
        boolean add = employeeService.addUser(useraccount,password,Integer.parseInt(power));
        StateSignal signal = new StateSignal();
        if(add){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }
    
    /**
     * 删除用户(已测试)
     * @param userid    用户id
     * @return
     */
    @RequestMapping(value = "/delUser.do")
    public Map delUser(@RequestParam("userid")Integer userid){
        boolean del = employeeService.delUser(userid);
        StateSignal signal = new StateSignal();
        if(del){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }

    /**
     * 获取用户列表(已测试)
     * @return
     */
    @RequestMapping(value = "/getAllUser.do")
    public Map getAllUser(@RequestParam int pageNum,@RequestParam int pageSize){
    	List<UserInfoData> allUser = employeeService.getAllUser(pageNum,pageSize);
        StateSignal signal = new StateSignal();
        if(allUser!=null){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
            signal.put("List",allUser);
            signal.put("pageNum",pageNum);
            signal.put("pageSize",pageSize);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }

        return  signal.getResult();
    }
}
