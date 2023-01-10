package com.example.demo.member.controller.Service;

import com.example.demo.member.controller.Repository.MemberRepository;
import com.example.demo.member.vo.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
  private MemberRepository memberRepository;
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
    Member oldMember = getMemberByLogindId(loginId);


    if(oldMember != null){
      return -1;
    }

    memberRepository.join(loginId,  loginPw,  name, nickname, cellphoneNo, email);
    return memberRepository.getLastInsertId();

  }

  private Member getMemberByLogindId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }

  public Member getMemberById(int id) {
    return memberRepository.getMemberById(id);
  }
}
