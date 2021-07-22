package dev.mvc.artist;

import java.util.HashMap;
import java.util.List;

public interface ArtistProcInter {
  /**
   * 등록
   * @param artistVO
   * @return 등록된 레코드 갯수
   */
  public int create(ArtistVO artistVO);
  
  /**
   *  등록 순서별 목록
   *  @return
   */
  public List<ArtistVO>list_by_artistno();
  
  /**
   * 카테고리별 검색 목록
   * @param hashMap
   * @return
   */
  public List<ArtistVO> list_by_artistno_search(HashMap<String, Object> hashMap);

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
  public List<ArtistVO> list_by_artistno_search_paging(HashMap<String, Object> map);
  
  /**
   * 페이지 목록 문자열 생성, Box 형태
   * @param list_file 목록 파일명 
   * @param search_count 검색 갯수
   * @param now_page 현재 페이지, now_page는 1부터 시작
   * @param word 검색어
   * @return
   */
  public String pagingBox(String list_file, int search_count, int now_page, String word);
  
  /**
   * 팬수 증가
   * @param artistno
   * @return
   */
  public int update_fan(int artistno);
  
  /**
   * 조회
   * @param artistno
   * @return
   */
  public ArtistVO read(int artistno);
  
  /**
   * 텍스트 수정용 조회
   * @param artistno
   * @return
   */
  public ArtistVO read_update_artist(int artistno);
  
  /**
   * 텍스트 정보 수정
   * @param artistVO
   * @return
   */
  public int update_artist(ArtistVO artistVO);
  
  /**
   * 삭제
   * @param artistno
   * @return
   */
  public int delete(int artistno);
}
