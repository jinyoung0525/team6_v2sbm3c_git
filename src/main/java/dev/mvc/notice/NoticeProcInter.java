package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeProcInter {
  /**
   * 등록
   * @param noticeVO
   * @return
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * 목록
   * @return
   */
  public List<NoticeVO> list();
  
  /**
   * 조회
   * @param nnum
   * @return
   */
  public NoticeVO read(int nnum);
  
  /**
   * 수정용 조회
   * @param nnum
   * @return
   */
  public NoticeVO read_update(int nnum);
  
  /**
   * 수정
   * @return
   */
  public int update(NoticeVO noticeVO);
  
  /**
   * 삭제
   * @param nnum
   * @return
   */
  public int delete(int nnum);
}
