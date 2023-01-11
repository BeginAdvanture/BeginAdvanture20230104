package com.example.demo.service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.utill.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository){
    this.articleRepository = articleRepository;

  }

  public ResultData writeArticle(int memberId, String title, String body) {
   articleRepository.writeArticle(memberId,title,body);

   int id = articleRepository.getLastInsertId();
   return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.",id),"id",id);
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
