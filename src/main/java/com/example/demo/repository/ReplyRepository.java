package com.example.demo.repository;

import com.example.demo.vo.Reply;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReplyRepository {

  @Insert("""
      INSERT INTO reply
      SET regDate = NOW(),
      updateDate = NOW(),
      memberId = #{memberId},
      relTypeCode = #{relTypeCode},
      relId = #{relId},
      body = #{body}
      """)
  public void writeReply(@Param("memberId") int memberId,
                                  @Param("relTypeCode") String relTypeCode,
                                  @Param("relId") int relId, @Param("body") String body);

  @Select("""
      SELECT LAST_INSERT_ID()
      """)
  public int getLastInsertId();
  @Select("""
      SELECT R.*
      M.nickname AS extra__writerName
      FROM reply AS R
      LEFT JOIN `member` AS M
      ON R.memberId = M.id
      WHERE R.relTypeCode = #{relTypeCode}
      AND R.relId = #{relId}
      ORDER BY R.id DESC
      """)
  List<Reply> getForPrintReplies(@Param("relTypeCode")String relTypeCode,@Param("relId") int relId);
  @Select("""
      SELECT R.*
      M.nickname AS extra__writerName
      FROM reply AS R
      LEFT JOIN `member` AS M
      ON R.memberId = M.id
      WHERE R.id = #{id}
      """)
  Reply getForPrintReply(@Param("id") int id);

  @Delete("""
      DELETE FROM reply
      WHERE id = #{id}
      """)
  void deleteReply(@Param("id") int id);

  @Update("""
      UPDATE reply
      SET updateDate
      `body` = #{body}
      WHERE id = #{id}
      """)
  void modifyReply(@Param("id") int id, @Param("body") String body);
}
