package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  private int id;
  private String title;
  private  String body;
  private int memberId;
  private String regDate;
  private String updateDate;
  private String hitCount;
  private String extra__reactionPoint;

  private int goodReactionPoint;
  private int badReactionPoint;


  private String extra__writerName;
  private boolean extra__actorCanDelete;
  private boolean extra__actorCanModify;

  public String getRegDateForPrint(){
    return regDate.substring(2,16);
  }
  public String getUpdateDateForPrint(){
    return updateDate.substring(2,16);
  }


}
