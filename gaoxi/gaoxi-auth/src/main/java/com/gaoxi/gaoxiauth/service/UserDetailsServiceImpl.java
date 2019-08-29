package com.gaoxi.gaoxiauth.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;
import com.gaoxi.gaoxicommonservicefacade.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ClientDetailsService clientDetailsService;
    @Reference(version = "1.0.0", loadbalance = "roundrobin")
    private UserService userService;

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

        UserInfoData userext = userService.getUserByAmount(username);
        if(userext == null){
            //返回空给spring security表示用户不存在
            return null;
        }
        //这里暂时使用静态密码
        String password = userext.getPassword();//new BCryptPasswordEncoder().encode("123");
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