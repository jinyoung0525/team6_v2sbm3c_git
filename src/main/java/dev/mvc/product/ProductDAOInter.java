package dev.mvc.product;

import java.util.HashMap;
import java.util.List;


public interface ProductDAOInter {
  /**
   * 등록
   * @param productVO
   * @return
   */
  public int create(ProductVO productVO);

  /**
   * 특정 카테고리의 등록된 글목록
   * @param cateno
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
   * 상품 정보 수정 처리
   * @param contentsVO
   * @return
   */
  public int product_update(ProductVO productVO);
  
  /**
   * 조회
   * @param productno
   * @return
   */
  public ProductVO read(int productno);
  
  /**
   * 텍스트 정보 수정
   * @param productVO
   * @return
   */
  public int update_text(ProductVO productVO);
  
  /**
   * 파일 정보 수정
   * @param productVO
   * @return
   */
  public int update_file(ProductVO productVO);
  
  //임시
  public List<ProductVO> list_product_asc();
  
}






