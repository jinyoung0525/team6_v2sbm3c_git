package dev.mvc.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.notice.NoticeVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class BoardCont {
  @Autowired
  @Qualifier("dev.mvc.board.BoardProc")
  private BoardProcInter boardProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  
  public BoardCont() {
    System.out.println("-> BoardCont created.");
  }
  
  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/board/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
   * 등록폼
   * http://localhost:9091/board/create.do 
   * 
   * @return
   */
  @RequestMapping(value="/board/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/board/create");
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9090/board/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/board/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, BoardVO boardVO) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String image = "";          // 원본 파일명 image
    String imagesaved = "";  // 저장된 파일명, image

    // 기준 경로 확인
    String user_dir = System.getProperty("user.dir");
    System.out.println("--> User dir: " + user_dir);
    //  --> User dir: F:\ai8\ws_frame\team6_v2sbm3c_git
    
    // 파일 접근임으로 절대 경로 지정, static 지정
    // 완성된 경로 F:/ai8/ws_frame/team6_v2sbm3c_git/src/main/resources/static/board/storage
    String upDir =  user_dir + "/src/main/resources/static/board/storage/"; // 절대 경로
    
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택">
    MultipartFile mf = boardVO.getImage1MF();
    
    image = mf.getOriginalFilename(); // 원본 파일명
    long imagesize = mf.getSize();  // 파일 크기
    
    if (imagesize > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      imagesaved = Upload.saveFileSpring(mf, upDir); 

    }    
    
    boardVO.setImage(image);
    boardVO.setImagesaved(imagesaved);
    boardVO.setImagesize(imagesize);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.boardProc.create(boardVO); 
    
    // -------------------------------------------------------------------
    // PK의 return
    // -------------------------------------------------------------------
    // System.out.println("--> bnum: " + boardVO.getBnum());
    // mav.addObject("bnum", boardVO.getBnum()); // redirect parameter 적용
    // -------------------------------------------------------------------

    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

    // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
    mav.addObject("url", "/board/create_msg"); // create_msg.jsp, redirect parameter 적용

    mav.setViewName("redirect:/board/msg.do"); 
    
    return mav; // forward
  }
  
  /**
   * 목록
   * http://localhost:9090/board/list.do
   * 
   * @return
   */
   @RequestMapping(value="/board/list.do", method=RequestMethod.GET)
   public ModelAndView list() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("board/list");
     
     List<BoardVO> list = this.boardProc.list();
     mav.addObject("list", list);
     
     return mav;
   }
  
// http://localhost:9091/board/read.do
  /**
   * 조회
   * @return
   */
   @RequestMapping(value="/board/read.do", method=RequestMethod.GET)
   public ModelAndView read(int bnum, HttpSession session) {
     ModelAndView mav = new ModelAndView();
     
     if (this.memberProc.isMember(session)) {
       BoardVO boardVO = this.boardProc.read(bnum);
       mav.addObject("boardVO", boardVO);
       
       mav.setViewName("/board/read");
     } else {
       mav.addObject("url", "login_need"); // login_need.jsp, redirect parameter 적용
       
       mav.setViewName("redirect:/member/msg.do");
     }
     return mav;
   }
  
   /**
    * 수정 폼
    * http://localhost:9091/board/update.do?bnum=3
    * 
    * @return
    */
   @RequestMapping(value = "/board/update.do", method = RequestMethod.GET)
   public ModelAndView update(int bnum) {
     ModelAndView mav = new ModelAndView();
     
     BoardVO boardVO = this.boardProc.read_update(bnum);
     
     mav.addObject("boardVO", boardVO);
     
     mav.setViewName("/board/update"); // /WEB-INF/views/board/update.jsp
     
     return mav; // forward
   }

   /**
    * 수정 처리
    * http://localhost:9091/board/update.do?bnum=3
    * 
    * @return
    */
   @RequestMapping(value = "/board/update.do", method = RequestMethod.POST)
   public ModelAndView update(HttpServletRequest request, BoardVO boardVO) {
     ModelAndView mav = new ModelAndView();
     
  // -------------------------------------------------------------------
     // 파일 삭제 코드 시작
     // -------------------------------------------------------------------
     // 삭제할 파일 정보를 읽어옴.
     BoardVO vo = boardProc.read(boardVO.getBnum());
     
     String imagesaved = vo.getImagesaved();
     long imagesize = 0;
     boolean sw = false;
     
     // 완성된 경로 F:/ai8/ws_frame/team6_v2sbm3c_git/src/main/resources/static/notice/storage
     String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/notice/storage/"; // 절대 경로

     sw = Tool.deleteFile(upDir, imagesaved);  // Folder에서 1건의 파일 삭제
     // System.out.println("sw: " + sw);
     // -------------------------------------------------------------------
     // 파일 삭제 종료
     // -------------------------------------------------------------------
     // -------------------------------------------------------------------
     // 파일 전송 코드 시작
     // -------------------------------------------------------------------
     String image = "";          // 원본 파일명 image

     // 완성된 경로 F:/ai8/ws_frame/team6_v2sbm3c_git/src/main/resources/static/board/storage
     // String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/board/storage/"; // 절대 경로
     
     // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
     // <input type='file' class="form-control" name='nimage1MF' id='nimage1MF' 
     //           value='' placeholder="파일 선택">
     MultipartFile mf = boardVO.getImage1MF();
     
     image = mf.getOriginalFilename(); // 원본 파일명
     imagesize = mf.getSize();  // 파일 크기
     

     if (imagesize > 0) { // 파일 크기 체크
       // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
       imagesaved = Upload.saveFileSpring(mf, upDir); 
       
     }    
     
     boardVO.setImage(image);
     boardVO.setImagesaved(imagesaved);
     boardVO.setImagesize(imagesize);
     // -------------------------------------------------------------------
     // 파일 전송 코드 종료
     // -------------------------------------------------------------------
     
     
     int cnt = this.boardProc.update(boardVO); // 수정 처리
     mav.addObject("bnum", boardVO.getBnum());
     mav.setViewName("redirect:/board/read.do"); 

     return mav; // forward
   }
  
   /**
    * 삭제 폼
    * @param bnum
    * @return
    */
   @RequestMapping(value="/board/delete.do", method=RequestMethod.GET )
   public ModelAndView delete(int bnum) { 
     ModelAndView mav = new  ModelAndView();
     
     // 삭제할 정보를 조회하여 확인
     BoardVO boardVO = this.boardProc.read(bnum);
     mav.addObject("boardVO", boardVO);     
     mav.setViewName("/board/delete");  // board/delete.jsp
     
     return mav; 
   }
   
   /**
    * 삭제 처리 http://localhost:9091/board/delete.do
    * 
    * @return
    */
   @RequestMapping(value = "/board/delete.do", method = RequestMethod.POST)
   public ModelAndView delete(HttpServletRequest request, int bnum) {
     ModelAndView mav = new ModelAndView();
     
  // 삭제할 파일 정보를 읽어옴.
     BoardVO vo = boardProc.read(bnum);
     
     String imagesaved = vo.getImagesaved();
     long imagesize = 0;
     boolean sw = false;
     
     // 완성된 경로 F:/ai8/ws_frame/team6_v2sbm3c_git/src/main/resources/static/notice/storage
     String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/notice/storage/"; // 절대 경로

     sw = Tool.deleteFile(upDir, imagesaved);  // Folder에서 1건의 파일 삭제
     // System.out.println("sw: " + sw);
     // -------------------------------------------------------------------
     // 파일 삭제 종료
     // -------------------------------------------------------------------
     
     int cnt = this.boardProc.delete(bnum); // DBMS 삭제
     
     mav.setViewName("redirect:/board/list.do"); 

     return mav; // forward
   }
   
  
}
