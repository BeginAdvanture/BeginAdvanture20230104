package com.example.demo.interceptor;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
  @Autowired
  private MemberService memberService;
  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,Object handle) throws Exception{
    Rq rq = new Rq(req,resp,memberService);
   // rq.getAttribute("rq",rq);
    req.setAttribute("rq",rq);
    return HandlerInterceptor.super.preHandle(req,resp,handle);
  }

}