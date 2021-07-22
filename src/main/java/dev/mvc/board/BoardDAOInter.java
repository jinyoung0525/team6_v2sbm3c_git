package dev.mvc.board;

import java.util.HashMap;
import java.util.List;

public interface BoardDAOInter {
  /**
   * 등록
   * @param boardVO
   * @return
   */
  public int create(BoardVO boardVO);
  
  /**
   * 특정 카테고리의 등록된 글목록
   * @return
   */
  public List<BoardVO> list();
  
  /**
   * 조회
   * @param bnum
   * @return
   */
  public BoardVO read(int bnum);
}
