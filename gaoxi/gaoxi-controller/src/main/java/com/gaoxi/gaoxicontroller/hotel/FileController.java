package com.gaoxi.gaoxicontroller.hotel;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.gaoxicommonservicefacade.service.UserService;
import com.gaoxi.gaoxicontroller.utils.FileUtil;
import com.gaoxi.gaoxicontroller.utils.State;
import com.gaoxi.gaoxicontroller.utils.StateSignal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/upFile")
public class FileController {
    @Reference(version = "1.0.0", loadbalance = "roundrobin")
    private UserService userService;

    @RequestMapping("/upFilePhoto")
    @ResponseBody
    public Map upFilePhoto(@RequestParam MultipartFile file,@RequestParam int userid){
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();

        String filePath = ".\\src\\main\\resources\\static\\File\\";
        String RealfilePath = "File\\"+fileName;
        boolean photo = userService.photo(userid, RealfilePath);
        boolean b = false;
        try {
           b = FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StateSignal signal = new StateSignal();
        if(b&&photo){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }
}
