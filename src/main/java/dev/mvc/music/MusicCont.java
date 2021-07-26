package dev.mvc.music;

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

import dev.mvc.album.AlbumProcInter;
import dev.mvc.album.AlbumVO;
import dev.mvc.artist.ArtistProcInter;
import dev.mvc.artist.ArtistVO;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class MusicCont {
  @Autowired
  @Qualifier("dev.mvc.artist.ArtistProc")
  private ArtistProcInter artistProc;
  
  @Autowired
  @Qualifier("dev.mvc.album.AlbumProc")
  private AlbumProcInter albumProc;
  
  @Autowired
  @Qualifier("dev.mvc.music.MusicProc")
  private MusicProcInter musicProc;
  
  public MusicCont() {
    System.out.println("-> MusicCont created.");
  }
  
  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/music/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
  
    mav.setViewName(url); // forward
    
    return mav; // forward
  }

  /**
   * 등록폼 http://localhost:9091/music/create.do?albumno=1
   * 
   * @return
   */
  @RequestMapping(value = "/music/create.do", method = RequestMethod.GET)
  public ModelAndView create(int albumno) {
    ModelAndView mav = new ModelAndView();
    
    AlbumVO albumVO = this.albumProc.read(albumno);
    ArtistVO artistVO = this.artistProc.read(albumVO.getArtistno());
    
    mav.addObject("albumVO", albumVO);
    mav.addObject("ArtistVO", artistVO);
    
    mav.setViewName("/music/create"); // /webapp/WEB-INF/views/cate/create.jsp

    return mav;
  }

  /**
   * 등록 처리 http://localhost:9091/music/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/music/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, MusicVO musicVO) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String mp3 = "";          // mp3 파일명
    String mp4 = "";          // mp4파일명

    // 기준 경로 확인
    String user_dir = System.getProperty("user.dir");
    // System.out.println("-> User dir: " + user_dir);
    //  --> User dir: F:\ai8\ws_frame\resort_v1sbm3a
    
    // 파일 접근임으로 절대 경로 지정, static 지정
    // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage
    String upDir =  user_dir + "/src/main/resources/static/music/storage/"; // 절대 경로
    
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택">
    MultipartFile mf1 = musicVO.getMp3MF();
    
    mp3 = mf1.getOriginalFilename(); // 원본 파일명
    long size3 = mf1.getSize();  // 파일 크기
    
    MultipartFile mf2 = musicVO.getMp4MF();
    
    mp4 = mf2.getOriginalFilename(); // 원본 파일명
    long size4 = mf2.getSize();  // 파일 크기
    
    if (size3 > 0   || size4 > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      mp3 = Upload.saveFileSpring(mf1, upDir); 
      mp4 = Upload.saveFileSpring(mf2, upDir);
    }    
    
    musicVO.setMp3(mp3);
    musicVO.setMp4(mp4);
    musicVO.setSize3(size3);
    musicVO.setSize4(size4);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.musicProc.create(musicVO); 
    
    // -------------------------------------------------------------------
    // PK의 return
    // -------------------------------------------------------------------
    // System.out.println("--> contentsno: " + contentsVO.getContentsno());
    // mav.addObject("contentsno", contentsVO.getContentsno()); // redirect parameter 적용
    // -------------------------------------------------------------------
    
//    if (cnt == 1) {
//      cateProc.increaseCnt(contentsVO.getCateno()); // 글수 증가
//    }
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

    // System.out.println("--> cateno: " + contentsVO.getCateno());
    // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
    mav.addObject("albumno", musicVO.getAlbumno()); // redirect parameter 적용
    mav.addObject("url", "/music/create_msg"); // create_msg.jsp, redirect parameter 적용

    // 연속 입력 지원용 변수, Call By Reference에 기반하여 contentsno를 전달 받음
    mav.addObject("songno", musicVO.getSongno());
    mav.addObject("title", musicVO.getTitle());
    
    mav.setViewName("redirect:/music/msg.do"); 
    
    return mav; // forward
  }
  
  /**
   * 전체 목록
   * http://localhost:9091/music/list_all.do 
   * @return
   */
  @RequestMapping(value="/music/list_all.do", method=RequestMethod.GET )
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    List<MusicVO> list = this.musicProc.list_all();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/music/list_all"); // /music/list_all.jsp
    return mav;
  }
  
  /**
   * 앨범별 목록 http://localhost:9091/music/list_by_albumno.do?albumno=1
   * 
   * @return
   */
   @RequestMapping(value = "/music/list_by_albumno.do", method = RequestMethod.GET)
    public ModelAndView list_by_albumno(int albumno) { 
      ModelAndView mav = new  ModelAndView();  
      
      AlbumVO albumVO = this.albumProc.read(albumno); 
      mav.addObject("albumVO", albumVO);
      
      ArtistVO artistVO = this.artistProc.read(albumVO.getArtistno());
      mav.addObject("artistVO", artistVO);
      
      List<MusicVO> list = this.musicProc.list_by_albumno(albumno);
      mav.addObject("list", list);

      // 테이블 이미지 기반, list_by_cateno.jsp
      mav.setViewName("/music/list_by_albumno"); // /views/contents/list_by_cateno.jsp
      
      return mav; // forward 
    }

   /**
    * 목록 + 검색 지원
    * http://localhost:9091/music/list_by_albumno_search.do?albumno=1&word=LOSER
    * @param albumno
    * @param word
    * @return
    */
     @RequestMapping(value = "/music/list_by_albumno_search.do", method = RequestMethod.GET)
     public ModelAndView list_by_albumno_search(@RequestParam(value="albumno", defaultValue="1") int albumno,
                                                                     @RequestParam(value="word", defaultValue="") String word ) {
     
     ModelAndView mav = new ModelAndView(); 
          
     // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용 
     HashMap<String, Object> map = new HashMap<String, Object>(); 
     map.put("albumno", albumno); // #{cateno}
     map.put("word", word); // #{word}
     
     // 검색 목록 
     List<MusicVO> list = musicProc.list_by_albumno_search(map);
     mav.addObject("list", list);
     
     // 검색된 레코드 갯수 
     int search_count = musicProc.search_count(map);
     mav.addObject("search_count", search_count);
     
     AlbumVO albumVO = albumProc.read(albumno); 
     mav.addObject("albumVO", albumVO);
     
     ArtistVO artistVO = this.artistProc.read(albumVO.getArtistno());
     mav.addObject("artistVO", artistVO);
     
     mav.setViewName("/music/list_by_albumno_search");   // /contents/list_by_cateno_search.jsp
     
     return mav; 
   }
     
   /**
    * 목록 + 검색 + 페이징 지원
    * http://localhost:9091/music/list_by_albumno_search_paging.do?albumno=1&word=베베&now_page=1
    * 
    * @param albumno
    * @param word
    * @param now_page
    * @return
    */
   @RequestMapping(value = "/music/list_by_albumno_search_paging.do", method = RequestMethod.GET)
   public ModelAndView list_by_albumno_search_paging(
       @RequestParam(value = "albumno", defaultValue = "1") int albumno,
       @RequestParam(value = "word", defaultValue = "") String word,
       @RequestParam(value = "now_page", defaultValue = "1") int now_page,
       HttpServletRequest request) {
     System.out.println("--> now_page: " + now_page);

     ModelAndView mav = new ModelAndView();

     // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
     HashMap<String, Object> map = new HashMap<String, Object>();
     map.put("albumno", albumno); // #{cateno}
     map.put("word", word); // #{word}
     map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용

     // 검색 목록
     List<MusicVO> list = musicProc.list_by_albumno_search_paging(map);
     mav.addObject("list", list);

     // 검색된 레코드 갯수
     int search_count = musicProc.search_count(map);
     mav.addObject("search_count", search_count);

     AlbumVO albumVO = albumProc.read(albumno);
     mav.addObject("albumVO", albumVO);

     ArtistVO artistVO = artistProc.read(albumVO.getArtistno());
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
     String paging = musicProc.pagingBox("list_by_albumno_search_paging.do", albumno, search_count, now_page, word);
     mav.addObject("paging", paging);

     mav.addObject("now_page", now_page);

     // /contents/list_by_cateno_table_img1_search_paging.jsp
     mav.setViewName("/music/list_by_albumno_search_paging");

     return mav;
   }
   
   /**
    * 추천수 Ajax 수정 처리
    * http://localhost:9091/music/update_likey_ajax.do?songno=30
    * 
    * @return
    */
   @RequestMapping(value = "/music/update_likey_ajax.do", method = RequestMethod.POST)
   @ResponseBody
   public String update_likey_ajax(int songno) {
     try {
       Thread.sleep(1000);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }

     int cnt = this.musicProc.update_likey(songno); // 추천수 증가
     int likey = this.musicProc.read(songno).getLikey(); // 새로운 추천수 읽음
         
     JSONObject json = new JSONObject();
     json.put("cnt", cnt);
     json.put("likey", likey);
     
     return json.toString();
   }
   
   // http://localhost:9091/music/read.do
   /**
    * 조회
    * @return
    */
   @RequestMapping(value="/music/read.do", method=RequestMethod.GET )
   public ModelAndView read(@RequestParam(value = "now_page", defaultValue = "1") int now_page,
                                       @RequestParam(value = "albumno", defaultValue = "1") int albumno,
                                       int songno) {
     ModelAndView mav = new ModelAndView();

     MusicVO musicVO = this.musicProc.read(songno);
     mav.addObject("musicVO", musicVO); // request.setAttribute("contentsVO", contentsVO);

     AlbumVO albumVO = this.albumProc.read(musicVO.getAlbumno());
     mav.addObject("albumVO", albumVO); 

     ArtistVO artistVO = this.artistProc.read(albumVO.getArtistno());
     mav.addObject("artistVO", artistVO); 
     
     mav.setViewName("/music/read"); // /WEB-INF/views/contents/read.jsp
         
     return mav;
   }
   
   /**
    * 수정 폼
    * http://localhost:9091/music/update_music.do?songno=1
    * 
    * @return
    */
   @RequestMapping(value = "/music/update_music.do", method = RequestMethod.GET)
   public ModelAndView update_music(int songno) {
     ModelAndView mav = new ModelAndView();
     
     MusicVO musicVO = this.musicProc.read(songno);
     AlbumVO albumVO = this.albumProc.read(musicVO.getAlbumno());
     ArtistVO artistVO = this.artistProc.read(albumVO.getArtistno());
     
     mav.addObject("musicVO", musicVO);
     mav.addObject("albumVO", albumVO);
     mav.addObject("artistVO", artistVO);
     
     mav.setViewName("/music/update_music"); // /WEB-INF/views/album/update_album.jsp

     return mav; // forward
   }
   
   /**
    * 수정 처리 http://localhost:9091/music/update_music.do
    * 
    * @return
    */
   @RequestMapping(value = "/music/update_music.do", method = RequestMethod.POST)
   public ModelAndView update_music(HttpServletRequest request, 
                                                     MusicVO musicVO
                                                     // ,int now_page
                                                     ) {
     ModelAndView mav = new ModelAndView();

     // -------------------------------------------------------------------
     // 파일 삭제 코드 시작
     // -------------------------------------------------------------------
     // 삭제할 파일 정보를 읽어옴.
     MusicVO vo = musicProc.read(musicVO.getSongno());
//     System.out.println("contentsno: " + vo.getContentsno());
//     System.out.println("file1: " + vo.getFile1());
     
     String mp3s = vo.getMp3();
     String mp4s = vo.getMp4();
     long size3 = 0;
     long size4 = 0;
     boolean sw = false;
     
     // 완성된 경로 F:/ai8/ws_frame/covid/src/main/resources/static/contents/storage/
     String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/music/storage/"; // 절대 경로

     sw = Tool.deleteFile(upDir, mp3s);  // Folder에서 1건의 파일 삭제
     sw = Tool.deleteFile(upDir, mp4s);     // Folder에서 1건의 파일 삭제
     // System.out.println("sw: " + sw);
     // -------------------------------------------------------------------
     // 파일 삭제 종료 시작
     // -------------------------------------------------------------------
     
     // -------------------------------------------------------------------
     // 파일 전송 코드 시작
     // -------------------------------------------------------------------
     String mp3 = "";          // 원본 파일명 image
     String mp4 = "";

     // 완성된 경로 F:/ai8/ws_frame/covid/src/main/resources/static/contents/storage/
     // String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로
     
     // 전송 파일이 없어도 fnamesMF 객체가 생성됨.
     // <input type='file' class="form-control" name='file1MF' id='file1MF' 
     //           value='' placeholder="파일 선택">
     MultipartFile mf1 = musicVO.getMp3MF();
     MultipartFile mf2 = musicVO.getMp4MF();
     
     mp3 = mf1.getOriginalFilename(); // 원본 파일명
     mp4 = mf2.getOriginalFilename();
     size3 = mf1.getSize();  // 파일 크기
     size4 = mf2.getSize(); 
     
     if (size3 > 0   || size4 > 0) { // 파일 크기 체크
       // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
       mp3 = Upload.saveFileSpring(mf1, upDir); 
       mp4 = Upload.saveFileSpring(mf2, upDir);
     }    
     
     musicVO.setMp3(mp3);
     musicVO.setMp4(mp4);
     musicVO.setSize3(size3);
     musicVO.setSize4(size4);
     // -------------------------------------------------------------------
     // 파일 전송 코드 종료
     // -------------------------------------------------------------------
     
     // Call By Reference: 메모리 공유, Hashcode 전달
     int cnt = this.musicProc.update_music(musicVO);
     
     mav.addObject("songno", musicVO.getSongno());
     //mav.addObject("now_page", now_page);
     
     mav.setViewName("redirect:/music/read.do"); 

     return mav; // forward
   }   
   
   /**
    * 삭제 폼
    * @param songno
    * @return
    */
   @RequestMapping(value="/music/delete.do", method=RequestMethod.GET )
   public ModelAndView delete(int songno) { 
     ModelAndView mav = new  ModelAndView();
     
     // 삭제할 정보를 조회하여 확인
     MusicVO musicVO = this.musicProc.read(songno);
     mav.addObject("musicVO", musicVO); 
     
     AlbumVO albumVO = this.albumProc.read(musicVO.getAlbumno());
     mav.addObject("albumVO", albumVO);  
     
     ArtistVO artistVO = this.artistProc.read(albumVO.getArtistno());   
     mav.addObject("artistVO", artistVO);
     
     mav.setViewName("/music/delete");  // album/delete.jsp
     
     return mav; 
   }
   
   /**
    * 삭제 처리 http://localhost:9091/album/delete.do
    * 
    * @return
    */
   @RequestMapping(value = "/music/delete.do", method = RequestMethod.POST)
   public ModelAndView delete(HttpServletRequest request,
                                              int songno,
                                              int albumno,
       /* int now_page, */
                                              String word) {
     ModelAndView mav = new ModelAndView();

     // -------------------------------------------------------------------
     // 파일 삭제 코드 시작
     // -------------------------------------------------------------------
     // 삭제할 파일 정보를 읽어옴.
     MusicVO vo = musicProc.read(songno);
//     System.out.println("newsno: " + vo.getNewsno());
//     System.out.println("file1: " + vo.getFile1());
     
     String mp3 = vo.getMp3();
     String mp4 = vo.getMp4();
     long size3 = 0;
     long size4 = 0;
     boolean sw = false;
     
     // 완성된 경로 F:/ai8/ws_frame/Covid/src/main/resources/static/contents/storage/
     String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/music/storage/"; // 절대 경로

     sw = Tool.deleteFile(upDir, mp3);  // Folder에서 1건의 파일 삭제
     sw = Tool.deleteFile(upDir, mp4);     // Folder에서 1건의 파일 삭제
     // System.out.println("sw: " + sw);
     // -------------------------------------------------------------------
     // 파일 삭제 종료 시작
     // -------------------------------------------------------------------
     
     int cnt = this.musicProc.delete(songno); // DBMS 삭제

     /*
      * if (cnt == 1) { //
      * -----------------------------------------------------------------------------
      * -------- // 마지막 페이지의 레코드 삭제시의 페이지 번호 -1 처리 HashMap<String, Object> map = new
      * HashMap<String, Object>(); map.put("albumno", albumno); map.put("word",
      * word); // 10번째 레코드를 삭제후 // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면 //
      * 페이지수를 4 -> 3으로 감소 시켜야함. if (musicProc.search_count(map) %
      * Music.RECORD_PER_PAGE == 0) { now_page = now_page - 1; if (now_page < 1) {
      * now_page = 1; // 시작 페이지 } } //
      * -----------------------------------------------------------------------------
      * -------- } // System.out.println("-> delete now_page: " + now_page);
      * mav.addObject("now_page", now_page);
      */
     mav.addObject("albumno",albumno);
     
     mav.setViewName("redirect:/music/list_by_albumno_search_paging.do"); 

     return mav; // forward
   }   
   
 


}
