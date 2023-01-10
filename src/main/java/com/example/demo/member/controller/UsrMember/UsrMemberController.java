package com.example.demo.member.controller.UsrMember;

import com.example.demo.article.vo.Article;
import com.example.demo.member.controller.Service.MemberService;
import com.example.demo.member.vo.Member;
import com.example.demo.utill.Ut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UsrMemberController {
  private MemberService memberService;
  public UsrMemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/usr/member/doJoin")
  @ResponseBody
  public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email)
  {
    int id = memberService.join( loginId,  loginPw,  name, nickname, cellphoneNo, email);
    if (Ut.empty(loginId)){
      return "loginId를 입력 해주세요.";
    }
    if (Ut.empty(loginPw)){
      return "loginPw 입력 해주세요.";
    }
    if (Ut.empty(name)){
      return "name 입력 해주세요.";
    }
    if (Ut.empty(nickname)){
      return "nickname 입력 해주세요.";
    }
    if (Ut.empty(cellphoneNo)){
      return "cellphoneNo 입력 해주세요.";
    }
    if (Ut.empty(email)){
      return "email 입력 해주세요.";
    }
    if (id == -1){
      return Ut.f("해당 (%s)아이디는 이미 사용중입니다.",loginId);
    }
    if (id == -2){
      return Ut.f("해당 (%s)이름과 (%s) 이메일은 이미 사용중입니다.",name,email);
    }

    Member member = memberService.getMemberById(id);
    return member;
  }
}
