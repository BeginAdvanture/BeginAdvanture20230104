package com.example.demo.config;

import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;
import com.example.demo.interceptor.NeedLogoutInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
  @Autowired
  BeforeActionInterceptor beforeActionInterceptor;
  @Autowired
  NeedLoginInterceptor needLoginInterceptor;
  @Autowired
  NeedLogoutInterceptor needLogoutInterceptor;
  @Override
  public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(beforeActionInterceptor)
          .addPathPatterns("/**")
          .excludePathPatterns("/css/**")
          .excludePathPatterns("/js/**")
          .excludePathPatterns("/error");

    registry.addInterceptor(needLoginInterceptor)
          .addPathPatterns("/usr/article/myPage")
          .addPathPatterns("/usr/article/checkPassword")
          .addPathPatterns("/usr/article/doCheckPassword")
          .addPathPatterns("/usr/article/write")
          .addPathPatterns("/usr/article/doWrite")
          .addPathPatterns("/usr/article/modify")
          .addPathPatterns("/usr/article/doModify")
          .addPathPatterns("/usr/article/doDelete")
          .addPathPatterns("/usr/reactionPoint/doGoodReaction")
          .addPathPatterns("/usr/reactionPoint/doBadReaction")
          .addPathPatterns("/usr/reactionPoint/doCancelGoodReaction")
          .addPathPatterns("/usr/reactionPoint/doCancelBadReaction");

    registry.addInterceptor(needLogoutInterceptor)
        .addPathPatterns("/usr/member/join")
        .addPathPatterns("/usr/member/doJoin")
        .addPathPatterns("/usr/member/login")
        .addPathPatterns("/usr/member/doLogin")
        .addPathPatterns("/usr/member/findLoginId")
        .addPathPatterns("/usr/member/doFindLoginId")
        .addPathPatterns("/usr/member/findLoginPw")
        .addPathPatterns("/usr/member/doFindLoginPw");

  }

}
