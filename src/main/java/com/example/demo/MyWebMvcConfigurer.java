package com.example.demo;

import com.example.demo.interceptor.BeforeActionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
  @Autowired
  BeforeActionInterceptor beforeActionInterceptor;
  @Override
  public void addInterceptor(InterceptorRegistration registry){
    registry.addInterceptor(beforeActionInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/css/**")
        .excludePathPatterns("/js/**")
        .excludePathPatterns("/error");
  }
}
