package com.example.demo.controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.service.ArticleService;
import com.example.demo.utill.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsrArticleController {

  @Autowired
  private ArticleService articleService;


  ///////////////////////////////////// 매서드
  @RequestMapping("/usr/article/doAdd")
  @ResponseBody
  public ResultData<Article> doAdd(HttpSession httpSession, String title, String body) {
    int loginedMemberId = 0;
    boolean isLogined = false;

    if(httpSession.getAttribute("loginMemberId")!=null){
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("logindMemberId");
    }
    if(isLogined==false){
      return ResultData.from("F-A","로그인 후 이용하세요");
    }
    if(Ut.empty(title)){
      return ResultData.from("F-1","title을 입력해주세요");
    }
    if(Ut.empty(body)){
      return ResultData.from("F-2","body를 입력해주세요");
    }


    ResultData<Integer> writeArticleRd = articleService.writeArticle(loginedMemberId,title,body);
    int id = writeArticleRd.getData1();
    Article article = articleService.getForPrintArticle(loginedMemberId,id);
    return ResultData.newData(writeArticleRd,"article",article);
  }


  @RequestMapping("/usr/article/detail")
  public String showDetail(HttpSession httpSession,Model model,int id) {
    int loginedMemberId = 0;
    boolean isLogined = false;

    if(httpSession.getAttribute("loginMemberId")!=null){
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("loginMemberId");
    }

    Article article = articleService.getForPrintArticle(loginedMemberId,id);
   //model.addAttribute("article",article);
    return "/usr/article/detail";
  }


  @RequestMapping("/usr/article/list")

  public String showList(HttpSession httpSession,Model model) {
    int loginedMemberId = 0;
    boolean isLogined = false;

    if(httpSession.getAttribute("loginMemberId")!=null){
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("logindMemberId");
    }

    List<Article> articles = articleService.getForPrintArticles(loginedMemberId);
    //model.addAttribute("article",article);

    return "usr/article/list";
  }
  @RequestMapping("/usr/article/doDelete")
  @ResponseBody
  public String doDelete(HttpSession httpSession,int id) {

    int loginedMemberId = 0;
    boolean isLogined = false;
    Article article = articleService.getForPrintArticle(loginedMemberId,id);

    if(httpSession.getAttribute("loginMemberId")!=null){
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("logindMemberId");
    }

    if(isLogined == false){
      return Ut.jsHistoryBack("로그인 후 이용하세요");
    }
    if(article.getMemberId() != loginedMemberId){
      return Ut.jsHistoryBack("권한이 없습니다.");
    }
    if( article == null){
      return  Ut.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.",id));
    }
    articleService.deleteArticle(id);

    return Ut.jsReplace( Ut.f("%d번 게시물을 삭제했다.",id),"../article/list");
  }

  @RequestMapping("/usr/article/doModify")
  @ResponseBody
  public ResultData<Integer> doModify(HttpSession httpSession, int id,String title,String body) {
    int loginedMemberId = 0;
    boolean isLogined = false;

    if(httpSession.getAttribute("loginMemberId")!=null){
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("loginMemberId");
    }


    Article article = articleService.getForPrintArticle(loginedMemberId,id);

    if( article == null){
      return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.",id));
    }
    articleService.modifyArticle(id,title,body);
    return  ResultData.from("S-1", Ut.f("%d번 게시물을 수정했다.",id),"id",id);
  }



}