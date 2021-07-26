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
   * 검색 + 페이징 목록
   * @param map
   * @return
   */
  public List<ProductVO> list_product_asc();
  
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
  public ProductVO read(int product_no);
  
  /**
   * 삭제
   * @param productno
   * @return
   */
  public int delete(int product_no);

  
 
}






