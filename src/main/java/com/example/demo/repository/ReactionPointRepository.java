package com.example.demo.repository;

import org.apache.ibatis.annotations.*;

@Mapper
public interface ReactionPointRepository {
  @Select("""
      <script>
      SELECT IFNULL(SUM(RP.point), 0) AS s
      FROM reactionPoint AS RP
      WHERE RP.relTypeCode = #{relTypeCode}
      AND RP.relId = #{relId}
      AND RP.memberId = #{memberId}
      </script>
      """)
  public int getSumReactionPointByMemberId( @Param("relTypeCode") String relTypeCode, @Param("relId") int relId, @Param("actorId") int actorId);
  @Insert("""
      INSERT INTO reactionPoint
      SET regDate = NOW(),
      updateDate = NOW(),
      WHERE RP.relTypeCode = #{relTypeCode},
      relId = #{relId},
      memberId = #{memberId},
      `point` = 1
      """)
  public void addGoodReactionPoint(@Param("memberId") int memberId,@Param("relTypeCode") String relTypeCode,@Param("relId") int relId);

  @Insert("""
      INSERT INTO reactionPoint
      SET regDate = NOW(),
      updateDate = NOW(),
      WHERE RP.relTypeCode = #{relTypeCode},
      relId = #{relId},
      memberId = #{memberId},
      `point` = -1
      """)
  public void addBadReactionPoint(@Param("memberId") int memberId,@Param("relTypeCode") String relTypeCode,@Param("relId") int relId);

  @Delete("""
      DELETE FROM reactionPoint
      WHERE relTypeCode = #{relTypeCode}
      AND relId = #{relId}
      AND memberId = #{memberId}
      """)
  public void deleteReactionPoint(@Param("memberId") int actorId,@Param("relTypeCode") String relTypeCode,@Param("relId") int relId);
}
