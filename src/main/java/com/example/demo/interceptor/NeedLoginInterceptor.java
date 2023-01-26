package com.example.demo.interceptor;

import com.example.demo.vo.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
  private Rq rq;
  public NeedLoginInterceptor(Rq rq) {
    this.rq = rq;
  }
  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handle) throws Exception{

    if(!rq.isLogined()) {

      rq.printHistoryBackJs("로그인 후 이용해주세요.");
      return false;
    }

    return HandlerInterceptor.super.preHandle(req,resp,handle);
  }

}

