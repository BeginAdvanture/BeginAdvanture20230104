package com.example.demo.service;

import com.example.demo.repository.ReplyRepository;
import com.example.demo.utill.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
  @Autowired
  private ReplyRepository replyRepository;


  public ReplyService(ReplyRepository replyRepository) {
    this.replyRepository = replyRepository;
  }

  public ResultData<Integer> writeArticle(int actorId, int relId, String relTypeCode, String body) {
    replyRepository.writeArticle(actorId,relTypeCode,relId,body);

    int id = replyRepository.getLastInsertId();
    return ResultData.from("S-1", Ut.f("%d번 댓글이 생성되었습니다.",id),"id",id);
  }
  public List<Reply> getForPrintReplies(Member actor, String relTypeCode, int relId) {
    List<Reply> replies = replyRepository.getForPrintReplies(relTypeCode,relId);

    for(Reply reply: replies ){
      upDateForPrintData(actor, reply);
    }
    return replies;
  }

  private void upDateForPrintData(Member actor, Reply reply) {
    if(reply == null){
      return ;
    }
    ResultData actorCanDeleteRd =  actorCanDelete(actor,reply);
    reply.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());

    ResultData actorCanModifyRd =  actorCanModify(actor,reply);
    reply.setExtra__actorCanModify(actorCanModifyRd.isSuccess());
  }

  private ResultData actorCanModify(Member actor, Reply reply) {
    if(reply ==null){
      return ResultData.from("F-1","권한이 없습니다.");
    }
    if(reply.getMemberId() != actor.getId()){
      return ResultData.from("F-2","권한이 없습니다.");
    }
    return ResultData.from("S-1","수정 가능합니다.");
  }

  private ResultData actorCanDelete(Member actor, Reply reply) {
    if(reply ==null){
      return ResultData.from("F-1","권한이 없습니다.");
    }
    if(reply.getMemberId() != actor.getId()){
      return ResultData.from("F-2","권한이 없습니다.");
    }
    return ResultData.from("S-1","게시물 삭제가 가능합니다.");
  }
}
