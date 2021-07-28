package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.board.BoardVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.notice.NoticeVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class NoticeCont {
  
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc")
  private NoticeProcInter noticeProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  public NoticeCont() {
    System.out.println("-> NoticeCont created.");
  }
  

  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/notice/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
   * 등록폼
   * 사전 준비된 레코드: X
   * http://localhost:9091/notice/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();

    mav.setViewName("/notice/create"); // /webapp/WEB-INF/views/notice/create.jsp

    return mav; // forward
  }
  
  /**
   * 등록 처리 http://localhost:9090/notice/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String nimage = "";          // 원본 파일명 image
    String nimagesaved = "";  // 저장된 파일명, image

    // 기준 경로 확인
    String user_dir = System.getProperty("user.dir");
    System.out.println("--> User dir: " + user_dir);
    //  --> User dir: F:\ai8\ws_frame\team6_v2sbm3c_git
    
    // 파일 접근임으로 절대 경로 지정, static 지정
    // 완성된 경로 F:/ai8/ws_frame/team6_v2sbm3c_git/src/main/resources/static/notice/storage
    String upDir =  user_dir + "/src/main/resources/static/notice/storage/"; // 절대 경로
    
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='nimage1MF' id='nimage1MF' 
    //           value='' placeholder="파일 선택">
    MultipartFile mf = noticeVO.getNimage1MF();
    
    nimage = mf.getOriginalFilename(); // 원본 파일명
    long nsize = mf.getSize();  // 파일 크기
    
    if (nsize > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      nimagesaved = Upload.saveFileSpring(mf, upDir); 
      
    }    
    
    noticeVO.setNimage(nimage);
    noticeVO.setNimagesaved(nimagesaved);
    noticeVO.setNsize(nsize);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.noticeProc.create(noticeVO); 
    
    // -------------------------------------------------------------------
    // PK의 return
    // -------------------------------------------------------------------
    // System.out.println("--> nnum: " + noticeVO.getNnum());
    // mav.addObject("nnum", noticeVO.getNnum()); // redirect parameter 적용
    // -------------------------------------------------------------------
    
//    if (cnt == 1) {
//      cateProc.increaseCnt(contentsVO.getCateno()); // 글수 증가
//    }
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

    mav.addObject("url", "/notice/create_msg"); // create_msg.jsp, redirect parameter 적용

    mav.setViewName("redirect:/notice/msg.do");
    
    return mav; // forward
  }

  /**
   *  목록 http://localhost:9091/notice/list.do
   * 
   * @return
   */
   @RequestMapping(value = "/notice/list.do", method = RequestMethod.GET)
    public ModelAndView list() { 
      ModelAndView mav = new  ModelAndView(); // /webapp/notice/list.jsp //
      mav.setViewName("/notice/list");
      
      List<NoticeVO> list = this.noticeProc.list();
      mav.addObject("list", list);
      
      return mav; // forward 
    }
  
// http://localhost:9091/board/read.do?bnum=1
   /**
    * 조회
    * @return
    */
   @RequestMapping(value="/notice/read.do", method=RequestMethod.GET )
   public ModelAndView read(int nnum, HttpSession session, Authentication SecurityContextHolder) {
     ModelAndView mav = new ModelAndView();
    
     
     if (this.memberProc.isMember(session)||SecurityContextHolder!=null) {
       NoticeVO noticeVO = this.noticeProc.read(nnum);
       mav.addObject("noticeVO", noticeVO); // request.setAttribute("noticeVO", noticeVO);
       
       mav.setViewName("/notice/read"); // /WEB-INF/views/notice/read.jsp
       
     } else {
      
       mav.addObject("url", "login_need"); // login_need.jsp, redirect parameter 적용
       
       mav.setViewName("redirect:/member/msg.do");  
     
     }
         
     return mav;
   }
  
  /**
   * 수정 폼
   * http://localhost:9091/notice/update.do?nnum=3
   * 
   * @return
   */
  @RequestMapping(value = "/notice/update.do", method = RequestMethod.GET)
  public ModelAndView update(int nnum) {
    ModelAndView mav = new ModelAndView();
    
    NoticeVO noticeVO = this.noticeProc.read_update(nnum);
    
    mav.addObject("noticeVO", noticeVO);
    
    mav.setViewName("/notice/update"); // /WEB-INF/views/notice/update.jsp
    
    return mav; // forward
  }

  /**
   * 수정 처리
   * http://localhost:9091/notice/update.do?nnum=3
   * 
   * @return
   */
  @RequestMapping(value = "/notice/update.do", method = RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();
    
 // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    NoticeVO vo = noticeProc.read(noticeVO.getNnum());
    
    String nimagesaved = vo.getNimagesaved();
    long nsize = 0;
    boolean sw = false;
    
    // 완성된 경로 F:/ai8/ws_frame/team6_v2sbm3c_git/src/main/resources/static/notice/storage
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/notice/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, nimagesaved);  // Folder에서 1건의 파일 삭제
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료
    // -------------------------------------------------------------------
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String nimage = "";          // 원본 파일명 image

    // 완성된 경로 F:/ai8/ws_frame/team6_v2sbm3c_git/src/main/resources/static/notice/storage
    // String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/notice/storage/"; // 절대 경로
    
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='nimage1MF' id='nimage1MF' 
    //           value='' placeholder="파일 선택">
    MultipartFile mf = noticeVO.getNimage1MF();
    
    nimage = mf.getOriginalFilename(); // 원본 파일명
    nsize = mf.getSize();  // 파일 크기
    

    if (nsize > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      nimagesaved = Upload.saveFileSpring(mf, upDir); 
      
    }    
    
    noticeVO.setNimage(nimage);
    noticeVO.setNimagesaved(nimagesaved);
    noticeVO.setNsize(nsize);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    
    int cnt = this.noticeProc.update(noticeVO); // 수정 처리
    mav.addObject("nnum", noticeVO.getNnum());
    mav.setViewName("redirect:/notice/read.do"); 

    return mav; // forward
  }
  
  /**
   * 삭제 폼
   * @param nnum
   * @return
   */
  @RequestMapping(value="/notice/delete.do", method=RequestMethod.GET )
  public ModelAndView delete(int nnum) { 
    ModelAndView mav = new  ModelAndView();
    
    // 삭제할 정보를 조회하여 확인
    NoticeVO noticeVO = this.noticeProc.read(nnum);
    mav.addObject("noticeVO", noticeVO);     
    mav.setViewName("/notice/delete");  // notice/delete.jsp
    
    return mav; 
  }
  
  /**
   * 삭제 처리 http://localhost:9091/notice/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, int nnum) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    
    
    
    NoticeVO vo = noticeProc.read(nnum);
//    System.out.println("nnum: " + vo.getNnum());
//    System.out.println("nimage: " + vo.getNimage());

    String nimagesaved = vo.getNimagesaved();
    long nsize = 0;
    boolean sw = false;

    // 완성된 경로 F:/ai8/ws_frame/team6_v2sbm3c_git/src/main/resources/static/notice/storage
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/notice/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, nimagesaved);  // Folder에서 1건의 파일 삭제
    // System.out.println("sw: " + sw);
    
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    int cnt = this.noticeProc.delete(nnum); // DBMS 삭제
    
    mav.setViewName("redirect:/notice/list.do"); 

    return mav; // forward
  }
  
}

