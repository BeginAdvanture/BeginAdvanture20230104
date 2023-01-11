package com.example.demo.repository;

import com.example.demo.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface ArticleRepository {
  @Insert("INSERT INTO article SET regDate = NOW(),updateDate = NOW(),memberId = #{memberId}, title = #{title},`body`= #{body}")
  public void writeArticle(@Param("memberId") int memberId, @Param("title")String title, @Param("body")String body);
  @Select("SELECT * FROM article WHERE id = #{id}")
  public Article getArticle( @Param("id") int id);
  @Delete("DELETE FROM article WHERE id = ?")
  public void deleteArticle(@Param("id")int id);
  @Update("UPDATE article SET title = #{title}, `body` = #{body}, updateDate = NOW() WHERE id = #{id}")
  public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);
  @Select("SELECT = FROM article ORDER BY id DESC")
  public List<Article> getArticles();
  @Select("SELECT LAST_INSERT_ID()")
  public int getLastInsertId();
}
