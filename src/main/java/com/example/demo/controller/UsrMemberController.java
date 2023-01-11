package com.example.demo.controller;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import com.example.demo.utill.Ut;
import com.example.demo.vo.ResultData;
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
  public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email)
  {

    if (Ut.empty(loginId)){
      return ResultData.from("F-1","loginId를 입력 해주세요.");
    }
    if (Ut.empty(loginPw)){
      return ResultData.from("F-2","loginPw 입력 해주세요.");
    }
    if (Ut.empty(name)){
      return ResultData.from("F-3","name 입력 해주세요.");
    }
    if (Ut.empty(nickname)){
      return ResultData.from("F-4","nickname 입력 해주세요.");
    }
    if (Ut.empty(cellphoneNo)){
      return ResultData.from("F-5","cellphoneNo 입력 해주세요.");
    }
    if (Ut.empty(email)){
      return ResultData.from("F-6","email 입력 해주세요.");
    }

    ResultData joinRd = memberService.join( loginId,  loginPw,  name, nickname, cellphoneNo, email);
    if (joinRd.isFail()){
      return joinRd;
    }

    Member member = memberService.getMemberById((int)joinRd.getData1());
    return ResultData.newData(joinRd,member);
  }
}
