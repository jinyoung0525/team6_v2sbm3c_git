package dev.mvc.artist;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
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
public class ArtistCont {
  @Autowired
  @Qualifier("dev.mvc.artist.ArtistProc")
  private ArtistProcInter artistProc;
  
  public ArtistCont() {
    System.out.println("-> ArtistCont created");
  }
  
  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/artist/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
//http://localhost:9091/artist/create.do
 /**
  * 등록 폼
  * @return
  */
 @RequestMapping(value="/artist/create.do", method=RequestMethod.GET )
 public ModelAndView create() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/artist/create"); // /WEB-INF/views/artist/create.jsp
   
   return mav; // forward
 }
 
 // http://localhost:9091/artist/create.do
 /**
  * 등록 처리
  * @param artistVO
  * @return
  */
 @RequestMapping(value="/artist/create.do", method=RequestMethod.POST )
 public ModelAndView create(HttpServletRequest request, ArtistVO artistVO) { // artistVO 자동 생성, Form -> VO
   // ArtistVO artistVO <FORM> 태그의 값으로 자동 생성됨.
   // request.setAttribute("artistVO", artistVO); 자동 실행
   
   ModelAndView mav = new ModelAndView();
   
   // -------------------------------------------------------------------
   // 파일 전송 코드 시작
   // -------------------------------------------------------------------
   String fname = "";          // 원본 파일명 image
   String fupname = "";  // 저장된 파일명, image
   String thumb = "";     // preview image

   // 기준 경로 확인
   String user_dir = System.getProperty("user.dir");
   System.out.println("--> User dir: " + user_dir);
   //  --> User dir: F:\ai8\ws_frame\resort_v1sbm3a
   
   // 파일 접근임으로 절대 경로 지정, static 지정
   // 완성된 경로 F:/ai8/ws_frame/covid/src/main/resources/static/contents/storage
   String upDir =  user_dir + "/src/main/resources/static/artist/storage/"; // 절대 경로
   
   // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
   // <input type='file' class="form-control" name='file1MF' id='file1MF' 
   //           value='' placeholder="파일 선택">
   MultipartFile mf = artistVO.getFile1MF();
   
   fname = mf.getOriginalFilename(); // 원본 파일명
   long fsize = mf.getSize();  // 파일 크기
   
   if (fsize > 0) { // 파일 크기 체크
     // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
     fupname = Upload.saveFileSpring(mf, upDir); 
     
     if (Tool.isImage(fupname)) { // 이미지인지 검사
       // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
       thumb = Tool.preview(upDir, fupname, 200, 150); 
     }
     
   }    
   
   artistVO.setFname(fname);
   artistVO.setFupname(fupname);
   artistVO.setThumb(thumb);
   artistVO.setFsize(fsize);
   // -------------------------------------------------------------------
   // 파일 전송 코드 종료
   // -------------------------------------------------------------------
   
   int cnt = this.artistProc.create(artistVO); // 등록 처리
   
   mav.addObject("cnt", cnt); // request에 저장, request.setAttribute("cnt", cnt)
   mav.addObject("url", "/artist/create_msg");
   
   mav.addObject("artistno", artistVO.getArtistno());
   mav.addObject("name", artistVO.getName());
   
   mav.setViewName("redirect:/artist/msg.do"); // /webapp/WEB-INF/views/artist/create_msg.jsp
   /*
    * // cnt = 0; // error test if (cnt == 1) {
    * mav.setViewName("redirect:/artist/list.do"); } else { mav.addObject("code",
    * "create"); // request에 저장, request.setAttribute("cnt", cnt)
    * mav.setViewName("/artist/error_msg"); // /WEB-INF/views/artist/error_msg.jsp
    * }
    */
   

   return mav; // forward
 }
 
//아티스트 일반 목록
/* 
* http://localhost:9091/artist/list_by_artistno.do
*/
@RequestMapping(value="/artist/list_by_artistno.do", method=RequestMethod.GET )
public ModelAndView list() {
  ModelAndView mav = new ModelAndView();
  
  List<ArtistVO> list = this.artistProc.list_by_artistno();
  mav.addObject("list", list); // request.setAttribute("list", list);

  mav.setViewName("/artist/list_by_artistno"); // /webapp/WEB-INF/views/artist/list_by_artistno.jsp
  return mav;
 }

/**
 * 목록 + 검색 지원
 * http://localhost:9091/artist/list_by_artistno_search.do?word=
 * @param word
 * @return
 */
  @RequestMapping(value = "/artist/list_by_artistno_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_artistno_search( @RequestParam(value="word", defaultValue="") String word) {
  
  ModelAndView mav = new ModelAndView(); 
       
  // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용 
  HashMap<String, Object> map = new HashMap<String, Object>(); 
  //map.put("newsno", newsno); // #{newsno}
  map.put("word", word); // #{word}
  
  // 검색 목록 
  List<ArtistVO> list = artistProc.list_by_artistno_search(map);
  mav.addObject("list", list);
  
  // 검색된 레코드 갯수 
  int search_count = artistProc.search_count(map);
  mav.addObject("search_count", search_count);
  
  mav.setViewName("/artist/list_by_artistno_search");   // /artist/list_by_artistno_search.jsp
  
  return mav; 
 }
  /**
   * 목록 + 검색 + 페이징 지원
   * http://localhost:9091/artist/list_by_artistno_search_paging.do?word=&now_page=1
   * 
   * @param word
   * @param now_page
   * @return
   */
  @RequestMapping(value = "/artist/list_by_artistno_search_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_artistno_search_paging( @RequestParam(value = "word", defaultValue = "") String word,
                                                                         @RequestParam(value = "now_page", defaultValue = "1") int now_page) {

    ModelAndView mav = new ModelAndView();

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    //map.put("cateno", cateno); // #{cateno}
    map.put("word", word); // #{word}
    map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용

    // 검색 목록
    List<ArtistVO> list = artistProc.list_by_artistno_search_paging(map);
    mav.addObject("list", list);

    // 검색된 레코드 갯수
    int search_count = artistProc.search_count(map);
    mav.addObject("search_count", search_count);

    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * @param list_file 목록 파일명
     * @param cateno 카테고리번호
     * @param search_count 검색(전체) 레코드수
     * @param now_page 현재 페이지
     * @param word 검색어
     * @return 페이징 생성 문자열
     */
    String paging = artistProc.pagingBox("list_by_artistno_search_paging.do", search_count, now_page, word);
    mav.addObject("paging", paging);

    mav.addObject("now_page", now_page);

    // /artist/list_by_artistno_search_paging.jsp
    mav.setViewName("/artist/list_by_artistno_search_paging");

    return mav;
  }
  
  /**
   * Grid 형태의 화면 구성 http://localhost:9091/artist/list_by_artistno_grid.do
   * 
   * @return
   */
  @RequestMapping(value = "/artist/list_by_artistno_grid.do", method = RequestMethod.GET)
  public ModelAndView list_by_artistno_grid() {
    ModelAndView mav = new ModelAndView();
    
    List<ArtistVO> list = this.artistProc.list_by_artistno();
    mav.addObject("list", list);

    // 테이블 이미지 기반, /webapp/news/list_by_newsno_grid.jsp
    mav.setViewName("/artist/list_by_artistno_grid");

    return mav; // forward
  }
  
  /**
   * 팬맺기 Ajax 수정 처리
   * http://localhost:9091/artist/update_fan_ajax.do?artistno=1
   * 
   * @return
   */
  @RequestMapping(value = "/artist/update_fan_ajax.do", method = RequestMethod.POST)
  @ResponseBody
  public String update_fan_ajax(int artistno) {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    int cnt = this.artistProc.update_fan(artistno); // 팬 수 증가
    int fan = this.artistProc.read(artistno).getFan(); // 새로운 팬 수 읽음
        
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    json.put("fan", fan);
    
    return json.toString();
  }
   
  
  // http://localhost:9091/artist/read.do
  /**
   * 조회
   * @return
   */
  @RequestMapping(value="/artist/read.do", method=RequestMethod.GET )
  public ModelAndView read(@RequestParam(value = "now_page", defaultValue = "1") int now_page,
                                        int artistno) {
    //public ModelAndView read(int newsno, int now_page) 
    //System.out.println("-> now_page: " + now_page);
    
    ModelAndView mav = new ModelAndView();

    ArtistVO artistVO = this.artistProc.read(artistno); 
    mav.addObject("artistVO", artistVO); // request.setAttribute("newsVO", newsVO);
    
    mav.setViewName("/artist/read"); // /WEB-INF/views/artist/read.jsp
        
    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9091/artist/update_artist.do?artistno=1
   * 
   * @return
   */
  @RequestMapping(value = "/artist/update_artist.do", method = RequestMethod.GET)
  public ModelAndView update_artist(int artistno) {
    ModelAndView mav = new ModelAndView();
    
    ArtistVO artistVO = this.artistProc.read(artistno);
    
    mav.addObject("artistVO", artistVO);
    
    mav.setViewName("/artist/update_artist"); // /WEB-INF/views/artist/update_artist.jsp

    return mav; // forward
  }
  
  /**
   * 수정 처리 http://localhost:9091/artist/update_artist.do
   * 
   * @return
   */
  @RequestMapping(value = "/artist/update_artist.do", method = RequestMethod.POST)
  public ModelAndView update_artist(HttpServletRequest request, 
                                                    ArtistVO artistVO
                                                    // ,int now_page
                                                    ) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    ArtistVO vo = artistProc.read(artistVO.getArtistno());
//    System.out.println("contentsno: " + vo.getContentsno());
//    System.out.println("file1: " + vo.getFile1());
    
    String fupname = vo.getFupname();
    String thumb = vo.getThumb();
    long fsize = 0;
    boolean sw = false;
    
    // 완성된 경로 F:/ai8/ws_frame/covid/src/main/resources/static/contents/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/artist/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, fupname);  // Folder에서 1건의 파일 삭제
    sw = Tool.deleteFile(upDir, thumb);     // Folder에서 1건의 파일 삭제
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String fname = "";          // 원본 파일명 image

    // 완성된 경로 F:/ai8/ws_frame/covid/src/main/resources/static/contents/storage/
    // String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로
    
    // 전송 파일이 없어도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택">
    MultipartFile mf = artistVO.getFile1MF();
    
    fname = mf.getOriginalFilename(); // 원본 파일명
    fsize = mf.getSize();  // 파일 크기
    
    if (fsize > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      fupname = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(fupname)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
        thumb = Tool.preview(upDir, fupname, 250, 200); 
      }
      
    }    
    
    artistVO.setFname(fname);
    artistVO.setFupname(fupname);
    artistVO.setThumb(thumb);
    artistVO.setFsize(fsize);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.artistProc.update_artist(artistVO);
    
    mav.addObject("artistno", artistVO.getArtistno());
    //mav.addObject("now_page", now_page);
    
    mav.setViewName("redirect:/artist/read.do"); 

    return mav; // forward
  }   
  
  /**
   * 삭제 폼
   * @param artistno
   * @return
   */
  @RequestMapping(value="/artist/delete.do", method=RequestMethod.GET )
  public ModelAndView delete(int artistno) { 
    ModelAndView mav = new  ModelAndView();
    
    // 삭제할 정보를 조회하여 확인
    ArtistVO artistVO = this.artistProc.read(artistno);
    mav.addObject("artistVO", artistVO);    
    
    mav.setViewName("/artist/delete");  // news/delete.jsp
    
    return mav; 
  }
  
  /**
   * 삭제 처리 http://localhost:9091/artist/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/artist/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request,
                                             int artistno,
      /* int now_page, */
                                             String word) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    ArtistVO vo = artistProc.read(artistno);
//    System.out.println("newsno: " + vo.getNewsno());
//    System.out.println("file1: " + vo.getFile1());
    
    String fupname = vo.getFupname();
    String thumb = vo.getThumb();
    long fsize = 0;
    boolean sw = false;
    
    // 완성된 경로 F:/ai8/ws_frame/Covid/src/main/resources/static/contents/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/artist/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, fupname);  // Folder에서 1건의 파일 삭제
    sw = Tool.deleteFile(upDir, thumb);     // Folder에서 1건의 파일 삭제
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    int cnt = this.artistProc.delete(artistno); // DBMS 삭제

    /*
     * if (cnt == 1) { //
     * -----------------------------------------------------------------------------
     * -------- // 마지막 페이지의 레코드 삭제시의 페이지 번호 -1 처리 HashMap<String, Object> map = new
     * HashMap<String, Object>(); //map.put("cateno", cateno); map.put("word",
     * word); // 10번째 레코드를 삭제후 // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면 //
     * 페이지수를 4 -> 3으로 감소 시켜야함. if (artistProc.search_count(map) %
     * Artist.RECORD_PER_PAGE == 0) { now_page = now_page - 1; if (now_page < 1) {
     * now_page = 1; // 시작 페이지 } } //
     * -----------------------------------------------------------------------------
     * -------- } // System.out.println("-> delete now_page: " + now_page);
     * mav.addObject("now_page", now_page); //mav.addObject("cateno", cateno);
     */
    mav.setViewName("redirect:/artist/list_by_artistno_grid.do"); 

    return mav; // forward
  }   
  
}
