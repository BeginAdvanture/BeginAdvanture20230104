package com.example.demo.home.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class UsrHomeController {


  @RequestMapping("/usr/home/getMap")
  @ResponseBody
  public Map<String, Object> getMap(){
    Map<String, Object> map = new HashMap<>();
    map.put("철수나이", 22);
    map.put("영희나이",21);
    return map;
  }
  @RequestMapping("/usr/home/getList")
  @ResponseBody
  public List<String> getList(){
    List<String> list = new ArrayList<>();
    list.add("철수");
    list.add("영희");
    return list;
  }


}
