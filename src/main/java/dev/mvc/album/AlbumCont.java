package dev.mvc.album;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import dev.mvc.artist.ArtistProcInter;
import dev.mvc.artist.ArtistVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class AlbumCont {
  @Autowired
  @Qualifier("dev.mvc.album.AlbumProc")
  private AlbumProcInter albumProc;
  
  @Autowired
  @Qualifier("dev.mvc.artist.ArtistProc")
  private ArtistProcInter artistProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  public AlbumCont() {
    System.out.println("-> AlbumCont created.");
  }
  
  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/album/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
//http://localhost:9091/album/create.do?artistno=1
 /**
  * 등록 폼
  * @return
  */
 @RequestMapping(value="/album/create.do", method=RequestMethod.GET )
 public ModelAndView create(int artistno) {
   ModelAndView mav = new ModelAndView();
   
   ArtistVO artistVO = this.artistProc.read(artistno);
   mav.addObject("artistVO", artistVO);
   
   mav.setViewName("/album/create"); // /WEB-INF/views/album/create.jsp
   
   return mav; // forward
 }
  
//http://localhost:9091/album/create.do
/**
 * 등록 처리
 * @param albumVO
 * @return
 */
@RequestMapping(value="/album/create.do", method=RequestMethod.POST )
public ModelAndView create(HttpServletRequest request, AlbumVO albumVO) { // albumVO 자동 생성, Form -> VO
  // ArtistVO albumVO <FORM> 태그의 값으로 자동 생성됨.
  // request.setAttribute("albumVO", albumVO); 자동 실행
  
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
  String upDir =  user_dir + "/src/main/resources/static/album/storage/"; // 절대 경로
  
  // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
  // <input type='file' class="form-control" name='file1MF' id='file1MF' 
  //           value='' placeholder="파일 선택">
  MultipartFile mf = albumVO.getFnameMF();
  
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
  
  albumVO.setFname(fname);
  albumVO.setFupname(fupname);
  albumVO.setThumb(thumb);
  albumVO.setFsize(fsize);
  // -------------------------------------------------------------------
  // 파일 전송 코드 종료
  // -------------------------------------------------------------------
  
  int cnt = this.albumProc.create(albumVO); // 등록 처리
  
  mav.addObject("cnt", cnt); // request에 저장, request.setAttribute("cnt", cnt)
  mav.addObject("artistno", albumVO.getArtistno());
  mav.addObject("title", albumVO.getTitle());
  mav.addObject("url", "/album/create_msg");
  
  mav.addObject("albumno", albumVO.getAlbumno());
  
  mav.setViewName("redirect:/album/msg.do"); // /webapp/WEB-INF/views/artist/create_msg.jsp
  /*
   * // cnt = 0; // error test if (cnt == 1) {
   * mav.setViewName("redirect:/artist/list.do"); } else { mav.addObject("code",
   * "create"); // request에 저장, request.setAttribute("cnt", cnt)
   * mav.setViewName("/artist/error_msg"); // /WEB-INF/views/artist/error_msg.jsp
   * }
   */
  

  return mav; // forward
}

//http://localhost:9091/album/create.do?artistno=1
/**
* 조인 등록 폼
* @return
*/
@RequestMapping(value="/album/create_join.do", method=RequestMethod.GET )
public ModelAndView create_join() {
 ModelAndView mav = new ModelAndView();
 mav.setViewName("/album/create_join"); // /WEB-INF/views/album/create.jsp
 
 return mav; // forward
}

//http://localhost:9091/album/create_join.do
/**
* 조인 등록 처리
* @param albumVO
* @return
*/
@RequestMapping(value="/album/create_join.do", method=RequestMethod.POST )
public ModelAndView create_join(HttpServletRequest request, AlbumVO albumVO) { // albumVO 자동 생성, Form -> VO
// ArtistVO albumVO <FORM> 태그의 값으로 자동 생성됨.
// request.setAttribute("albumVO", albumVO); 자동 실행

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
String upDir =  user_dir + "/src/main/resources/static/album/storage/"; // 절대 경로

// 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
// <input type='file' class="form-control" name='file1MF' id='file1MF' 
//           value='' placeholder="파일 선택">
MultipartFile mf = albumVO.getFnameMF();

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

albumVO.setFname(fname);
albumVO.setFupname(fupname);
albumVO.setThumb(thumb);
albumVO.setFsize(fsize);
// -------------------------------------------------------------------
// 파일 전송 코드 종료
// -------------------------------------------------------------------

int cnt = this.albumProc.create_join(albumVO); // 등록 처리

mav.addObject("cnt", cnt); // request에 저장, request.setAttribute("cnt", cnt)
mav.addObject("url", "/album/create_join_msg");

mav.addObject("albumno", albumVO.getAlbumno());
mav.addObject("title", albumVO.getTitle());




mav.setViewName("redirect:/album/msg.do"); // /webapp/WEB-INF/views/artist/create_msg.jsp
/*
 * // cnt = 0; // error test if (cnt == 1) {
 * mav.setViewName("redirect:/artist/list.do"); } else { mav.addObject("code",
 * "create"); // request에 저장, request.setAttribute("cnt", cnt)
 * mav.setViewName("/artist/error_msg"); // /WEB-INF/views/artist/error_msg.jsp
 * }
 */


return mav; // forward
}

/**
 * 전체 목록
 * http://localhost:9091/album/list_all.do 
 * @return
 */
@RequestMapping(value="/album/list_all.do", method=RequestMethod.GET )
public ModelAndView list_all() {
  ModelAndView mav = new ModelAndView();
  
  List<AlbumVO> list = this.albumProc.list_all();
  mav.addObject("list", list); // request.setAttribute("list", list);

  mav.setViewName("/album/list_all"); // /album/list_all.jsp
  return mav;
}

/**
 * 아티스트별 전체 목록
 * http://localhost:9091/album/list_by_artistno.do?artistno=1 
 * @return
 */
@RequestMapping(value="/album/list_by_artistno.do", method=RequestMethod.GET )
public ModelAndView list_by_artistno(int artistno) {
  ModelAndView mav = new ModelAndView();
  
  ArtistVO  artistVO = this.artistProc.read(artistno); // 카테고리 그룹 정보
  mav.addObject("artistVO", artistVO); 
  
  List<AlbumVO> list = this.albumProc.list_by_artistno(artistno);
  mav.addObject("list", list); // request.setAttribute("list", list);

  mav.setViewName("/album/list_by_artistno"); // /album/list_by_artistno.jsp
  return mav;
}

/**
 * Artist + Album join, 연결 목록
 * http://localhost:9091/album/list_all_join.do 
 * @return
 */
@RequestMapping(value="/album/list_all_join.do", method=RequestMethod.GET )
public ModelAndView list_all_join() {
  ModelAndView mav = new ModelAndView();
  
  List<AlbumVO> list = this.albumProc.list_all_join();
  mav.addObject("list", list); // request.setAttribute("list", list);

  mav.setViewName("/album/list_all_join"); // /cate/list_all_join.jsp
  return mav;
}

/**
 * 목록 + 검색 지원
 * http://localhost:9091/album/list_by_artistno_search.do?artistno=1&word=M
 * @param artistno
 * @param word
 * @return
 */
  @RequestMapping(value = "/album/list_by_artistno_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_artistno_search(@RequestParam(value="artistno", defaultValue="1") int artistno,
                                                                  @RequestParam(value="word", defaultValue="") String word ) {
  
  ModelAndView mav = new ModelAndView(); 
       
  // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용 
  HashMap<String, Object> map = new HashMap<String, Object>(); 
  map.put("artistno", artistno); // #{artistno}
  map.put("word", word); // #{word}
  
  // 검색 목록 
  List<AlbumVO> list = albumProc.list_by_artistno_search(map);
  mav.addObject("list", list);
  
  // 검색된 레코드 갯수 
  int search_count = albumProc.search_count(map);
  mav.addObject("search_count", search_count);
  
  ArtistVO artistVO = artistProc.read(artistno); 
  mav.addObject("artistVO", artistVO);
  
  mav.setViewName("/album/list_by_artistno_search");   // /contents/list_by_cateno_search.jsp
  
  return mav; 
}
  
  /**
   * 목록 + 검색 + 페이징 지원
   * http://localhost:9091/album/list_by_artistno_search_paging.do?artistno=1&word=M&now_page=1
   * 
   * @param artistno
   * @param word
   * @param now_page
   * @return
   */
  @RequestMapping(value = "/album/list_by_artistno_search_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_artistno_search_paging(@RequestParam(value = "artistno", defaultValue = "1") int artistno,
      @RequestParam(value = "word", defaultValue = "") String word,
      @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
    System.out.println("--> now_page: " + now_page);

    ModelAndView mav = new ModelAndView();

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("artistno", artistno); // #{cateno}
    map.put("word", word); // #{word}
    map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용

    // 검색 목록
    List<AlbumVO> list = albumProc.list_by_artistno_search_paging(map);
    mav.addObject("list", list);

    // 검색된 레코드 갯수
    int search_count = albumProc.search_count(map);
    mav.addObject("search_count", search_count);

    ArtistVO artistVO = artistProc.read(artistno);
    mav.addObject("artistVO", artistVO);

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
    String paging = albumProc.pagingBox("list_by_artistno_search_paging.do", artistno, search_count, now_page, word);
    mav.addObject("paging", paging);

    mav.addObject("now_page", now_page);

    // /contents/list_by_cateno_table_img1_search_paging.jsp
    mav.setViewName("/album/list_by_artistno_search_paging");

    return mav;
  }
  /**
   * Grid 형태의 화면 구성 http://localhost:9091/album/list_by_artistno_grid.do?artistno=1
   * 
   * @return
   */
  @RequestMapping(value = "/album/list_by_artistno_grid.do", method = RequestMethod.GET)
  public ModelAndView list_by_artistno_grid(int artistno) {
    ModelAndView mav = new ModelAndView();
    
    ArtistVO artistVO = this.artistProc.read(artistno);
    mav.addObject("artistVO", artistVO);
   
    List<AlbumVO> list = this.albumProc.list_by_artistno(artistno);
    mav.addObject("list", list);

    // 테이블 이미지 기반, /webapp/contents/list_by_cateno_grid.jsp
    mav.setViewName("/album/list_by_artistno_grid");

    return mav; // forward
  }
  
  /**
   * 추천수 Ajax 수정 처리
   * http://localhost:9091/album/update_likey_ajax.do?albumno=1
   * 
   * @return
   */
  @RequestMapping(value = "/album/update_likey_ajax.do", method = RequestMethod.POST)
  @ResponseBody
  public String update_likey_ajax(int albumno) {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    int cnt = this.albumProc.update_likey(albumno); // 추천수 증가
    int likey = this.albumProc.read(albumno).getLikey(); // 새로운 추천수 읽음
        
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    json.put("likey", likey);
    
    return json.toString();
  }
  
 //http://localhost:9091/album/read.do
 /**
  * 조회
  * @return
  */
 @RequestMapping(value="/album/read.do", method=RequestMethod.GET )
 public ModelAndView read(@RequestParam(value = "now_page", defaultValue = "1") int now_page,
                                     @RequestParam(value = "artistno", defaultValue = "1") int artistno,
                                       int albumno, HttpSession session) {
   //public ModelAndView read(int newsno, int now_page) 
   //System.out.println("-> now_page: " + now_page);
   
   ModelAndView mav = new ModelAndView();
   
   if (this.memberProc.isMember(session)) {
     AlbumVO albumVO = this.albumProc.read(albumno);
     mav.addObject("albumVO", albumVO);

     ArtistVO artistVO = this.artistProc.read(albumVO.getArtistno()); 
     mav.addObject("artistVO", artistVO); // request.setAttribute("newsVO", newsVO);
     
     mav.setViewName("/album/read"); // /WEB-INF/views/album/read.jsp
     
   } else {
     mav.addObject("url", "login_need"); // login_need.jsp, redirect parameter 적용
     
     mav.setViewName("redirect:/member/msg.do");  
   }
       
   return mav;
 }
  
  /**
   * 수정 폼
   * http://localhost:9091/album/update_album.do?albumno=1
   * 
   * @return
   */
  @RequestMapping(value = "/album/update_album.do", method = RequestMethod.GET)
  public ModelAndView update_album(int albumno) {
    ModelAndView mav = new ModelAndView();
    
    AlbumVO albumVO = this.albumProc.read(albumno);
    ArtistVO artistVO = this.artistProc.read(albumVO.getArtistno());
    
    mav.addObject("albumVO", albumVO);
    mav.addObject("artistVO", artistVO);
    
    mav.setViewName("/album/update_album"); // /WEB-INF/views/album/update_album.jsp

    return mav; // forward
  }
  
  /**
   * 수정 처리 http://localhost:9091/album/update_album.do
   * 
   * @return
   */
  @RequestMapping(value = "/album/update_album.do", method = RequestMethod.POST)
  public ModelAndView update_album(HttpServletRequest request, 
                                                    AlbumVO albumVO
                                                    // ,int now_page
                                                    ) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    AlbumVO vo = albumProc.read(albumVO.getAlbumno());
//    System.out.println("contentsno: " + vo.getContentsno());
//    System.out.println("file1: " + vo.getFile1());
    
    String fupname = vo.getFupname();
    String thumb = vo.getThumb();
    long fsize = 0;
    boolean sw = false;
    
    // 완성된 경로 F:/ai8/ws_frame/covid/src/main/resources/static/contents/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/album/storage/"; // 절대 경로

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
    MultipartFile mf = albumVO.getFnameMF();
    
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
    
    albumVO.setFname(fname);
    albumVO.setFupname(fupname);
    albumVO.setThumb(thumb);
    albumVO.setFsize(fsize);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.albumProc.update_album(albumVO);
    
    mav.addObject("albumno", albumVO.getAlbumno());
    //mav.addObject("now_page", now_page);
    
    mav.setViewName("redirect:/album/read.do"); 

    return mav; // forward
  }   
  
  /**
   * 삭제 폼
   * @param albumno
   * @return
   */
  @RequestMapping(value="/album/delete.do", method=RequestMethod.GET )
  public ModelAndView delete(int albumno) { 
    ModelAndView mav = new  ModelAndView();
    
    // 삭제할 정보를 조회하여 확인
    AlbumVO albumVO = this.albumProc.read(albumno);
    ArtistVO artistVO = this.artistProc.read(albumVO.getArtistno());
    
    mav.addObject("albumVO", albumVO);    
    mav.addObject("artistVO", artistVO);
    
    mav.setViewName("/album/delete");  // album/delete.jsp
    
    return mav; 
  }
  
  /**
   * 삭제 처리 http://localhost:9091/album/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/album/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request,
                                             int albumno,
                                             int artistno,
      /* int now_page, */
                                             String word) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    AlbumVO vo = albumProc.read(albumno);
//    System.out.println("newsno: " + vo.getNewsno());
//    System.out.println("file1: " + vo.getFile1());
    
    String fupname = vo.getFupname();
    String thumb = vo.getThumb();
    long fsize = 0;
    boolean sw = false;
    
    // 완성된 경로 F:/ai8/ws_frame/Covid/src/main/resources/static/contents/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/album/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, fupname);  // Folder에서 1건의 파일 삭제
    sw = Tool.deleteFile(upDir, thumb);     // Folder에서 1건의 파일 삭제
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    int cnt = this.albumProc.delete(albumno); // DBMS 삭제

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
    mav.addObject("artistno",artistno);
    
    mav.setViewName("redirect:/album/list_by_artistno_grid.do"); 

    return mav; // forward
  }   
  
}
