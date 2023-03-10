package com.example.demo.controller;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import com.example.demo.utill.Ut;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UsrMemberController {
  private MemberService memberService;
  private  Rq rq;

  public UsrMemberController(MemberService memberService, Rq rq) {
    this.memberService = memberService;
    this.rq = rq;
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
    return ResultData.newData(joinRd,"member",member);
  }
///////////////////////// LogID
  @RequestMapping("/usr/member/login")
  public String showLogin(HttpSession httpSession){
    return "/usr/member/login";
  }


  @RequestMapping("/usr/member/doLogin")
  @ResponseBody
  public String doLogin(String loginId, String loginPw)
  {
      if (Ut.empty(loginId)){
      return rq.jsHistoryBack("loginId를 입력 해주세요.");
    }
    if (Ut.empty(loginPw)){
      return rq.jsHistoryBack("loginPw 입력 해주세요.");
    }
    Member member = memberService.getMemberLoginId(loginId);
    if(member == null){
      return rq.jsHistoryBack("존재하지 않는 LoginID 입니다");

    }
    if(member.getLoginPw().equals(loginPw) == false){
      return rq.jsHistoryBack("비밀번호가 일치하지 않습니다");
    }
    rq.login(member);

    return rq.jsReplace(Ut.f("%s님 환영합니다",member.getNickname()),"/");
  }
  ///////로그아웃
  @RequestMapping("/usr/member/doLogout")
  @ResponseBody
  public String doLogout()
  {
    rq.logout();

    return rq.jsReplace(Ut.f("로그아웃 되었습니다"),"/");
  }

  @RequestMapping("/usr/member/myPage")
  public String showMyPage(HttpSession httpSession){
    return "/usr/member/myPage";
  }
  @RequestMapping("/usr/member/checkPassword")
  public String showCheckPassword(HttpSession httpSession){
    return "/usr/member/checkPassword";
  }
  @RequestMapping("/usr/member/doCheckPassword")
  @ResponseBody
  public String doCheckPassword(String loginPw,String replaceUri){
    if (Ut.empty(loginPw)){
      return rq.jsHistoryBack("loginPw를 입력 해주세요.");
    }
    if (rq.getLoginedMember().getLoginPw().equals(loginPw)==false){
      return rq.jsHistoryBack("비밀번호가 일치하지 않습니다.");
    }
    return rq.jsReplace("",replaceUri);
  }
}
