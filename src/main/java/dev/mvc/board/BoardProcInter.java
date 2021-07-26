package dev.mvc.board;

import java.util.HashMap;
import java.util.List;

public interface BoardProcInter {
  /**
   * 등록
   * @param BoardVO
   * @return
   */
  public int create(BoardVO boardVO);
  
  /**
   * 목록
   * @return
   */
  public List<BoardVO> list();

  /** 
   * 조회
   * @param bnum
   * @return
   */
  public BoardVO read(int bnum);
  
  /**
   * 수정용 조회
   * @param bnum
   * @return
   */
  public BoardVO read_update(int bnum);
  
  /**
   * 수정
   * @return
   */
  public int update(BoardVO boardVO);
  

  /**
   * 삭제
   * @param bnum
   * @return
   */
  public int delete(int bnum);
}
