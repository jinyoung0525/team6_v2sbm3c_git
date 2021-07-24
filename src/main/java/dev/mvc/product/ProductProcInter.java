package dev.mvc.product;

import java.util.HashMap;
import java.util.List;

import org.springframework.util.MultiValueMap;

import reactor.core.publisher.Mono;

public interface ProductProcInter {
  /**
   * 등록
   * @param productVO
   * @return
   */
  public int create(ProductVO productVO);

  /**
   * 특정 카테고리의 등록된 글목록
   * @param product
   * @return
   */
  public List<ProductVO> list_by_cateno(int product);
  
  /**
   * 카테고리별 검색 목록
   * @param hashMap
   * @return
   */
  public List<ProductVO> list_by_search(HashMap<String, Object> hashMap);

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
  public List<ProductVO> list_by_search_paging(HashMap<String, Object> map);

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
   * 상품 정보 수정 처리
   * @param contentsVO
   * @return
   */
  public int product_update(ProductVO productVO);
  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public ProductVO read(int productno);
  
  /**
   * 텍스트 정보 수정
   * @param contentsVO
   * @return
   */
  public int update_text(ProductVO productVO);
 
  /**
   * 텍스트 수정용 조회
   * @param contentsno
   * @return
   */
  public ProductVO read_update_text(int productno);
  
  /**
   * 파일 정보 수정
   * @param contentsVO
   * @return
   */
  public int update_file(ProductVO productVO);
  
//임시
  public List<ProductVO> list_product_asc();

   
  
}










