package com.gaoxi.gaoxicommonservicefacade.common.api.auth;

import com.gaoxi.gaoxicommonservicefacade.common.auth.request.LoginRequest;
import com.gaoxi.gaoxicommonservicefacade.common.auth.response.JwtResult;
import com.gaoxi.gaoxicommonservicefacade.common.auth.response.LoginResult;
import com.gaoxi.gaoxicommonservicefacade.common.base.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator.
 */
@Api(value = "用户认证",description = "用户认证接口")
public abstract class AuthControllerApi {
    @ApiOperation("登录")
    public abstract LoginResult login(LoginRequest loginRequest, HttpSession session);

    @ApiOperation("退出")
    public abstract ResponseResult logout();

    @ApiOperation("查询用户jwt令牌")
    public abstract JwtResult userjwt();
}
