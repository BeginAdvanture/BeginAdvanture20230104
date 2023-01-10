package com.example.demo.article.controller;

import com.example.demo.article.service.ArticleService;
import com.example.demo.article.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsrArticleController {

  @Autowired
  private ArticleService articleService;


  ///////////////////////////////////// 매서드
  @RequestMapping("/usr/article/doAdd")
  @ResponseBody
  public Article doAdd(String title, String body) {
    int id = articleService.writeArticle(title,body);
    Article article = articleService.getArticle(id);
    return article;
  }


  @RequestMapping("/usr/article/getArticle")
  @ResponseBody
  public Object getArticle(int id) {
    Article article = articleService.getArticle(id);
    if( article == null){
      return  id + "게시물이 존재하지 않습니다.";
    }
    return article;
  }


  @RequestMapping("/usr/article/getarticles")
  @ResponseBody
  public List<Article> getarticles() {
    return articleService.getArticles();
  }
  @RequestMapping("/usr/article/doDeleate")
  @ResponseBody
  public String doDeleate(int id) {
    Article article = articleService.getArticle(id);

    if( article == null){
      return  id + "게시물이 존재하지 않습니다.";
    }
    articleService.deleteArticle(id);

    return id + "ID가 삭제되었습니다.";
  }

  @RequestMapping("/usr/article/doModify")
  @ResponseBody
  public String doModify(int id,String title,String body) {
    Article article = articleService.getArticle(id);

    if( article == null){
      return  id + "게시물이 존재하지 않습니다.";
    }
    articleService.modifyArticle(id,title,body);
    return id + "게시물을 수정하였습니다.";
  }



}