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
  private  Rq rq;
  public BeforeActionInterceptor(Rq rq) {
    this.rq = rq;
  }
  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,Object handle) throws Exception{
    // 이제는 Rq 객체가 자동으로 만들어지기 때문에 필요없다.
    rq.initOnBeforeActionInterceptor();
    return HandlerInterceptor.super.preHandle(req,resp,handle);
  }

}
