package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
  private int id;

  private  String body;

  private int memberId;

  private String regDate;

  private String updateDate;

  private String relTypeCode;
  private int relId;

  private String hitCount;
  private String extra__reactionPoint;

  private int goodReactionPoint;
  private int badReactionPoint;


  private String extra__writerName;
  private boolean extra__actorCanDelete;
  private boolean extra__actorCanModify;

  public String getForPrintType1RegDate(){
    return regDate.substring(2,16);
  }
  public String getForPrintType1UpdateDate(){
    return updateDate.substring(2,16);
  }
  public String getForPrintType2RegDate(){
    return regDate.substring(2,16);
  }
  public String getForPrintType2UpdateDate(){
    return updateDate.substring(2,16);
  }

  public String getForPrintBody(){
    return body.replaceAll("\n","<br>");
  }


}
