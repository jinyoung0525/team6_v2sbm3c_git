package dev.mvc.pay;

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
public class PayCont {

  @Autowired
  @Qualifier("dev.mvc.pay.PayProc")
  private PayProcInter payProc;

  
  public PayCont() {
    System.out.println("-> PayCont created.");
  }

  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/pay/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }

	
	
	  // http://localhost:9091/pay/read.do
	   /**
	    * 조회
	    * @return
	    */
	   @RequestMapping(value="/pay/read.do", method=RequestMethod.GET )
	   public ModelAndView read(int pay_no) {
	     
	     ModelAndView mav = new ModelAndView();

	     PayVO payVO = this.payProc.read(pay_no);
	     mav.addObject("payVO", payVO); // request.setAttribute("productVO", productVO);

	     
	     mav.setViewName("/pay/read"); // /WEB-INF/views/pay/read.jsp
	         
	     return mav;
	   }
	   
	   
	
	   
    
     @RequestMapping(value = "/pay/kpay2.do", method = RequestMethod.GET)
     public ModelAndView test() {
       ModelAndView mav = new ModelAndView();
       
       
       mav.setViewName("/pay/kpay"); // /webapp/WEB-INF/views/pay/create.jsp


       return mav; // forward
     }
     

     @RequestMapping(value = "/pay/kpay.do", method = RequestMethod.POST)
     public ModelAndView test2() {
       ModelAndView mav = new ModelAndView();
       
       
       mav.setViewName("redirect:/pay/kpay.do");
       mav.setViewName("/pay/kpay"); // /webapp/WEB-INF/views/pay/create.jsp


       return mav; // forward
     }

}


