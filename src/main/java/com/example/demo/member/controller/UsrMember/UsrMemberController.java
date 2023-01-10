package com.example.demo.member.controller.UsrMember;

import com.example.demo.article.vo.Article;
import com.example.demo.member.controller.Service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UsrMemberController {

  public UsrMemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  private MemberService memberService;

  @RequestMapping("/usr/member/doJoin")
  @ResponseBody
  public String doJoin(String loginId, String loginPw, String name,String nickname
  ,String cellphoneNo,String email) {
    memberService.join( loginId,  loginPw,  name, nickname
        , cellphoneNo, email);
    return "안녕";
  }
}
