package com.example.demo.controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.utill.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsrArticleController {
  private ArticleService articleService;

  private BoardService boardService;
  private Rq rq;
  public UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
    this.articleService = articleService;
    this.boardService = boardService;
    this.rq = rq;
  }



  ///////////////////////////////////// 매서드
  @RequestMapping("/usr/article/write")
  public String showWrite(HttpServletRequest req) {
    return "usr/article/write";
  }
  @RequestMapping("/usr/article/doWrite")
  public String doWrite(int boardId,String title, String body,String replaceUri) {

    ////

  //}
    if(rq.isLogined()==false){
      return rq.jsHistoryBack("로그인 후 이용하세요");
    }
    if(Ut.empty(title)){
      return rq.jsHistoryBack("title을 입력해주세요");
    }
    if(Ut.empty(body)){
      return rq.jsHistoryBack("body를 입력해주세요");
    }


    ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(),boardId,title,body);
    int id = writeArticleRd.getData1();
    if(Ut.empty(replaceUri)){
      replaceUri = Ut.f("../article/detail?id=%d",id);
    }
    return rq.jsReplace(Ut.f("%d번 게시물이 생성되었습니다.",id),replaceUri);
  }


  @RequestMapping("/usr/article/detail")
  public String showDetail(Model model,int id) {


    Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(),id);
    // model.addAttribute("article",article);
    return "/usr/article/detail";
  }


  @RequestMapping("/usr/article/list")
  public String showList(Model model, @RequestParam(defaultValue = "1") int boardId,
                         @RequestParam(defaultValue = "1")  int page,
                         @RequestParam(defaultValue = "title,body") String searchKeywordTypeCode,@RequestParam(defaultValue = "")String searchKeyword) {

    Board board = boardService.getBoardById(boardId);
    if(board == null){
      return rq.historyBackJsOnView(Ut.f("%d번 게시판은 존재하지 않습니다.",boardId));
    }
    int articlesCount = articleService.getArticlesCount(boardId,searchKeywordTypeCode,searchKeyword);
    int itemsCountInAPage = 10;
    int pagesCount = (int) Math.ceil((double) articlesCount / itemsCountInAPage);

    List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId(),boardId
        ,itemsCountInAPage,page);
    //model.addAttribute("boardId",boardId);
    //model.addAttribute("pagesCount",pagesCount);
    //model.addAttribute("page",page);
    //model.addAttribute("articlesCount",articlesCount);
    //model.addAttribute("article",article);
    return "usr/article/list";
  }
  @RequestMapping("/usr/article/doDelete")
  @ResponseBody
  public String doDelete(int id) {



    Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(),id);


    if(rq.isLogined() == false){
      return rq.jsHistoryBack("로그인 후 이용하세요");
    }
    if(article.getMemberId() != rq.getLoginedMemberId()){
      return rq.jsHistoryBack("권한이 없습니다.");
    }
    if( article == null){
      return  rq.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.",id));
    }
    articleService.deleteArticle(id);

    return rq.jsReplace( Ut.f("%d번 게시물을 삭제했다.",id),"../article/list");
  }
  @RequestMapping("/usr/article/modify")
  public String  showModify(int id,Model model) {

    //아이디 구해야해서 필요함
    Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(),id);
    if( article == null){
      return rq.historyBackJsOnView(Ut.f("%d번 게시물이 존재하지 않습니다.",id));
    }
    ResultData actorCanModifyRd  = articleService.actorCanModify(rq.getLoginedMemberId(),article);
    if(actorCanModifyRd.isFail()){
      return rq.historyBackJsOnView(actorCanModifyRd.getMsg());
    }
    //model.addAttribute("article",article);
    return "/usr/article/modify";
  }
  @RequestMapping("/usr/article/doModify")
  @ResponseBody
  public String doModify( int id,String title,String body) {



    Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(),id);

    if( article == null){
      return rq.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.",id));
    }
    articleService.modifyArticle(id,title,body);
    ResultData actorCanModifyRd  = articleService.actorCanModify(rq.getLoginedMemberId(),article);
    if(actorCanModifyRd.isFail()){
      return rq.jsHistoryBack(actorCanModifyRd.getMsg());
    }
    articleService.modifyArticle(id,title,body);
    return rq.jsReplace(Ut.f("%d번 게시물이 수정 되었습니다.",id),Ut.f("../article/detail",id));
  }

}