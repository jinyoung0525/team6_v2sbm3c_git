package dev.mvc.pay;

import java.util.HashMap;
import java.util.List;


public interface PayDAOInter {
  /**
   * 등록
   * @param payVO
   * @return
   */
  public int create_c(PayVO payVO);


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
   * 조회
   * @param productno
   * @return
   */
  public PayVO read(int pay_no);
  

  
  
  //임시
  public List<PayVO> list_product_asc();
  
}






