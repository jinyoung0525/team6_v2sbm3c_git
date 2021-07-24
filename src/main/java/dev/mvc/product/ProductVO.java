package dev.mvc.product;

import org.springframework.web.multipart.MultipartFile;


public class ProductVO {
  /** 상품 번호 */
  private int product_no;
  /** 상품 이름 */
  private String product_name= "";
  /** 듣기횟수 */
  private int product_count;
  /** 이용 기간 */
  private int product_day;
  /** 상품 설명  */
  private String product_cont = "";
  
  /** 메인 이미지 */
  private String file1 = "";
  /** 실제 저장된 메인 이미지
  private String file1saved = ""; */  
  /** 메인 이미지 preview 
  private String thumb1 = ""; */
  /** 메인 이미지 크기 
  private long size1; */
  /** 정가 */
  private int product_price;

  public int getProduct_count() {
    return product_count;
  }

  public void setProduct_count(int product_count) {
    this.product_count = product_count;
  }

  public int getProduct_day() {
    return product_day;
  }

  public void setProduct_day(int product_day) {
    this.product_day = product_day;
  }

  public int getProduct_no() {
    return product_no;
  }

  /** 등록 날짜 */
  private String rdate = "";
 
  /** 
  이미지 MultipartFile 
  <input type='file' class="form-control" name='file1MF' id='file1MF' 
                   value='' placeholder="파일 선택">
  */
  private MultipartFile file1MF;
  
  /**
   * 파일 크기 단위 출력
   */
  private String size1_label;

public int getProductno() {
	return product_no;
}

public void setProduct_no(int product_no) {
	this.product_no = product_no;
}

public String getProduct_name() {
	return product_name;
}

public void setProduct_name(String product_name) {
	this.product_name = product_name;
}

public String getProduct_cont() {
	return product_cont;
}

public void setProduct_cont(String product_cont) {
	this.product_cont = product_cont;
}



public String getFile1() {
	return file1;
}

public void setFile1(String file1) {
	this.file1 = file1;
}
/*
public String getFile1saved() {
	return file1saved;
}

public void setFile1saved(String file1saved) {
	this.file1saved = file1saved;
}
/*
public String getThumb1() {
	return thumb1;
}

public void setThumb1(String thumb1) {
	this.thumb1 = thumb1;
}

public long getSize1() {
	return size1;
}

public void setSize1(long size1) {
	this.size1 = size1;
}
*/


public int getproduct_price() {
	return product_price;
}

public void setproduct_price(int product_price) {
	this.product_price = product_price;
}

public String getRdate() {
	return rdate;
}

public void setRdate(String rdate) {
	this.rdate = rdate;
}


public MultipartFile getFile1MF() {
	return file1MF;
}

public void setFile1MF(MultipartFile file1mf) {
	file1MF = file1mf;
}

public String getSize1_label() {
	return size1_label;
}

public void setSize1_label(String size1_label) {
	this.size1_label = size1_label;
}
    

}


