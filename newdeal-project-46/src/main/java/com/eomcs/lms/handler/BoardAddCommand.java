package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardAddCommand implements Command {
  
  Scanner keyboard;
  BoardDao boardDao;
  public BoardAddCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard=keyboard;
    this.boardDao=boardDao;
  }
  
  public void execute() {
    try {
      Board board = new Board();
      System.out.print("내용? ");
      board.setContents(keyboard.nextLine());
      
      System.out.print("작성자 번호? ");
      board.setWriterNo(Integer.parseInt(keyboard.nextLine()));
     
      System.out.print("수업번호 ? ");
      board.setLessonNo(Integer.parseInt(keyboard.nextLine()));
      
      boardDao.insert(board);
      System.out.println("입력했습니다!!");
      //DBMS에서 한개의 레코드를 가져온다.

    } catch (Exception e){
      e.printStackTrace();
    }    

    }
  }




