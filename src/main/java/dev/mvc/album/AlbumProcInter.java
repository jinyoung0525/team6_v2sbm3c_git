package dev.mvc.album;

import java.util.HashMap;
import java.util.List;

import dev.mvc.artist.ArtistVO;

public interface AlbumProcInter {
  /**
   * 등록
   * @param albumVO
   * @return 등록된 갯수
   */
  public int create(AlbumVO albumVO);
  
  /**
   *  전체 목록
   * @return
   */
  public List<AlbumVO> list_all();  
  
  /**
   *  artistno별 목록
   * @return
   */
  public List<AlbumVO> list_by_artistno(int artistno);   
  
  /**
   * 카테고리별 검색 목록
   * @param hashMap
   * @return
   */
  public List<AlbumVO> list_by_artistno_search(HashMap<String, Object> hashMap);

  /**
   * 카테고리별 검색 레코드 갯수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 검색 + 페이징 목록
   * @param map
   * @return
   */
  public List<AlbumVO> list_by_artistno_search_paging(HashMap<String, Object> map);
  
  /**
   * 페이지 목록 문자열 생성, Box 형태
   * @param list_file 목록 파일명 
   * @param artistno 아티스트번호
   * @param search_count 검색 갯수
   * @param now_page 현재 페이지, now_page는 1부터 시작
   * @param word 검색어
   * @return
   */
  public String pagingBox(String list_file, int artistno, int search_count, int now_page, String word);
  
  /**
   * 조회
   * @param albumno
   * @return
   */
  public AlbumVO read(int albumno);
  
  /**
   * 텍스트 정보 수정
   * @param albumVO
   * @return
   */
  public int update_album(AlbumVO albumVO);
  
  /**
   * 삭제
   * @param albumno
   * @return
   */
  public int delete(int albumno);
  
  /**
   * 추천수 증가
   * @param albumno
   * @return
   */
  public int update_likey(int albumno);
}
