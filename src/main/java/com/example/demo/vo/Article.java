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
  private String extra__writerName;

  private boolean extra__actorCanDelete;
  private boolean extra__actorCanModify;

  private int extra_sumReactionPoint;
  private int extra_goodReactionPoint;
  private int extra_bedReactionPoint;
  private String hitCount;
  private String extra__reactionPoint;

  public String getRegDateForPrint(){
    return regDate.substring(2,16);
  }
  public String getUpdateDateForPrint(){
    return updateDate.substring(2,16);
  }


}
