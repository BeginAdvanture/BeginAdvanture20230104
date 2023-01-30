package com.example.demo.service;

import com.example.demo.repository.ReactionPointRepository;
import com.example.demo.vo.ResultData;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;

@Service
public class ReactionPointService {
  private ReactionPointRepository reactionPointRepository;
  private ArticleService articleService;
  public ReactionPointService(ReactionPointRepository reactionPointRepository, ArticleService articleService) {
    this.reactionPointRepository = reactionPointRepository;
    this.articleService = articleService;
  }

  public ResultData actorCanMakeReactionPoint(int actorId, String relTypeCode,int relId) {
    if(actorId ==0){
      return ResultData.from("F-1","로그인 후 이용하세요");
    }

    int sumReactionPointByMemberId = reactionPointRepository.getSumReactionPointByMemberId(relTypeCode,relId,actorId);
    if(sumReactionPointByMemberId != 0){
      return ResultData.from("F-2","리액션 불가능","sumReactionPointByMemberId",sumReactionPointByMemberId);
    }
    return ResultData.from("S-1","리액션 가능");
  }

  public ResultData addGoodReactionPoint(int actorId, String relTypeCode, int relId) {
    reactionPointRepository.addGoodReactionPoint(actorId,relTypeCode,relId);

    switch(relTypeCode){
    case "article":
      articleService.increaseGoodReactionPoint(relId);
      
      break;
    }

    return ResultData.from("S-1","좋아요");
  }
  public ResultData addBadReactionPoint(int actorId, String relTypeCode, int relId) {
    reactionPointRepository.addBadReactionPoint(actorId,relTypeCode,relId);

    switch(relTypeCode){
      case "article":
        articleService.increaseBadReactionPoint(relId);

        break;
    }
    return ResultData.from("S-1","싫어요");
  }

}
