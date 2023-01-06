package com.example.demo.article.service;

import com.example.demo.article.vo.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

  private List<Article> articles;
  private int articleLastId;

  public ArticleService(){
    articles = new ArrayList<>();
    articleLastId = 0;

    makeTestData();
  }

  public void makeTestData() {
    for (int i = 1; i <= 10; i++) {

      String title = "제목" + i;
      String body = "내용" + i;
      writeArticle(title, body);
    }
  }


  public Article writeArticle(String title, String body) {
    int id = articleLastId +1;
    Article article = new Article(id,title,body);
    articles.add(article);
    articleLastId = id;
    return  article;

  }
  public Article getArticle(int id) {
    for (Article article : articles){
      if(article.getId() == id){
        return article;
      }
    }
    return null;
  }

  public void deleteArticle(int id) {
    Article article = getArticle(id);
    articles.remove(article);
  }
  public void modifyArticle(int id, String title, String body) {
    Article article = getArticle(id);
    article.setTitle(title);
    article.setBody(body);

  }

  public List<Article> getArticles() {
    return articles;
  }
}
