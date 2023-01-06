package com.example.demo.article.controller;

import com.example.demo.article.vo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}