package com.eomcs.lms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class MariaDBBoardDao implements BoardDao {
  //프로그램의 유연성을 위해서 그냥 Interface로 설정해준다(List)
public List<Board> findAll() throws Exception{
  DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
  try {    
    
    Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study","1111");
    PreparedStatement stmt = con.prepareStatement("select bno, cont, cdt, view from board");
    ResultSet rs = stmt.executeQuery();
    
    List<Board> list = new ArrayList<>();
    //DBMS에서 한개의 레코드를 가져온다.
    while (rs.next()) {
      Board board = new Board();
      board.setNo(rs.getInt("bno"));
      board.setContents(rs.getString("cont"));
      board.setCreatedDate(rs.getDate("cdt"));
      board.setViewCount(rs.getInt("view"));
      list.add(board);
    }
    
    return list;
    
  } catch (Exception e){
    throw e;
  }
}
public Board findByNo(int no) throws Exception{

  DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
  try ( Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study","1111");
      PreparedStatement stmt = con.prepareStatement("select bno, cont, cdt, view, mno, lno"
          + " from board"
          + " where bno=?"); ){
    stmt.setInt(1, no);
    try(ResultSet rs = stmt.executeQuery()){  
      if (rs.next()) {
      Board board = new Board();
      board.setNo(rs.getInt("bno"));
      board.setContents(rs.getString("cont"));
      board.setCreatedDate(rs.getDate("cdt"));
      board.setViewCount(rs.getInt("view"));
      board.setWriterNo(rs.getInt("mno"));
      board.setLessonNo(rs.getInt("lno"));
      return board;
    }else {
      return null;
    }}
  } catch (Exception e){
    throw e;
  } 

}
public int insert(Board board) throws Exception{
  DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
  try (
      Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study","1111");
      PreparedStatement stmt = con.prepareStatement("insert into board(cont,mno,lno)"
            + "values(?,?,?)");
      ){
    stmt.setString(1, board.getContents());
    stmt.setInt(2, board.getWriterNo());
    stmt.setInt(3, board.getLessonNo());
      return stmt.executeUpdate();
  }
}
public int update(Board board) throws Exception {
  
  PreparedStatement stmt=null;
  Connection con=null;
  try {
    DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
    con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study","1111");
    
    stmt = con.prepareStatement("update board set cont=? where bno=?");
    stmt.setString(1, board.getContents());
    stmt.setInt(2, board.getNo());
    return stmt.executeUpdate();

  } finally {
    try {stmt.close();} catch (Exception e) {}
    try {con.close();} catch (Exception e) {}
  }
}
public int delete(int no) throws Exception{
  Connection con=null;
  PreparedStatement stmt=null;
  try {
    DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
    con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study","1111");
    stmt = con.prepareStatement("delete from board where bno=?");
    stmt.setInt(1, no);
    return stmt.executeUpdate();
    //retrun 값은 삭제된 값
  } finally {

    try {stmt.close();} catch (Exception e) {}
    try {con.close();} catch (Exception e) {}
  }
}
}
