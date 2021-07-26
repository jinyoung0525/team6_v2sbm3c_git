package dev.mvc.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.notice.NoticeVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.board.BoardProc")
public class BoardProc implements BoardProcInter {

  @Autowired
  private BoardDAOInter boardDAO;
  @Override
  public int create(BoardVO boardVO) {
    int cnt = this.boardDAO.create(boardVO);
    return cnt;
  }
  
  @Override
  public List<BoardVO> list() {
    List<BoardVO> list = this.boardDAO.list();
    return list;
  }
  
  @Override
  public BoardVO read(int bnum) {
    BoardVO boardVO = this.boardDAO.read(bnum);
    
    String btitle = boardVO.getBtitle();
    String bcontent = boardVO.getBcontent();

    btitle = Tool.convertChar(btitle);  // 특수 문자 처리
    bcontent = Tool.convertChar(bcontent); 
    
    boardVO.setBtitle(btitle);
    boardVO.setBcontent(bcontent);
    
    long imagesize = boardVO.getImagesize();
    boardVO.setSize_label(Tool.unit(imagesize));
    
    return boardVO;
    
  }
  
  @Override
  public BoardVO read_update(int bnum) {
    BoardVO boardVO = this.boardDAO.read(bnum);
    return boardVO;
  }

  @Override
  public int update(BoardVO boardVO) {
    int cnt = this.boardDAO.update(boardVO);
    return cnt;
  }

  @Override
  public int delete(int bnum) {
    int cnt = this.boardDAO.delete(bnum);
    return cnt;    
  }
}
