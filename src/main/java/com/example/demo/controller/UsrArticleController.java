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
  public ResultData doAdd(HttpSession httpSession, String title, String body) {
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
    Article article = articleService.getArticle(id);
    return ResultData.newData(writeArticleRd,"article",article);
  }


  @RequestMapping("/usr/article/getArticle")
  @ResponseBody
  public ResultData<Article> getArticle(int id) {
    Article article = articleService.getArticle(id);
    if( article == null){
      return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.",id));
    }
    return ResultData.from("S-1", Ut.f("%d번 게시물입니다.",id),"article",article);
  }


  @RequestMapping("/usr/article/list")

  public String showList(Model model) {
    List<Article> articles = articleService.getArticles();
    model.addSub;

    return "usr/article/list";
  }
  @RequestMapping("/usr/article/doDelete")
  @ResponseBody
  public ResultData<Integer> doDelete(HttpSession httpSession,int id) {
    Article article = articleService.getArticle(id);
    int loginedMemberId = 0;
    boolean isLogined = false;

    if(httpSession.getAttribute("loginMemberId")!=null){
      isLogined = true;
      loginedMemberId = (int) httpSession.getAttribute("logindMemberId");
    }
    if(isLogined == false){
      return ResultData.from("F-A","로그인 후 이용하세요");
    }
    if(article.getMemberId() != loginedMemberId){
      return ResultData.from("F-2","권한이 없습니다.");
    }
    if( article == null){
      return  ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.",id));
    }
    articleService.deleteArticle(id);

    return ResultData.from("S-1", Ut.f("%d번 게시물을 삭제했다.",id),"id",id);
  }

  @RequestMapping("/usr/article/doModify")
  @ResponseBody
  public ResultData<Integer> doModify(HttpSession httpSession, int id,String title,String body) {
    Article article = articleService.getArticle(id);

    if( article == null){
      return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.",id));
    }
    articleService.modifyArticle(id,title,body);
    return  ResultData.from("S-1", Ut.f("%d번 게시물을 수정했다.",id),"id",id);
  }



}