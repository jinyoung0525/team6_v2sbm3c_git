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
  public List<ProductVO> list_product_asc();
  
 

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
  public ProductVO read(int product_no);
  /**
   * 삭제
   * @param contentsno
   * @return
   */
  public int delete(int product_no);
  

  

   
  
}










