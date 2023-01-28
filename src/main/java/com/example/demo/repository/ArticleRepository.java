package com.example.demo.repository;

import com.example.demo.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface ArticleRepository {
  @Insert("""
    INSERT INTO article
    SET regDate = NOW(),
    updateDate = NOW(),
    boardId = #{boardId},
    memberId = #{memberId},
    title = #{title},
    `body` = #{body}
    """)
  public void writeArticle(@Param("memberId") int memberId, @Param("boardId") int boardId, @Param("title")String title, @Param("body")String body);
  @Select("""
    SELECT A.*,
    M.nickname AS extra__writerName
    FROM article AS A
    LEFT JOIN member AS M
    ON A.memberId = M.id
    WHERE 1
    AND A.id = #{id}
    """)
  public Article getForPrintArticle(@Param("id") int id);
  @Delete("""
    DELETE
    FROM article
    WherE id = #{id}
    """)
  public void deleteArticle(@Param("id")int id);
  @Update("""
    <script>
    UPDATE article
    <set>
      <if test='title !=null'>
        title = #{title},
      </if>
      <if test = 'body != null'>
        `body` = #{body},
       </if>
       updateDate = NOW();
       </set>
       WHERE id = #{id]
       </script>
    """)
  public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);
  /*
  @Select("""
    SELECT A.*,
    M.nickname AS extra__writerName
    FROM article AS A
    LEFT JOIN member AS M
    ON A.memberId = M.id
    <if test="boardId !=0">
      AND A.boardId = #{boardId}
    </if>
    ORDER BY A.id DESC
    </script>
    """)
  public List<Article> getForPrintArticles(@Param("boardId") int boardId);

   */
  @Select("SELECT LAST_INSERT_ID()")
  public int getLastInsertId();
  @Select("""
      <script>
      SELECT A.*,
      IFNULL(SUM(RP.point,0)) AS extra_sumReactionPoint,
      IFNULL(IF(SUM(RP.point &gt; 0,RP.point,0),0)) AS extra_goodReactionPoint,
      IFNULL(IF(SUM(RP.point &lt; 0,RP.point,0),0)) AS extra_bedReactionPoint
      FROM (
        SELECT A.*,
        M.nickname AS extra__writerName
        FROM article AS A
        LEFT JOIN member AS M
        ON A.memberId = M.id
        WHERE 1
        <if test="boardId !=0">
          AND A.boardId = #{boardId}
        </if>
        <if test="searchKeyword != ''">
          <choose>
            <when test="searchKeywordTypeCode == 'title' ">
              AND A.title LIKE CONCAT( '%',#{searchKeyword},'%')
            </when>
            <when test="searchKeywordTypeCode == 'body' ">
              AND A.body LIKE CONCAT( '%',#{searchKeyword},'%')
            </when>
            <otherwise>
              AND(
                  AND A.body LIKE CONCAT( '%',#{searchKeyword},'%')
                  OR
                  AND A.title LIKE CONCAT( '%',#{searchKeyword},'%')
                )
            </otherwise>
          </choose>
        </if>
        <if test="limitTake !=-1">
          LIMIT #{limitStart}, #{limitTake}
        </if>
      ) AS A
      LEFT JOIN reactionPoint AS RP\s
      ON RP.relTypeCode = 'article'
      AND A.id = RP.relId
      GROUP BY A.id;
      </script>
      """)
  public List<Article> getArticles(@Param("boardId") int boardId, @Param("limitStart") int limitStart, @Param("limitTake") int limitTake, @Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword);
  @Select("""
    <script>
    SELECT COUNT(*) AS cnt
    FROM article AS A
    WHERE 1
    <if test="boardId !=0">
      AND A.boardId = #{boardId}
    </if>
    <if test="searchKeyword != ''">
      <choose>
        <when test="searchKeywordTypeCode == 'title' ">
          AND A.title LIKE CONCAT( '%',#{searchKeyword},'%')
        </when>
        <when test="searchKeywordTypeCode == 'body' ">
          AND A.body LIKE CONCAT( '%',#{searchKeyword},'%')
        </when>
        <otherwise>
          AND(
              AND A.body LIKE CONCAT( '%',#{searchKeyword},'%')
              OR
              AND A.title LIKE CONCAT( '%',#{searchKeyword},'%')
            )
        </otherwise>
      </choose>
    </if>
    </script>
    """)
  public int getArticlesCount(@Param("boardId") int boardId, @Param("searchKeywordTypeCode") String searchKeywordTypeCode,@Param("searchKeyword")  String searchKeyword);
  @Update("""
      <script>
        UPDATE article
        SET hitCount = hitCount +1
        WHERE id = #{id}
      </script>
      """

  )
  public int increaseHitCount(@Param("id") int id);
  @Select("""
    <script>
    SELECT hitCount
    FROM article
    WHERE id = #{id}
    </script>
    """)
  public int getArticleHitCount(@Param("id") int id);
}
