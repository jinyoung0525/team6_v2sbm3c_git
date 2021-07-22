package dev.mvc.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class BoardCont {
  
  @Autowired
  @Qualifier("dev.mvc.board.BoardProc")
  private BoardProcInter boardProc;
  
  public BoardCont() {
    System.out.println("-> BoardCont created.");
  }

  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/board/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
   * 등록폼
   * 사전 준비된 레코드: X
   * http://localhost:9091/board/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/board/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();

    mav.setViewName("/board/create"); // /webapp/WEB-INF/views/board/create.jsp
    // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
    // mav.addObject("content", content);

    return mav; // forward
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
    String file1 = "";          // 원본 파일명 image
    String file1saved = "";  // 저장된 파일명, image

    // 기준 경로 확인
    String user_dir = System.getProperty("user.dir");
    System.out.println("--> User dir: " + user_dir);
    //  --> User dir: F:\ai8\ws_frame\resort_v1sbm3a
    
    // 파일 접근임으로 절대 경로 지정, static 지정
    // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/board/storage
    String upDir =  user_dir + "/src/main/resources/static/board/storage/"; // 절대 경로
    
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택">
    MultipartFile mf = boardVO.getImage1MF();
    
    file1 = mf.getOriginalFilename(); // 원본 파일명
    long size1 = mf.getSize();  // 파일 크기
    
    if (size1 > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1saved = Upload.saveFileSpring(mf, upDir); 

    }    
    
    boardVO.setImage(file1);
    boardVO.setImagesaved(file1saved);
    boardVO.setSize(size1);
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
   *  목록 http://localhost:9091/board/list.do
   * 
   * @return
   */
   @RequestMapping(value = "/board/list.do", method = RequestMethod.GET)
    public ModelAndView list() { 
      ModelAndView mav = new  ModelAndView(); // /webapp/board/list.jsp //
      mav.setViewName("/board/list.do");
      
      List<BoardVO> list = this.boardProc.list();
      mav.addObject("list", list);
      
      return mav; // forward 
    }
  
// http://localhost:9091/board/read.do?bnum=1
  /**
   * 조회
   * @return
   */
  @RequestMapping(value="/board/read.do", method=RequestMethod.GET )
  public ModelAndView read(int bnum) {
    ModelAndView mav = new ModelAndView();

    BoardVO boardVO = this.boardProc.read(bnum);
    mav.addObject("boardVO", boardVO); // request.setAttribute("boardVO", boardVO);
    
    mav.setViewName("/board/read"); // /WEB-INF/views/board/read.jsp
        
    return mav;
  }
   
}
