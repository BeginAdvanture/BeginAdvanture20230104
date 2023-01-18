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

  public Article getForPrintArticle(int actorId,int id) {

    Article article= articleRepository.getForPrintArticle(id);
    upDateForPrintData(actorId,article);
    return article;
  }

  public List<Article> getForPrintArticles(int actorId) {
    List<Article> articles = articleRepository.getForPrintArticles();
    for(Article article:articles){
      upDateForPrintData(actorId, article);
    }
    return articles;
  }

  private void upDateForPrintData(int actorId, Article article) {
    if(article == null){
      return ;
    }
    ResultData actorCanDeleteRd =  actorCanDelete(actorId,article);
    article.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());

    ResultData actorCanModifyRd =  actorCanModify(actorId,article);
    article.setExtra__actorCanModify(actorCanModifyRd.isSuccess());
  }

  public void deleteArticle(int id) {
    articleRepository.deleteArticle(id);
  }


  public ResultData<Article> modifyArticle(int id, String title, String body){
    articleRepository.modifyArticle(id,title,body);
    Article article = getForPrintArticle(0,id);
    return ResultData.from("S-1",Ut.f("게시물을 수정하였습니다.",id),"article",article );
  }


  public ResultData actorCanModify(int actorId, Article article){
    if(article ==null){
      return ResultData.from("F-1","권한이 없습니다.");
    }
    if(article.getMemberId() != actorId){
      return ResultData.from("F-2","권한이 없습니다.");
    }
    return ResultData.from("S-1","수정 가능합니다.");
  }


  public ResultData actorCanDelete(int actorId, Article article){
    if(article ==null){
      return ResultData.from("F-1","권한이 없습니다.");
    }
    if(article.getMemberId() != actorId){
      return ResultData.from("F-2","권한이 없습니다.");
    }
    return ResultData.from("S-1","게시물 삭제가 가능합니다.");
  }
}
