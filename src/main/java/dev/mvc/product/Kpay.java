package dev.mvc.product;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Kpay {
  String url = "https://kapi.kakao.com/";   //kakaopay post
  
  public  String Kpayready() {
    

    WebClient webClient = WebClient.builder().build();
    MultiValueMap<String, String> formData =  new LinkedMultiValueMap<>();
  
    String a= "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=test01&quantity=1&total_amount=2900&tax_free_amount=0&approval_url=http://localhost:9091&cancel_url=http://localhost:9091&fail_url=http://localhost:9091";
  
  
    formData.add("cid", "TC0ONETIME" );
    formData.add("partner_order_id", "partner_order_id" );
    formData.add("partner_user_id", "partner_user_id" );
    formData.add("item_name", "test01" );
    formData.add("quantity", "1" );
    formData.add("total_amount", "2900" );
    formData.add("tax_free_amount",  "0" );
    formData.add("approval_url",  "http://localhost:9091/product/kpaysuccess.do");
    formData.add("cancel_url", "http://localhost:9091" );
    formData.add("fail_url", "http://localhost:9091" );
    
  
  
  
    List<String> res = webClient.post()
            .uri(url+"v1/payment/ready")
            .header("Authorization", "KakaoAK c92ab4879f981778f7237de24e2f169d")
            .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8")
            .bodyValue(formData)
            .retrieve()
            .bodyToFlux(String.class)
            .toStream()
            .collect(Collectors.toList());
    
    return res.get(0);
  }
  
public  String Kpayapprove(String token, String tid) {
    

    WebClient webClient = WebClient.builder().build();
    MultiValueMap<String, String> formData =  new LinkedMultiValueMap<>();
  
    String a= "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=test01&quantity=1&total_amount=2900&tax_free_amount=0&approval_url=http://localhost:9091&cancel_url=http://localhost:9091&fail_url=http://localhost:9091";
  
  
    formData.add("cid", "TC0ONETIME" );
    formData.add("tid", tid );
    formData.add("partner_order_id", "partner_order_id" );
    formData.add("partner_user_id", "partner_user_id" );
    formData.add("pg_token", token );
    
    
  
  
  
    List<String> res = webClient.post()
            .uri(url+"v1/payment/approve")
            .header("Authorization", "KakaoAK c92ab4879f981778f7237de24e2f169d")
            .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8")
            .bodyValue(formData)
            .retrieve()
            .bodyToFlux(String.class)
            .toStream()
            .collect(Collectors.toList());
    
    return res.get(0);
  }

}

