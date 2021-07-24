package dev.mvc.product;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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


//import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import reactor.core.publisher.Mono;

@Controller
public class ProductCont {

  private static String tid = null;
  @Autowired
  @Qualifier("dev.mvc.product.ProductProc")
  private ProductProcInter productProc;

  
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

	    mav.setViewName("/product/list"); // /webapp/bookmarkgrp/list.jsp
	    return mav;
}
	
	/**
	* 목록 + 검색 + 페이징 지원
	* http://localhost:9091/product/list_by_cateno_search_paging.do?cateno=1&word=스위스&now_page=1
	* 
	* @param cateno
	* @param word
	* @param now_page
	* @return
	*/
	@RequestMapping(value = "/product/list.do", method = RequestMethod.GET)
	public ModelAndView list_by_search_paging(
			@RequestParam(value = "word", defaultValue = "") String word,
			//@RequestParam(value = "now_page", defaultValue = "1")int now_page,
			@RequestParam(value = "v", defaultValue = "l")  String v) {
		  //System.out.println("--> now_page: " + now_page);
	  

		ModelAndView mav = new ModelAndView();

		// 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
		HashMap<String, Object> map = new HashMap<String, Object>();
	    //map.put("cateno", cateno); // #{cateno}
	    map.put("word", word); // #{word}
	    //map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
	    map.put("v", v);
	    // 검색 목록
	    List<ProductVO> list = productProc.list_by_search_paging(map);
	    mav.addObject("list", list);
	    System.out.println("--> now_page: "+ list);

	    // 검색된 레코드 갯수
	    //int search_count = productProc.search_count(map);
	    //mav.addObject("search_count", search_count);


	    /*
	     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
	     * 18 19 20 [다음]
	     * @param list_file 목록 파일명
	     * @param cateno 카테고리번호
	     * @param search_count 검색(전체) 레코드수
	     * @param now_page 현재 페이지
	     * @param word 검색어
	     * @return 페이징 생성 문자열
	     */
	    //String paging = productProc.pagingBox("list.do",search_count, now_page, word, v);
	    //mav.addObject("paging", paging);
	     
	    //mav.addObject("now_page", now_page);

	    // /product/list_by_cateno_table_img1_search_paging.jsp
	    
	    if(v.equals("g")) {
	    	mav.setViewName("/product/list_by_grid");
	    } else {
	    	mav.setViewName("/product/list_by_search_paging");
	    }
	    	
	    return mav;
	   }
	
	
	  // http://localhost:9091/product/read.do
	   /**
	    * 조회
	    * @return
	    */
	   @RequestMapping(value="/product/read.do", method=RequestMethod.GET )
	   public ModelAndView read(int product_no) {
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
	   
	   
	   
	   
	   
	   /**
      * 등록폼
      * http://localhost:9091/product/create.do?cateno=1
      * 
      * @return
      */
      
     @RequestMapping(value = "/product/kpay5.do", method = RequestMethod.GET)
     public ModelAndView Test(String res) {
  
       ModelAndView mav = new ModelAndView();
       
       System.out.println(res);
       mav.addObject("cnt", res);
       //mav.setViewName("redirect:"+ res{);
       
       return mav;

     }
     
	   
	  
     /**
      * 주문 처리
      * @param
      * @return
      */
     
	   @RequestMapping(value="/product/kpay.do", 
                                         method=RequestMethod.GET)
	   @ResponseBody
	   public String Kpay() {
	     
	     Kpay kpay  = new Kpay();
	     String kpay2 = kpay.Kpayready();
	     
	     JSONObject json = new JSONObject(kpay2);
	     System.out.println(json.get("tid"));
	     
	     tid = json.get("tid").toString();
	     
	    
	     return json.toString();
	     
	   }
       
	   @RequestMapping(value = "/product/kpaysuccess.do", method = RequestMethod.GET)
     public ModelAndView test3(String pg_token) {
       ModelAndView mav = new ModelAndView();
   
       
       Kpay kpay  = new Kpay();
       String kpay2 = kpay.Kpayapprove(pg_token, tid);
       
       System.out.println(kpay2);

       
       mav.addObject("tid", tid);
       mav.setViewName("/product/kpaysuccess"); // /webapp/WEB-INF/views/product/create.jsp


       return mav; // forward
     }
	   
	   @RequestMapping(value = "/product/paysuccess_msg.do", method = RequestMethod.GET)
     public ModelAndView Kpaysuccess() {
       ModelAndView mav = new ModelAndView();
       
       
       mav.setViewName("/product/paysuccess_msg"); // /webapp/WEB-INF/views/product/create.jsp


       return mav; // forward
     }
	   
    
     @RequestMapping(value = "/product/kpay2.do", method = RequestMethod.GET)
     public ModelAndView test() {
       ModelAndView mav = new ModelAndView();
       
       
       mav.setViewName("/product/kpay"); // /webapp/WEB-INF/views/product/create.jsp


       return mav; // forward
     }
     

     @RequestMapping(value = "/product/kpay.do", method = RequestMethod.POST)
     public ModelAndView test2() {
       ModelAndView mav = new ModelAndView();
       
       
       mav.setViewName("redirect:/product/kpay.do");
       mav.setViewName("/product/kpay"); // /webapp/WEB-INF/views/product/create.jsp


       return mav; // forward
     }

}


