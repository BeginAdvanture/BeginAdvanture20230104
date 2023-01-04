package com.example.demo.artiicle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class UsrHomeController {
  private int count = 0;
  public UsrHomeController(){
    count = 0;
  }
  @RequestMapping("/usr/home/main")
  @ResponseBody
  public int showMain(){

    return ++count;
  }
}
