package dev.mvc.album;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.artist.ArtistDAOInter;
import dev.mvc.artist.ArtistVO;
import dev.mvc.tool.Tool;


@Component("dev.mvc.album.AlbumProc")
public class AlbumProc implements AlbumProcInter {
  @Autowired
  private AlbumDAOInter albumDAO;
  private ArtistDAOInter artistDAO;
  
  public AlbumProc() {
    System.out.println("-> AlbumProc created");
  }
  
  @Override
  public int create(AlbumVO albumVO) {
    int cnt = this.albumDAO.create(albumVO);
    return cnt;
  }

  @Override
  public List<AlbumVO> list_all() {
    List<AlbumVO> list = this.albumDAO.list_all();
    return list;
  }

  @Override
  public List<AlbumVO> list_by_artistno(int artistno) {
    List<AlbumVO> list = this.albumDAO.list_by_artistno(artistno);
    return list;
  }

  @Override
  public List<AlbumVO> list_by_artistno_search(HashMap<String, Object> hashMap) {
    List<AlbumVO> list = albumDAO.list_by_artistno_search(hashMap);
    
    for (AlbumVO albumVO : list) { // 내용이 160자 이상이면 160자만 선택
      String intro = albumVO.getIntro();
      String detail = albumVO.getDetail();
      if (detail.length() > 160 || intro.length() > 160) {
        intro = intro.substring(0, 160) + "...";
        albumVO.setIntro(intro);
        detail = detail.substring(0, 160) + "...";
        albumVO.setDetail(detail);
      }
    }
    
    return list;
  }

  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    int count = albumDAO.search_count(hashMap);
    return count;
  }

  @Override
  public List<AlbumVO> list_by_artistno_search_paging(HashMap<String, Object> map) {
    /* 
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지 시작 rownum: now_page = 1, (1 - 1) * 10 --> 0 
    2 페이지 시작 rownum: now_page = 2, (2 - 1) * 10 --> 10
    3 페이지 시작 rownum: now_page = 3, (3 - 1) * 10 --> 20
    */
    int begin_of_page = ((Integer)map.get("now_page") - 1) * Album.RECORD_PER_PAGE;
   
    // 시작 rownum 결정
    // 1 페이지 = 0 + 1, 2 페이지 = 10 + 1, 3 페이지 = 20 + 1 
    int start_num = begin_of_page + 1;
    
    //  종료 rownum
    // 1 페이지 = 0 + 10, 2 페이지 = 0 + 20, 3 페이지 = 0 + 30
    int end_num = begin_of_page + Album.RECORD_PER_PAGE;   
    /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
    */
    map.put("start_num", start_num);
    map.put("end_num", end_num);
   
    List<AlbumVO> list = this.albumDAO.list_by_artistno_search_paging(map);
    
    for (AlbumVO albumVO : list) { // 내용이 160자 이상이면 160자만 선택
      String intro = albumVO.getIntro();
      String detail = albumVO.getDetail();
      if (detail.length() > 160 || intro.length() > 160) {
        intro = intro.substring(0, 160) + "...";
        albumVO.setIntro(intro);
        detail = detail.substring(0, 160) + "...";
        albumVO.setDetail(detail);
      }
      String title = Tool.convertChar(albumVO.getTitle());
      albumVO.setTitle(title);
      
      intro = Tool.convertChar(intro);
      albumVO.setIntro(intro);
      
      detail = Tool.convertChar(detail);
      albumVO.setDetail(detail);
    }
      
    
      
      // CKEditor 에서는 주석 처리 할 것.
      // content = Tool.convertChar(content);
      // contentsVO.setContent(content);
    return list;
    }

  @Override
  public String pagingBox(String list_file, int artistno, int search_count, int now_page, String word) {
    int total_page = (int)(Math.ceil((double)search_count/Album.RECORD_PER_PAGE)); // 전체 페이지 수 
    int total_grp = (int)(Math.ceil((double)total_page/Album.PAGE_PER_BLOCK)); // 전체 그룹  수
    int now_grp = (int)(Math.ceil((double)now_page/Album.PAGE_PER_BLOCK));  // 현재 그룹 번호
    
    // 1 group: 1 2 3
    // 2 group: 4 5 6
    // 3 group: 7 8 9
    int start_page = ((now_grp - 1) * Album.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
    int end_page = (now_grp * Album.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
 
    // 이전 10개 페이지로 이동
    // now_grp: 1 (1 ~ 10 page)
    // now_grp: 2 (11 ~ 20 page)
    // now_grp: 3 (21 ~ 30 page) 
    // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 마지막 페이지 10
    // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 마지막 페이지 20
    int _now_page = (now_grp - 1) * Album.PAGE_PER_BLOCK;  
    if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 이전 그룹으로 갈수 있는 링크 생성 
      str.append("<span class='span_box_1'><A href='"+list_file+"?&word="+word+"&now_page="+_now_page+"&cateno="+artistno+"'>이전</A></span>"); 
    } 
 
    // 중앙의 페이지 목록
    for(int i=start_page; i<=end_page; i++){ 
      if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
        break; 
      } 
  
      if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
        str.append("<span class='span_box_1'><A href='"+list_file+"?word="+word+"&now_page="+i+"&cateno="+artistno+"'>"+i+"</A></span>");   
      } 
    } 
 
    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 시작페이지 11
    // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 시작페이지 21
    _now_page = (now_grp * Album.PAGE_PER_BLOCK)+1;  
    if (now_grp < total_grp){ 
      str.append("<span class='span_box_1'><A href='"+list_file+"?&word="+word+"&now_page="+_now_page+"&cateno="+artistno+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  @Override
  public AlbumVO read(int albumno) {
    AlbumVO albumVO = this.albumDAO.read(albumno);
    
    String title = albumVO.getTitle();
    // Integer debut = artistVO.getDebut();
    String kind = albumVO.getKind();
    String release = albumVO.getRelease();
    String genre = albumVO.getGenre();
    String enter = albumVO.getEnter();
    String intro = albumVO.getIntro();
    String detail = albumVO.getDetail();
    
    title = Tool.convertChar(title);
    /* debut = Tool.convertChar(debut); */
    kind = Tool.convertChar(kind);
    release = Tool.convertChar(release);
    genre = Tool.convertChar(genre);
    enter = Tool.convertChar(enter);
    intro = Tool.convertChar(intro);
    detail = Tool.convertChar(detail);
    
    albumVO.setTitle(title);
    albumVO.setKind(kind);
    albumVO.setRelease(release);
    albumVO.setGenre(genre);
    albumVO.setEnter(enter);
    albumVO.setIntro(intro);
    albumVO.setDetail(detail);
    
    long fsize = albumVO.getFsize();
    albumVO.setFsize_label(Tool.unit(fsize));
    
    return albumVO;
  }

  @Override
  public int update_album(AlbumVO albumVO) {
    int cnt = this.albumDAO.update_album(albumVO);
    return cnt;
  }

  @Override
  public int delete(int albumno) {
    int cnt = this.albumDAO.delete(albumno);
    return cnt;
  }

  @Override
  public int update_likey(int albumno) {
    int cnt = this.albumDAO.update_likey(albumno);
    return cnt;
  }

}
