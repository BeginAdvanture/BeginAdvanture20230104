package com.example.demo.interceptor;

import com.example.demo.vo.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,Object handle) throws Exception{
    Rq rq = new Rq(req,resp);
   // rq.getAttribute("rq",rq);
    req.setAttribute("rq",rq);
    return HandlerInterceptor.super.preHandle(req,resp,handle);
  }

}
