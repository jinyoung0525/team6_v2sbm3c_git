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
}
