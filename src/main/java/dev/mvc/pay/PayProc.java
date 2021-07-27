package dev.mvc.pay;

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

@Component("dev.mvc.pay.PayProc")
  public class PayProc implements PayProcInter {
    @Autowired
    private PayDAOInter payDAO;

    @Override
    public int create_c(PayVO payVO) {
     
      int cnt=this.payDAO.create_c(payVO);
      
      return cnt;
    }



    @Override
    public int search_count(HashMap<String, Object> hashMap) {
      int count = payDAO.search_count(hashMap);
      return count;
    }

    /** 
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     *
     * @param list_file 목록 파일명 
     * @param cateno 카테고리번호 
     * @param search_count 검색(전체) 레코드수 
     * @param now_page     현재 페이지
     * @param word 검색어
     * @return 페이징 생성 문자열
     */ 
    
    @Override
    public String pagingBox(String list_file, int search_count, int now_page, String word, String v){ 
      int total_page = (int)(Math.ceil((double)search_count/Pay.RECORD_PER_PAGE)); // 전체 페이지 수 
      int total_grp = (int)(Math.ceil((double)total_page/Pay.PAGE_PER_BLOCK)); // 전체 그룹  수
      int now_grp = (int)(Math.ceil((double)now_page/Pay.PAGE_PER_BLOCK));  // 현재 그룹 번호
      
      // 1 group: 1 2 3
      // 2 group: 4 5 6
      // 3 group: 7 8 9
      int start_page = ((now_grp - 1) * Pay.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
      int end_page = (now_grp * Pay.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
       
      StringBuffer str = new StringBuffer(); 
       
      str.append("<style type='text/css'>"); 
      str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
      str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
      str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
      str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
      str.append("  .span_box_1{"); 
      str.append("    text-align: center;");    
      str.append("    font-size: 1em;"); 
      str.append("    border: 1px;"); 
      str.append("    border-style: solid;"); 
      str.append("    border-color: #cccccc;"); 
      str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/");
      str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
      str.append("  }"); 
      str.append("  .span_box_2{"); 
      str.append("    text-align: center;");    
      str.append("    background-color: #668db4;"); 
      str.append("    color: #FFFFFF;"); 
      str.append("    font-size: 1em;"); 
      str.append("    border: 1px;"); 
      str.append("    border-style: solid;"); 
      str.append("    border-color: #cccccc;"); 
      str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
      str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
      str.append("  }"); 
      str.append("</style>"); 
      str.append("<DIV id='paging'>"); 
//      str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
   
      // 이전 10개 페이지로 이동
      // now_grp: 1 (1 ~ 10 page)
      // now_grp: 2 (11 ~ 20 page)
      // now_grp: 3 (21 ~ 30 page) 
      // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 마지막 페이지 10
      // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 마지막 페이지 20
      int _now_page = (now_grp - 1) * Pay.PAGE_PER_BLOCK;  
      if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 이전 그룹으로 갈수 있는 링크 생성 
        str.append("<span class='span_box_1'><A href='"+list_file+"?&word="+word+"&now_page="+_now_page+"&v="+v+"'>이전</A></span>"); 
      } 
   
      // 중앙의 페이지 목록
      for(int i=start_page; i<=end_page; i++){ 
        if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
          break; 
        } 
    
        if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
          str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
        }else{
          // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
          str.append("<span class='span_box_1'><A href='"+list_file+"?word="+word+"&now_page="+i+"&v="+v+"'>"+i+"</A></span>");   
        } 
      } 
   
      // 10개 다음 페이지로 이동
      // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
      // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 시작페이지 11
      // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 시작페이지 21
      _now_page = (now_grp * Pay.PAGE_PER_BLOCK)+1;  
      if (now_grp < total_grp){ 
        str.append("<span class='span_box_1'><A href='"+list_file+"?&word="+word+"&now_page="+_now_page+"&v="+v+"'>다음</A></span>"); 
      } 
      str.append("</DIV>"); 
       
      return str.toString(); 
    }
 
    @Override
    public List<PayVO> list_by_search_paging(HashMap<String, Object> map) {
    	/* 
        페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
        1 페이지 시작: LIMIT 0, 10 
        2 페이지 시작: LIMIT 10, 10 
        3 페이지 시작: LIMIT 20, 10 

        offset 결정, 1페이지당 출력할 레코드 갯수 10개
        1 페이지 = 0 * 10: LIMIT 0, 10
        2 페이지 = 1 * 10: LIMIT 10, 10
        3 페이지 = 2 * 10: LIMIT 20, 10
         */ 
       // int offset = ((Integer)map.get("now_page") - 1) * Product.RECORD_PER_PAGE;
        // System.out.println("-> offset: " + offset);
        
       // int start_num = offset ;
        
        //int end_num = offset + Product.RECORD_PER_PAGE;   
        //System.out.println("-> : start_num" +start_num);
        //System.out.println("-> :end_num" +end_num);
        //map.put("start_num", start_num);
        //map.put("end_num", end_num);
        //map.put("page_size", Product.RECORD_PER_PAGE);
        // System.out.println("-> page_size: " + Contents.RECORD_PER_PAGE);
        
        List<PayVO> list = this.payDAO.list_by_search_paging(map);
        
        return list;
    }
    
    
    @Override
    public PayVO read(int pay_no) {
      PayVO payVO = this.payDAO.read(pay_no);
      
      String payname = payVO.getPay_name();
      
      payname = Tool.convertChar(payname);  // 특수 문자 처리
       
      payVO.setPay_name(payname);
      
      return payVO;
    }
    
    
    
    @Override
    public List<PayVO> read_member(int memberno) {
      List<PayVO> payVO = null;
      payVO = this.payDAO.read_member(memberno);
      
      //String payname = payVO.getPay_name();
      
      //payname = Tool.convertChar(payname);  // 특수 문자 처리
       
      //payVO.setPay_name(payname);
      
      return payVO;
    }
    
    
	
	 @Override
	  public List<PayVO> list_product_asc() {
		 List<PayVO> list = null;
	    list = this.payDAO.list_product_asc();
	    return list;
	  }
	 
	 
  @Override
  public List<PayVO> list_by_search(HashMap<String, Object> hashMap) {
    // TODO Auto-generated method stub
    return null;
  }

}





