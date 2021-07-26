package dev.mvc.music;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.album.Album;
import dev.mvc.album.AlbumVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.music.MusicProc")
public class MusicProc implements MusicProcInter {
@Autowired
private MusicDAOInter musicDAO;

public MusicProc() {
  System.out.println("-> MusicProc created");
}

  @Override
  public int create(MusicVO musicVO) {
    int cnt = this.musicDAO.create(musicVO);
    return cnt;
  }
  
  @Override
  public List<MusicVO> list_all() {
    List<MusicVO> list = this.musicDAO.list_all();
    return list;
  }

  @Override
  public List<MusicVO> list_by_albumno(int albumno) {
    List<MusicVO> list = this.musicDAO.list_by_albumno(albumno);
    return list;
  }

  @Override
  public List<MusicVO> list_all_join() {
    List<MusicVO> list = this.musicDAO.list_all_join();
    return list;
  }
  
  @Override
  public List<MusicVO> list_by_albumno_search(HashMap<String, Object> hashMap) {
    List<MusicVO> list = musicDAO.list_by_albumno_search(hashMap);
    return list;
  }

  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    int count = musicDAO.search_count(hashMap);
    return count;
  }
  
  @Override
  public List<MusicVO> list_by_albumno_search_paging(HashMap<String, Object> map) {
    /* 
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지 시작 rownum: now_page = 1, (1 - 1) * 10 --> 0 
    2 페이지 시작 rownum: now_page = 2, (2 - 1) * 10 --> 10
    3 페이지 시작 rownum: now_page = 3, (3 - 1) * 10 --> 20
    */
    int begin_of_page = ((Integer)map.get("now_page") - 1) * Music.RECORD_PER_PAGE;
   
    // 시작 rownum 결정
    // 1 페이지 = 0 + 1, 2 페이지 = 10 + 1, 3 페이지 = 20 + 1 
    int start_num = begin_of_page + 1;
    
    //  종료 rownum
    // 1 페이지 = 0 + 10, 2 페이지 = 0 + 20, 3 페이지 = 0 + 30
    int end_num = begin_of_page + Music.RECORD_PER_PAGE;   
    /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
    */
    map.put("start_num", start_num);
    map.put("end_num", end_num);
   
    List<MusicVO> list = this.musicDAO.list_by_albumno_search_paging(map);
    return list;
    }
  
  @Override
  public String pagingBox(String list_file, int artistno, int search_count, int now_page, String word) {
    int total_page = (int)(Math.ceil((double)search_count/Music.RECORD_PER_PAGE)); // 전체 페이지 수 
    int total_grp = (int)(Math.ceil((double)total_page/Music.PAGE_PER_BLOCK)); // 전체 그룹  수
    int now_grp = (int)(Math.ceil((double)now_page/Music.PAGE_PER_BLOCK));  // 현재 그룹 번호
    
    // 1 group: 1 2 3
    // 2 group: 4 5 6
    // 3 group: 7 8 9
    int start_page = ((now_grp - 1) * Music.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
    int end_page = (now_grp * Music.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
     
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
    int _now_page = (now_grp - 1) * Music.PAGE_PER_BLOCK;  
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
    _now_page = (now_grp * Music.PAGE_PER_BLOCK)+1;  
    if (now_grp < total_grp){ 
      str.append("<span class='span_box_1'><A href='"+list_file+"?&word="+word+"&now_page="+_now_page+"&cateno="+artistno+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  @Override
  public MusicVO read(int songno) {
    MusicVO musicVO = this.musicDAO.read(songno);
        
        String title = musicVO.getTitle();
        String lyrics = musicVO.getLyrics();
        
        title = Tool.convertChar(title);
        lyrics = Tool.convertChar(lyrics);
        
        musicVO.setTitle(title);
        musicVO.setLyrics(lyrics);
        
        long size3 = musicVO.getSize3();
        musicVO.setSize3_label(Tool.unit(size3));
        
        long size4 = musicVO.getSize4();
        musicVO.setSize4_label(Tool.unit(size4));
        
        return musicVO;
  }

  @Override
  public int update_music(MusicVO musicVO) {
   int cnt = this.musicDAO.update_music(musicVO);
   return cnt;
  }

  @Override
  public int delete(int songno) {
   int cnt = this.musicDAO.delete(songno);
   return cnt;
  }

  @Override
  public int update_likey(int songno) {
    int cnt = this.musicDAO.update_likey(songno);
    return cnt;
  }

  

}
