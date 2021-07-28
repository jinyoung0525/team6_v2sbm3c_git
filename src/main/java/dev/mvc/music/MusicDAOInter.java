package dev.mvc.music;

import java.util.HashMap;
import java.util.List;

import dev.mvc.album.AlbumVO;

public interface MusicDAOInter {

  /**
   * 등록
   * @param musicVO
   * @return 등록된 갯수
   */
  public int create(MusicVO musicVO);
  
  /**
   * 조인 등록
   * @param musicVO
   * @return 등록된 갯수
   */
  public int create_join(MusicVO musicVO);
  
  /**
   *  전체 목록
   * @return
   */
  public List<MusicVO> list_all();  
  
  /**
   * Album + Music  join, 연결 목록
   * @return
   */
  public List<MusicVO> list_all_join();  
  
  /**
   * 특정 카테고리의 등록된 글목록
   * @return
   */
  public List<MusicVO> list_by_albumno(int albumno);
  
  /**
   * 앨범별 검색 목록
   * @param hashMap
   * @return
   */
  public List<MusicVO> list_by_albumno_search(HashMap<String, Object> hashMap);

  /**
   * 앨범별 검색 레코드 갯수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 검색 + 페이징 목록
   * @param map
   * @return
   */
  public List<MusicVO> list_by_albumno_search_paging(HashMap<String, Object> map);
  
  /**
   * 조회
   * @param musicno
   * @return
   */
  public MusicVO read(int musicno);
  
  /**
   * 수정
   * @param musicVO
   * @return
   */
  public int update_music(MusicVO musicVO);
  
  /**
   * 삭제
   * @param songno
   * @return
   */
  public int delete(int songno);
  
  /**
   * 추천수 증가
   * @param songno
   * @return
   */
  public int update_likey(int songno);
 
}
