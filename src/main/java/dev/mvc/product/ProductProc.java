package dev.mvc.product;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tool.Tool;
import reactor.core.publisher.Mono;

@Component("dev.mvc.product.ProductProc")
  public class ProductProc implements ProductProcInter {
    @Autowired
    private ProductDAOInter productDAO;

    @Override
    public int create(ProductVO productVO) {
      int cnt=this.productDAO.create(productVO);
      return cnt;
    }
    /*
    @Override
    public List<ProductVO> list_by_product(int product) {
      List<ProductVO> list = this.productDAO.list_by_cateno(cateno);
      
      for (ProductVO contentsVO : list) { // 내용이 160자 이상이면 160자만 선택
        String product = contentsVO.getContent();
        if (product.length() > 160) {
          product = product.substring(0, 160) + "...";
          contentsVO.setContent(product);
        }
      }
      
      return list;
    }
    */
    
   
    
    @Override
    public int product_update(ProductVO productVO) {
      int cnt = this.productDAO.product_update(productVO);
      return cnt;
    }
    
    @Override
    public ProductVO read(int product_no) {
      ProductVO productVO = this.productDAO.read(product_no);
      
      String productname = productVO.getProduct_name();
      String productcont = productVO.getProduct_cont();
      
      productname = Tool.convertChar(productname);  // 특수 문자 처리
      productcont = Tool.convertChar(productcont); 
      
      productVO.setProduct_name(productname);
      productVO.setProduct_cont(productcont);  
      
      //long size1 = productVO.getSize1();
      //productVO.setSize1_label(Tool.unit(size1));
      
      return productVO;
    }
    
 

	
	 @Override
	  public List<ProductVO> list_product_asc() {
	   List<ProductVO> list = this.productDAO.list_product_asc();
	    list = this.productDAO.list_product_asc();
	    return list;
	  }


	
	@Override
  public int delete(int product_no) {
    int cnt = this.productDAO.delete(product_no);
    return cnt;
  }







	

}





