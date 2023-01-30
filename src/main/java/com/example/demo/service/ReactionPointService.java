package com.example.demo.service;

import com.example.demo.repository.ReactionPointRepository;
import org.springframework.stereotype.Service;

@Service
public class ReactionPointService {
  private ReactionPointRepository reactionPointRepository;

  public ReactionPointService(ReactionPointRepository reactionPointRepository) {
    this.reactionPointRepository = reactionPointRepository;
  }

  public boolean actorCanMakeReactionPoint(int actorId, String relTypeCode,int relId) {
    if(actorId ==0){
      return false;
    }

    return  reactionPointRepository.getSumReactionPointByMemberId(relTypeCode,relId,actorId) == 0;
  }
}
