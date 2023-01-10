package com.example.demo.member.controller.UsrMember;

import com.example.demo.article.vo.Article;
import com.example.demo.member.controller.Service.MemberService;
import com.example.demo.member.vo.Member;
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
    if (loginId == null){
      return "loginId를 입력 해주세요.";
    }

    if (loginId == null){
      return "loginId를 입력 해주세요.";
    }
    if (loginPw == null){
      return "loginPw 입력 해주세요.";
    }
    if (name == null){
      return "name 입력 해주세요.";
    }
    if (nickname == null){
      return "nickname 입력 해주세요.";
    }
    if (cellphoneNo == null){
      return "cellphoneNo 입력 해주세요.";
    }
    if (email == null){
      return "email 입력 해주세요.";
    }
    if (id == -1){
      return "해당 아이디는 이미 사용중입니다.";
    }

    Member member = memberService.getMemberById(id);
    return member;
  }
}
