package com.example.demo.vo;

import com.example.demo.service.MemberService;
import com.example.demo.utill.Ut;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {



  @Getter
  private boolean isLogined;
  @Getter
  private  int loginedMemberId;
  private HttpServletRequest req;
  private HttpServletResponse resp;
  private HttpSession session;
  @Getter
  private Member loginedMember;

  public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
    this.req = req;
    this.resp = resp;
    this.session = req.getSession();
    HttpSession httpSession = req.getSession();
    int loginedMemberId = 0;
    boolean isLogined = false;

    if(httpSession.getAttribute("loginMemberId")!=null){
      isLogined = true;
      loginedMemberId = (int) session.getAttribute("logindMemberId");
      loginedMember = memberService.getMemberById(loginedMemberId);
    }
    this.isLogined = isLogined;
    this.loginedMemberId =loginedMemberId;
    this.loginedMember = loginedMember;
    this.req.setAttribute("rq",this);
  }

  public boolean isNotLogined(){
    return !isLogined;
  }

  public void printHistoryBackJs(String msg) {
    resp.setContentType("text/html; charset=UTF-8");
    println("<script>");
    print(Ut.jsHistoryBack(msg));
  }
  public void print(String str){
    try {
      resp.getWriter().append(str);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public void println(String str){
    print(str+"\n");
  }

  public void login(Member member) {
    session.setAttribute("loginedMemberId",member.getId());
  }

  public void logout() {
    session.removeAttribute("loginedMemberId");
  }

  public String historyBackJsOnView(String msg) {
    req.setAttribute("msg",msg);
    req.setAttribute("historyBack",true);
    return "common/js";
  }

  public String jsHistoryBack(String msg) {
    return Ut.jsHistoryBack(msg);
  }

  public String jsReplace(String msg,String uri) {
    return Ut.jsReplace(msg,uri);
  }
  //이 메서드는 Rq 객체가 자연스럽게 생성되도록 유도하는 역할
  //BeforeActionInterceptor 호출 무조건 적으로 필요
  public void initOnBeforeActionInterceptor() {
  }

  public  String getCurrentUri(){
    String currentUri = (String)req.getAttribute("javax.servlet.forward.request_uri");
    String queryString = req.getQueryString();
    if(queryString != null && queryString.length() > 0){
      currentUri += "?" + queryString;
    }
    return currentUri;
  }

  public String getEncodedCurrentUri(){
    return Ut.getUriEncoded(getCurrentUri());
  }
}
