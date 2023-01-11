package com.example.demo.service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.utill.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
  private MemberRepository memberRepository;
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
   //로그인 아이디 중복 체크
    Member oldMember = getMemberLoginId(loginId);

    if(oldMember != null){
      return ResultData.from("F-7", Ut.f("해당 아이디 (%s)는 이미 사용중입니다"));
    }
    //이름 + 이메일 중복체크
    oldMember = getMemberByNameAndEmail(name, email);
    if(oldMember != null){
      return ResultData.from("F-8", Ut.f("해당 이름 (%s)과 이메일 (%s)는 이미 사용중입니다"));
    }
    memberRepository.join(loginId,  loginPw,  name, nickname, cellphoneNo, email);
    int id =  memberRepository.getLastInsertId();
    return ResultData.from("S-1","회원가입 완료");
  }

  public Member getMemberByNameAndEmail(String name, String email) {
    return memberRepository.getMemberByNameAndEmail(name,email);
  }

  public Member getMemberLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }

  public Member getMemberById(int id) {
    return memberRepository.getMemberById(id);
  }
}
