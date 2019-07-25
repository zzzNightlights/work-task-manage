package com.example.demo.config;

import com.example.demo.interceptors.AdminInterceptor;
import com.example.demo.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    AdminInterceptor adminInterceptor;

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration loginIntercept = registry.addInterceptor(loginInterceptor);
        //拦截路径
        loginIntercept.addPathPatterns("/**");
        //排除路径
        loginIntercept.excludePathPatterns("/login","/user/do-login","/webjars/**","/assets/**");

        InterceptorRegistration adminIntercept = registry.addInterceptor(adminInterceptor);
        //拦截路径
        adminIntercept.addPathPatterns("/admin/**");

    }

}
