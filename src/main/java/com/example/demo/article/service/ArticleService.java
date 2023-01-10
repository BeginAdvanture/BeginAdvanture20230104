package com.example.demo.article.service;

import com.example.demo.article.repository.ArticleRepository;
import com.example.demo.article.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository){
    this.articleRepository = articleRepository;

  }

  public int writeArticle(String title, String body) {
   articleRepository.writeArticle(title,body);
   return articleRepository.getLastInsertId();
  }

  public Article getArticle(int id) {
    return articleRepository.getArticle(id);
  }

  public List<Article> getArticles() {
    return articleRepository.getArticles();
  }

  public void deleteArticle(int id) {
    articleRepository.deleteArticle(id);
  }

  public void modifyArticle(int id, String title, String body) {
    articleRepository.modifyArticle(id,title,body);
  }
}
