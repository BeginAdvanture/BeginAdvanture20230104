package com.example.demo.article.controller;

import com.example.demo.article.vo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller

public class UsrArticleController {
  int articleLastId;
  private List<Article> articles;

  public UsrArticleController() {
    articles = new ArrayList<>();
    articleLastId = 0;
    makeTestData();
  }

  private void makeTestData() {
    for(int i=1; i<=10; i++){

      String title = "제목" + i;
      String body = "내용" + i;
      writeArticle(title,body);
    }
  }

  private Article writeArticle(String title, String body) {
    int id = articleLastId +1;
    Article article = new Article(id,title,body);
    articles.add(article);
    articleLastId = id;
    return  article;
  }

  private Article getArticle(int id) {
    for (Article artile : articles){
      if(artile.getId() == id){
        return article;
      }
    }
    return null;
  }

  private void deleteArticle(int id) {
    Article article = getArticle(id);
    articles.remove(article);
  }
  private void modifyArticle(int id, String title, String body) {
    Article article = getArticle(id);
    article.setTitle(title);
    article.setBody(body);
    
  }

  ///////////////////////////////////// 매서드
  @RequestMapping("/usr/article/doAdd")
  @ResponseBody
  public Article doAdd(String title, String body) {
    Article article = writeArticle(title,body);

    return article;
  }

  @RequestMapping("/usr/article/getarticles")
  @ResponseBody
  public List<Article> getarticles() {
    return articles;
  }
  @RequestMapping("/usr/article/doDeleate")
  @ResponseBody
  public String doDeleate(int id) {
    Article article = getArticle(id)

    if( article == null){
      return  id + "게시물 존재하지 않습니다.";
    }
    deleteArticle(id);

    return id + "ID가 삭제되었습니다.";
  }

  @RequestMapping("/usr/article/doModify")
  @ResponseBody
  public String doModify(int id,String title,String body) {
    Article article = getArticle(id);

    if( article == null){
      return  id + "게시물을 수정하였습니다.";
    }
    modifyArticle(id,title,body);
    return id + "게시물을 수정하였습니다.";
  }



}