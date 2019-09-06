package com.gaoxi.gaoxicontroller.intecpter;

import com.gaoxi.gaoxicontroller.utils.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer  {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/**");
        //.excludePathPatterns("/api2/xxx/**"); //拦截全部 /*/*/**

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}