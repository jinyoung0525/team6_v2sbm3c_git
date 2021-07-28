package dev.mvc.product;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;


import dev.mvc.pay.PayProcInter;
import dev.mvc.pay.PayVO;
//import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import reactor.core.publisher.Mono;

@Controller
public class ProductCont {

  
  @Autowired
  @Qualifier("dev.mvc.product.ProductProc")
  private ProductProcInter productProc;
  
  @Autowired
  @Qualifier("dev.mvc.pay.PayProc")
  private PayProcInter payProc;

  
  public ProductCont() {
    System.out.println("-> ProductCont created.");
  }

  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/product/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
   * 등록폼
   * http://localhost:9091/product/create.do?cateno=1
   * 
   * @return
   */
  @RequestMapping(value = "/product/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    
    mav.setViewName("/product/create"); // /webapp/WEB-INF/views/product/create.jsp


    return mav; // forward
  }
  
  /*
  /**
   * 등록 처리 http://localhost:9091/product/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/product/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ProductVO productVO) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";          // 원본 파일명 image
    String file1saved = "";  // 저장된 파일명, image
    String thumb1 = "";     // preview image

    // 기준 경로 확인
    String user_dir = System.getProperty("user.dir");
    System.out.println("--> User dir: " + user_dir);
    //  --> User dir: F:\ai8\ws_frame\resort_v1sbm3a
    
    // 파일 접근임으로 절대 경로 지정, static 지정
    // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/product/storage
    String upDir =  user_dir + "/src/main/resources/static/product/storage/"; // 절대 경로
    
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택">
    MultipartFile mf = productVO.getFile1MF();
    
    file1 = mf.getOriginalFilename(); // 원본 파일명
    //long size1 = mf.getSize();  // 파일 크기
    
    //if (size1 > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
     // file1saved = Upload.saveFileSpring(mf, upDir); 
      
      //if (Tool.isImage(file1saved)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
      //  thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
     // }
      
    //}    
    
    productVO.setFile1(file1);
    //productVO.setFile1saved(file1saved);
    //productVO.setThumb1(thumb1);
    //productVO.setSize1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.productProc.create(productVO); 
    
    // -------------------------------------------------------------------
    // PK의 return
    // -------------------------------------------------------------------
    // System.out.println("--> productno: " + productVO.getContentsno());
    // mav.addObject("productno", productVO.getContentsno()); // redirect parameter 적용
    // -------------------------------------------------------------------
    
//    if (cnt == 1) {
//      cateProc.increaseCnt(productVO.getCateno()); // 글수 증가
//    }
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

    // System.out.println("--> cateno: " + productVO.getCateno());
    // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
    //mav.addObject("url", "/product/create_msg"); // create_msg.jsp, redirect parameter 적용

    // 연속 입력 지원용 변수, Call By Reference에 기반하여 productno를 전달 받음
    //mav.addObject("productno", productVO.getProductno());
    
    mav.setViewName("redirect:/product/list.do"); 
    
    return mav; // forward
  }
  
  	//http://localhost:9091/bookmarkgrp/list.do
	@RequestMapping(value="/product/list2.do", method=RequestMethod.GET )
	public ModelAndView list() {
	  ModelAndView mav = new ModelAndView();
	    
	  List<ProductVO> list = this.productProc.list_product_asc();
	    mav.addObject("list", list); // request.setAttribute("list", list);

	    mav.setViewName("/product/list_by_search_paging"); // /webapp/bookmarkgrp/list.jsp
	    return mav;
}
	
	/**
	* 목록
	* 
	* 
	* @param cateno
	* @param word
	* @param now_page
	* @return
	*/
	@RequestMapping(value = "/product/list.do", method = RequestMethod.GET)
	public ModelAndView list_product_asc(HttpSession session) {
		  //System.out.println("--> now_page: " + now_page);
	 
	  

		ModelAndView mav = new ModelAndView();
		
		if(session.getAttribute("memberno")==null) {
		  
		}else {
		int memberno = (int) session.getAttribute("memberno");
		System.out.println(memberno);
		 List<PayVO> payVO = payProc.read_member(memberno);
		 mav.addObject("payVO", payVO);
		}
		 
	    List<ProductVO> list = productProc.list_product_asc();
	    mav.addObject("list", list);
	    

	    
	    
	    	mav.setViewName("/product/list_product_asc");
	    	
	    return mav;
	   }
	
	
	  // http://localhost:9091/product/read.do
	   /**
	    * 조회
	    * @return
	    */
	   @RequestMapping(value="/product/read.do", method=RequestMethod.GET )
	   public ModelAndView read(HttpSession session, int product_no) {
	     // public ModelAndView read(int productno, int now_page) {
	     // System.out.println("-> now_page: " + now_page);
	     
	     ModelAndView mav = new ModelAndView();

	     ProductVO productVO = this.productProc.read(product_no);
	     mav.addObject("productVO", productVO); // request.setAttribute("productVO", productVO);

	     
	     mav.setViewName("/product/read"); // /WEB-INF/views/product/read.jsp
	         
	     return mav;
	   }
	   
	   
	   /**
	    * 상품 정보 수정 폼
	    * 사전 준비된 레코드: 관리자 1번, cateno 1번, categrpno 1번을 사용하는 경우 테스트 URL
	    * http://localhost:9091/contents/create.do?cateno=1
	    * 
	    * @return
	    */
	   @RequestMapping(value = "/product/product_update.do", method = RequestMethod.GET)
	   public ModelAndView product_update(int product_no) {
	     ModelAndView mav = new ModelAndView();
	     
	     ProductVO productVO = this.productProc.read(product_no);
	     
	     mav.addObject("productVO", productVO);
	     
	     mav.setViewName("/product/product_update"); // /views/contents/product_update.jsp
	     // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
	     // mav.addObject("content", content);

	     return mav; // forward
	   }
	   
	// http://localhost:9091/categrp/update.do
	   /**
	    * 수정 처리
	    * @param categrpVO
	    * @return
	    */
	   @RequestMapping(value="/product/product_update.do", 
	                               method=RequestMethod.POST )
	   public ModelAndView update(ProductVO productVO) {
	
	     
	     ModelAndView mav = new ModelAndView();
	     
	     int cnt = this.productProc.product_update(productVO);
	     mav.addObject("cnt", cnt); // request에 저장
	     
	     if (cnt == 1) {
	       mav.setViewName("redirect:/product/list.do");   
	     } else {
	       mav.setViewName("/product/product_msg"); // /WEB-INF/views/categrp/update_msg.jsp
	     }
	     
	     return mav;
	   }
	   
	   
	   
	   
	   
	   //이용권정보
	   private static String tid = null;
	   private static int product_price;
	   private static int Pay_count;
	   private static int Pay_day;
	  
     /**
      * 주문 처리
      * @param
      * @return
      */
     
	   @RequestMapping(value="/product/kpay.do", 
                                         method=RequestMethod.GET)
	   @ResponseBody
	   public String Kpay(HttpSession session, int product_no, int product_price, int Pay_count, int Pay_day) {
	     
	     
	       
	       ProductVO productVO = this.productProc.read(product_no);
	       
	       //System.out.println(session.getAttribute("id"));
	       //System.out.println(session);
	       String partner_order_id ="00001";  //주문번호
	       String partner_user_id = (String) session.getAttribute("id");  //구매자id
	       
	       String Product_name = productVO.getProduct_name();
	       String product_pricest = Integer.toString(productVO.getproduct_price());
	       
	       Kpay kpay  = new Kpay();
	       String kpay2 = kpay.Kpayready(partner_order_id, partner_user_id,
	                                                 Product_name,product_pricest);
	       
	       this.product_price = product_price;
	       this.Pay_count = Pay_count;
	       this.Pay_day = Pay_day;
	       
	       
	       JSONObject json = new JSONObject(kpay2);
	       System.out.println(json.get("tid"));
	       
	       tid = json.get("tid").toString();
	      
	       
	       return json.toString();
	       

	   }
       
     /**
      * 결제완료 http://localhost:9091/product/kpaysuccess.do
      * 
      * @return
      */
	   @RequestMapping(value = "/product/kpaysuccess.do", method = RequestMethod.GET)
     public ModelAndView create_c(HttpSession session, String pg_token) {
       ModelAndView mav = new ModelAndView();
   
       String partner_order_id ="00001";
       String partner_user_id = (String) session.getAttribute("id");
       int memberno = (int) session.getAttribute("memberno");
       
       
       
       Kpay kpay  = new Kpay();
       String kpay2 = kpay.Kpayapprove(tid, partner_order_id, partner_user_id, pg_token);
       
       JSONObject json = new JSONObject(kpay2);
       
       //json.get(key)
       
       PayVO payVO = new PayVO();
       payVO.setApproved_a(json.getString("approved_at"));
       payVO.setCreated_at(json.getString("created_at"));
       payVO.setPay_count(this.Pay_count);
       payVO.setPay_day(this.Pay_day);
       payVO.setPay_name(json.getString("item_name"));
       payVO.setPayment("카카오페이");
       payVO.setPayment_method_type(json.getString("payment_method_type"));
       payVO.setPrice(this.product_price);
       payVO.setTid(tid);
       payVO.setMemberno(memberno);
       
       System.out.println(json.getString("approved_at"));
       //System.out.println(payVO.getPay_count());
       
       
       payProc.create_c(payVO);
    
 
       mav.addObject("tid", tid);
       mav.setViewName("/product/kpaysuccess"); // /webapp/WEB-INF/views/product/create.jsp


       return mav; // forward
     }
	   
	   /**
      * 결제성공 http://localhost:9091/product/kpaysuccess.do
      * 
      * @return
      */
	   @RequestMapping(value = "/product/paysuccess_msg.do", method = RequestMethod.GET)
     public ModelAndView Kpaysuccess() {
       ModelAndView mav = new ModelAndView();
       
       
       mav.setViewName("/product/paysuccess_msg"); // /webapp/WEB-INF/views/product/create.jsp


       return mav; // forward
     }
	   /**
      * 결제실패 http://localhost:9091/product/kpaysuccess.do
      * 
      * @return
      */
	   @RequestMapping(value = "/product/payfail_msg.do", method = RequestMethod.GET)
     public ModelAndView Kpayfail() {
       ModelAndView mav = new ModelAndView();
       
       
       mav.setViewName("/product/payfail_msg"); // /webapp/WEB-INF/views/product/create.jsp


       return mav; // forward
     }

     
	   /**
      * 삭제 처리 http://localhost:9091/contents/delete.do
      * 
      * @return
      */
     @RequestMapping(value = "/product/delete.do", method = RequestMethod.GET)
     public ModelAndView delete(int product_no) {
       ModelAndView mav = new ModelAndView();

      
       int cnt = this.productProc.delete(product_no); // DBMS 삭제

       
       mav.setViewName("redirect:/product/list.do"); 

       return mav; // forward
     }   

}


