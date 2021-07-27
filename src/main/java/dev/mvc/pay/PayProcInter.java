package dev.mvc.pay;

import java.util.HashMap;
import java.util.List;

import org.springframework.util.MultiValueMap;

import reactor.core.publisher.Mono;

public interface PayProcInter {
  /**
   * 등록
   * @param productVO
   * @return
   */
  public int create_c(PayVO payVO);

  
  /**
   * 카테고리별 검색 목록
   * @param hashMap
   * @return
   */
  public List<PayVO> list_by_search(HashMap<String, Object> hashMap);

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
  public List<PayVO> list_by_search_paging(HashMap<String, Object> map);

  /**
   * 페이지 목록 문자열 생성, Box 형태
   * @param list_file 목록 파일명 
   * @param categrpno 카테고리번호
   * @param search_count 검색 갯수
   * @param now_page 현재 페이지, now_page는 1부터 시작
   * @param word 검색어
   * @return
   */
  public String pagingBox(String list_file, int search_count, int now_page, String word, String v);

  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public PayVO read(int pay_no);
  
  
  /**
   * 회원 조회
   * @param contentsno
   * @return
   */
  public List<PayVO> read_member(int memberno);
  
 
  
//임시
  public List<PayVO> list_product_asc();




   
  
}










