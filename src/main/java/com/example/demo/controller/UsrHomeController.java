package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class UsrHomeController {
  @RequestMapping("/usr/home/main")
  public String showMain(){
    return "/usr/home/main";
  }
  @RequestMapping("/")
  public String showRoot(){
    return "redirect:/usr/home/main";
  }
}
