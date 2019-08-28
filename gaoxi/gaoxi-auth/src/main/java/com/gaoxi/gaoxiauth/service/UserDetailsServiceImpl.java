package com.gaoxi.gaoxiauth.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ClientDetailsService clientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            try {
                ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
                if(clientDetails!=null){
                    //密码
                    String clientSecret = clientDetails.getClientSecret();
                    return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
                }
            }catch (Exception e){
                return null;
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //TODO:远程调用用户中心根据账号查询用户信息
        /*
        UserInfoData userext = null;
        if(userext == null){
            //返回空给spring security表示用户不存在
            System.out.println("333333333333333");
            return null;
        }*/
        //这里暂时使用静态密码
        String password =new BCryptPasswordEncoder().encode("123");
        List<String> user_permission = new ArrayList<>();
        user_permission.add("course_get_baseinfo");
        user_permission.add("course_find_pic");
        String user_permission_string  = StringUtils.join(user_permission.toArray(), ",");


        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        userDetails.setId("111111");
        userDetails.setUtype("2");
        userDetails.setCompanyId("3");
        userDetails.setName("4");
        userDetails.setUserpic("5");
        return userDetails;
    }
}