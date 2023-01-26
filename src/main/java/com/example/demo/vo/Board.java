package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
  private int id;
  private String code;
  private  String name;
  private String regDate;
  private String updateDate;

  private boolean delStatus;
  private String delDate;
}
