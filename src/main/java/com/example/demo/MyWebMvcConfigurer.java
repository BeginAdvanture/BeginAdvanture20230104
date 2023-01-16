package com.example.demo;

import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
  @Autowired
  BeforeActionInterceptor beforeActionInterceptor;
  @Autowired
  NeedLoginInterceptor needLoginInterceptor;
  @Override
  public void addInterceptor(InterceptorRegistration registry){
    registry.addInterceptor(beforeActionInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/css/**")
        .excludePathPatterns("/js/**")
        .excludePathPatterns("/error");

    registry.addInterceptor(needLoginInterceptor)
          .addPathPatterns("/usr/article/write")
          .excPathPatterns("/usr/article/doWrite")
          .excPathPatterns("/usr/article/modify")
          .excPathPatterns("/usr/article/doModify")
        .excPathPatterns("/usr/article/doDelete");




  }

}
