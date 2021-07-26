package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.board.BoardVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.notice.NoticeProc")
public class NoticeProc implements NoticeProcInter {
  @Autowired
  private NoticeDAOInter noticeDAO;

  @Override
  public int create(NoticeVO noticeVO) {
    int cnt=this.noticeDAO.create(noticeVO);
    return cnt;
  }

  @Override
  public List<NoticeVO> list() {
    List<NoticeVO> list = this.noticeDAO.list();
    return list;
  }

  @Override
  public NoticeVO read(int nnum) {
    NoticeVO noticeVO = this.noticeDAO.read(nnum);
    
    String ntitle = noticeVO.getNtitle();
    String ncontent = noticeVO.getNcontent();
    
    ntitle = Tool.convertChar(ntitle);  // 특수 문자 처리
    ncontent = Tool.convertChar(ncontent); 
    
    noticeVO.setNtitle(ntitle);
    noticeVO.setNcontent(ncontent);  
    
    long nsize = noticeVO.getNsize();
    noticeVO.setSize_label(Tool.unit(nsize));
    
    return noticeVO;
  }

  @Override
  public NoticeVO read_update(int nnum) {
    NoticeVO noticeVO = this.noticeDAO.read(nnum);
    return noticeVO;
  }

  @Override
  public int update(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.update(noticeVO);
    return cnt;
  }

  @Override
  public int delete(int nnum) {
    int cnt = this.noticeDAO.delete(nnum);
    return cnt;
  }

}
