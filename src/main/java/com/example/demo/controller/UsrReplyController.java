package com.example.demo.controller;

import com.example.demo.service.ReactionPointService;
import com.example.demo.service.ReplyService;
import com.example.demo.utill.Ut;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrReplyController {
  private ReplyService replyService;
  private Rq rq;

  public UsrReplyController(ReplyService replyService, Rq rq) {
    this.replyService = replyService;
    this.rq = rq;
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


    ResultData<Integer> writeArticleRd = replyService.writeArticle(rq.getLoginedMemberId(),relId,relTypeCode,body);
    int id = writeArticleRd.getData1();

    switch (relTypeCode){
      case "article":
        replaceUri = Ut.f("../article/detail?id=%d",relId);
        break;
    }
    return rq.jsReplace(writeArticleRd.getMsg(),replaceUri);
  }

}

