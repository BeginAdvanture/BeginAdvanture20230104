package com.example.demo.service;

import com.example.demo.repository.ReplyRepository;
import com.example.demo.utill.Ut;
import com.example.demo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
