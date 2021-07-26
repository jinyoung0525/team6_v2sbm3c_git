package dev.mvc.board;

import java.util.HashMap;
import java.util.List;

import dev.mvc.notice.NoticeVO;

public interface BoardDAOInter {
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
   * 수정
   * @param boardVO
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
