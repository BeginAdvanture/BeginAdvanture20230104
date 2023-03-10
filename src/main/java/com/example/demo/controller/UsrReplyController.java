package com.example.demo.controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.service.ArticleService;
import com.example.demo.service.ReactionPointService;
import com.example.demo.service.ReplyService;
import com.example.demo.utill.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrReplyController {
  private ReplyService replyService;
  private Rq rq;
  private ArticleService articleService;

  public UsrReplyController(ReplyService replyService, ArticleService articleService,Rq rq) {
    this.replyService = replyService;
    this.rq = rq;
    this.articleService = articleService;
  }

  @RequestMapping("/usr/reply/doWrite")
  @ResponseBody
  public String doWrite(String relTypeCode,int relId, String body,String replaceUri) {


    if(Ut.empty(relTypeCode)){
      return rq.jsHistoryBack("relTypeCode 입력해주세요");
    }
    if(Ut.empty(relId)){
      return rq.jsHistoryBack("relId 입력해주세요");
    }


    ResultData<Integer> writeArticleRd = replyService.writeReply(rq.getLoginedMemberId(),relId,relTypeCode,body);
    int id = writeArticleRd.getData1();

    switch (relTypeCode){
      case "article":
        replaceUri = Ut.f("../article/detail?id=%d",relId);
        break;
    }
    return rq.jsReplace(writeArticleRd.getMsg(),replaceUri);
  }
  @RequestMapping("/usr/reply/modify")
  public String modify(int id, String replaceUri, Model model) {

    if(Ut.empty(id)){
      return rq.jsHistoryBack("id 입력해주세요");
    }

    Reply reply = replyService.getForPrintReply(rq.getLoginedMember(),id);

    if(reply==null){
      return rq.historyBackJsOnView(Ut.f("%d번 게시물은 존재하지 않습니다.",id));
    }
    if(reply.isExtra__actorCanModify() == false){
      return rq.historyBackJsOnView(Ut.f("%d번 수정할 권한이 없습니다.",id));
    }
    String  relDataTitle = null;
    switch (reply.getRelTypeCode()){
      case "article":
        Article article = articleService.getArticle(reply.getRelId());
        relDataTitle = article.getTitle();
    }
    //model.addAttribute("relDataTitle",relDataTitle);
    //model.addAttribute("reply",reply);
    return "/usr/reply/modify";
  }

  @RequestMapping("/usr/reply/doDelete")
  @ResponseBody
  public String doDelete(int id,String replaceUri) {

    if(Ut.empty(id)){
      return rq.jsHistoryBack("id 입력해주세요");
    }

    Reply reply = replyService.getForPrintReply(rq.getLoginedMember(),id);

    if(reply==null){
      return rq.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다.",id));
    }
    if(reply.isExtra__actorCanDelete() == false){
      return rq.jsHistoryBack(Ut.f("%d번 게시물은 삭제 할 권한이 없습니다.",id));
    }

    ResultData deleteReplyRd = replyService.deleteReply(id);


    switch (reply.getRelTypeCode()){
      case "article":
        replaceUri = Ut.f("../article/detail?id=%d",reply.getRelId());
        break;
    }
    return rq.jsReplace(deleteReplyRd.getMsg(),replaceUri);
  }

  @RequestMapping("/usr/reply/doModify")
  @ResponseBody
  public String doModify(int id,String body,String replaceUri) {

    if(Ut.empty(id)){
      return rq.jsHistoryBack("id 입력해주세요");
    }

    Reply reply = replyService.getForPrintReply(rq.getLoginedMember(),id);

    if(reply==null){
      return rq.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다.",id));
    }
    if(reply.isExtra__actorCanDelete() == false){
      return rq.jsHistoryBack(Ut.f("%d번 게시물은 수정 할 권한이 없습니다.",id));
    }


    if(Ut.empty(body)){
      return rq.jsHistoryBack("body(을)를 입력해주세요");
    }


    ResultData modifyReplyRd = replyService.modifyReply(id,body);


    switch (reply.getRelTypeCode()){
      case "article":
        replaceUri = Ut.f("../article/detail?id=%d",reply.getRelId());
        break;
    }
    return rq.jsReplace(modifyReplyRd.getMsg(),replaceUri);
  }
}

