package com.gaoxi.gaoxiauth.controller;

import com.gaoxi.gaoxiauth.service.AuthService;
import com.gaoxi.gaoxiauth.utils.BCryptUtil;
import com.gaoxi.gaoxicommonservicefacade.common.api.auth.AuthControllerApi;
import com.gaoxi.gaoxicommonservicefacade.common.auth.ext.AuthToken;
import com.gaoxi.gaoxicommonservicefacade.common.auth.request.LoginRequest;
import com.gaoxi.gaoxicommonservicefacade.common.auth.response.AuthCode;
import com.gaoxi.gaoxicommonservicefacade.common.auth.response.JwtResult;
import com.gaoxi.gaoxicommonservicefacade.common.auth.response.LoginResult;
import com.gaoxi.gaoxicommonservicefacade.common.base.response.CommonCode;
import com.gaoxi.gaoxicommonservicefacade.common.base.response.ResponseResult;
import com.gaoxi.gaoxicommonservicefacade.common.exception.ExceptionCast;
import com.gaoxi.gaoxicommonservicefacade.common.utils.CookieUtil;
import com.gaoxi.gaoxicommonservicefacade.common.utils.ImageCode;
import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * http://127.0.0.1:40400/auth/userlogin?username=itcast&password=123
 **/
@RestController
@RequestMapping("/")
public class AuthController extends AuthControllerApi {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    AuthService authService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("=========11111===============");
        return "success";
    }
    @Override
    @PostMapping("/userlogin")
    public LoginResult login(LoginRequest loginRequest,HttpSession session) {
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //账号
        String username = loginRequest.getUsername();
        //密码
        String password = loginRequest.getPassword();
        //验证码
        String verifycode = loginRequest.getVerifycode();
        String code = (String) session.getAttribute(ImageCode.CODENAME);
        System.out.println(verifycode);
        System.out.println(code);
        if(verifycode!=null&&code!=null&&verifycode.equals(code)) {
            System.out.println("=======================================");
            //申请令牌
            AuthToken authToken = authService.login(username, password, clientId, clientSecret);

            //用户身份令牌
            String access_token = authToken.getAccess_token();
            //将令牌存储到cookie
            this.saveCookie(access_token);

            return new LoginResult(CommonCode.SUCCESS, access_token);
        }
        return new LoginResult(CommonCode.VERIFY_FAIL, "");
    }
    @PostMapping("/userregister")
    public LoginResult register(LoginRequest loginRequest) {
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        BCryptUtil bBCryptUtil = new BCryptUtil();
        String passwordTmp = bBCryptUtil.encode(loginRequest.getPassword());
        UserInfoData userext = new UserInfoData();
        userext.setUsername(loginRequest.getUsername());
        userext.setPassword(passwordTmp);
        authService.register(userext);
        return new LoginResult(CommonCode.SUCCESS,"success");
    }

    @RequestMapping(value = "/createImage")
    @ResponseBody
    public void createImage(@RequestParam String code, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println("---------createImage----------");
        ImageCode.createImage(response,session);
    }
    //将令牌存储到cookie
    private void saveCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,cookieMaxAge,false);

    }
    //从cookie删除token
    private void clearCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,0,false);

    }

    //退出
    @Override
    @PostMapping("/userlogout")
    public ResponseResult logout() {
        //取出cookie中的用户身份令牌
        String uid = getTokenFormCookie();
        //删除redis中的token
        boolean result = authService.delToken(uid);
        //清除cookie
        this.clearCookie(uid);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    @GetMapping("/userjwt")
    public JwtResult userjwt() {
        System.out.println("========userjwt==============");
        //取出cookie中的用户身份令牌
        String uid = getTokenFormCookie();
        if(uid == null){
            System.out.println("========userjwt======1========");
            return new JwtResult(CommonCode.FAIL,null);
        }
        System.out.println("========userjwt======2========");
        System.out.println(uid);
        //拿身份令牌从redis中查询jwt令牌
        AuthToken userToken = authService.getUserToken(uid);
        if(userToken!=null){
            //将jwt令牌返回给用户
            String jwt_token = userToken.getJwt_token();
            System.out.println("========userjwt======3========");
            return new JwtResult(CommonCode.SUCCESS,jwt_token);
        }
        System.out.println("========userjwt======4========");
        return null;
    }

    //取出cookie中的身份令牌
    private String getTokenFormCookie(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if(map!=null && map.get("uid")!=null){
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }
}
