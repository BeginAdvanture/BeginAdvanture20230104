package com.example.demo.service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.utill.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository){
    this.boardRepository = boardRepository;
  }

  public Board getBoardById(int id) {
    return boardRepository.getBoardById(id);
  }


}
