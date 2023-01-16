package com.example.demo.vo;

import com.example.demo.utill.Ut;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

import java.io.IOException;

public class Rq {


  @Getter
  private boolean isLogined;
  @Getter
  private  int loginedMemberId;
  private HttpServletRequest req;
  private HttpServletResponse resp;
  private HttpSession session;
  public Rq(HttpServletRequest req, HttpServletResponse resp) {
    this.req = req;
    this.resp = resp;
    this.session = req.getSession();
    HttpSession httpSession = req.getSession();
    int loginedMemberId = 0;
    boolean isLogined = false;

    if(httpSession.getAttribute("loginMemberId")!=null){
      isLogined = true;
      loginedMemberId = (int) session.getAttribute("logindMemberId");
    }
    this.isLogined = isLogined;
    this.loginedMemberId =loginedMemberId;
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
}
