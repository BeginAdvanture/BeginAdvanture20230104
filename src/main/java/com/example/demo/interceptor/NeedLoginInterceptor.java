package com.example.demo.interceptor;

import com.example.demo.vo.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handle) throws Exception{
    //Rq rq = new Rq(req);

    Rq rq = (Rq) req.getAttribute("rq");
    if(!rq.isLogined()) {

      rq.printHistoryBackJs("로그인 후 이용해주세요.");
      return false;
    }

    return HandlerInterceptor.super.preHandle(req,resp,handle);
  }

}

