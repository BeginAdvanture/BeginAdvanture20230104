package com.example.demo.article.repository;

import com.example.demo.article.vo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper

public interface ArticleRepository {
  @Insert("INSERT INTO article SET regDate = NOW(),updadDate = NOW(), title = #{title},`body`= #{body}")
  public void writeArticle(String title, String body);
  @Select("SELECT * FROM article WHERE id = #{id}")
  public Article getArticle(int id);
  @Delete("DELETE FROM article WHERE id = ?")
  public void deleteArticle(int id);
  @Update("UPDATE artilcec SET title = #{title}, `body` = #{body}, updateDate = NOW() WHERE id = #{id}")
  public void modifyArticle(int id, String title, String body);
  @Select("SELECT = FROM article ORDER BY id DESC")
  public List<Article> getArticles();
  @Select("SELECT LAST_INSERT_ID()")
  public int getLastInsertId();
}
