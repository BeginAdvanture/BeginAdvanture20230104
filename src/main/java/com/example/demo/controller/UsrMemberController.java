package com.example.demo.controller;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import com.example.demo.utill.Ut;
import com.example.demo.vo.ResultData;
import jakarta.servlet.http.HttpSession;
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

    ResultData<Integer> joinRd = memberService.join( loginId,  loginPw,  name, nickname, cellphoneNo, email);
    if (joinRd.isFail()){
      return joinRd;
    }

    Member member = memberService.getMemberById(joinRd.getData1());
    return ResultData.newData(joinRd,member);
  }
/////////////////////////
  @RequestMapping("/usr/member/doLogin")
  @ResponseBody
  public ResultData doLogin(HttpSession httpSession, String loginId, String loginPw)
  {
    boolean isLogined = false;

    if(httpSession.getAttribute("loginMemberId")!=null){
      isLogined = true;
    }
    if(isLogined){
      return ResultData.from("F-5","이미 로그인 되었습니다.");
    }
      if (Ut.empty(loginId)){
      return ResultData.from("F-1","loginId를 입력 해주세요.");
    }
    if (Ut.empty(loginPw)){
      return ResultData.from("F-2","loginPw 입력 해주세요.");
    }
    Member member = memberService.getMemberLoginId(loginId);
    if(member == null){
      return ResultData.from("F-3","존재하지 않는 LoginID 입니다");
    }
    if(member.getLoginPw().equals(loginPw) == false){
      return ResultData.from("F-4","비밀번호가 일치하지 않습니다");
    }
    httpSession.setAttribute("loginMemberID",member.getId());

    return ResultData.from("s-1",Ut.f("%s님 환영합니다",member.getNickname()));
  }
}
