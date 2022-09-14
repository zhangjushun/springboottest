package com.cdv.springbootb.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyIntercepterConfig implements WebMvcConfigurer {
    @Autowired
    LoginIntercepter loginIntercepter;
    //添加拦截器对象
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String paths[] ={"/**"};
        registry.addInterceptor(loginIntercepter).addPathPatterns(paths);
    }
}
