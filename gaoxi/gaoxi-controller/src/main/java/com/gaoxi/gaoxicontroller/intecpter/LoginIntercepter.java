package com.gaoxi.gaoxicontroller.intecpter;

import com.gaoxi.gaoxicommonservicefacade.common.utils.CookieUtil;

import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.gaoxi.gaoxicontroller.utils.AuthService;


public class LoginIntercepter implements HandlerInterceptor{
    private AuthService authService;
    /**
     * 进入controller方法之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginIntercepter------->preHandle");
        if (this.authService==null){
            this.authService = new AuthService();
        }
        //取cookie中的身份令牌
        String tokenFromCookie = authService.getTokenFromCookie(request);
        if(StringUtils.isEmpty(tokenFromCookie)){
            //拒绝访问
            access_denied();
            System.out.println("1111111111111111111111111111");
            return false;
        }
        //从redis取出jwt的过期时间
        boolean expire = authService.getExpire(tokenFromCookie);
        if(expire==false){
            System.out.println("33333333333333333333333333");
            //拒绝访问
            access_denied();
            return false;
        }
        //从header中取jwt
        String jwtFromHeader = authService.getJwtFromHeader(request);
        if(StringUtils.isEmpty(jwtFromHeader)){
            //拒绝访问
            access_denied();
            System.out.println("22222222222222222222");
            return false;
        }
        //从redis取出jwt的过期时间
        boolean expire1 = authService.getExpire(tokenFromCookie);
        if(expire1==false){
            System.out.println("33333333333333333333333333");
            //拒绝访问
            access_denied();
            return false;
        }
        return true;
    }

    /**
     * 调用完controller之后，视图渲染之前
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("LoginIntercepter------->postHandle");

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 整个完成之后，通常用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("LoginIntercepter------->afterCompletion");

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    //拒绝访问
    private void access_denied(){
        return;
    }

}
