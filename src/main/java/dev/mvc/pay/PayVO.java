package dev.mvc.pay;

import org.springframework.web.multipart.MultipartFile;


public class PayVO {
  /** 상품 번호 */
  private int pay_no;
  /** 상품 이름 */
  private String pay_name= "";
  /** 듣기횟수 */
  private int pay_count;
  /** 이용 기간 */
  private int pay_day;
  /** 결제일  */
  private String rdata = "";
  /** 결제방법 */
  private String payment = "";
  /** 결제금액 */
  private int price;
  /** 결제고유번호 */
  private String tid = "";
  /** 결제 수단 */
  private String payment_method_type = "";
  /** 결제 요청 시각 */
  private String created_at = "";
  /** 결제 승인 시각 */
  private String approved_at = "";

  /** 회원 번호 */
  private int memberno;

  
  
 
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getPay_no() {
    return pay_no;
  }
  public void setPay_no(int pay_no) {
    this.pay_no = pay_no;
  }
  public String getPay_name() {
    return pay_name;
  }
  public void setPay_name(String pay_name) {
    this.pay_name = pay_name;
  }
  public int getPay_count() {
    return pay_count;
  }
  public void setPay_count(int pay_count) {
    this.pay_count = pay_count;
  }
  public int getPay_day() {
    return pay_day;
  }
  public void setPay_day(int pay_day) {
    this.pay_day = pay_day;
  }
  public String getRdata() {
    return rdata;
  }
  public void setRdata(String rdata) {
    this.rdata = rdata;
  }
  public String getPayment() {
    return payment;
  }
  public void setPayment(String payment) {
    this.payment = payment;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public String getTid() {
    return tid;
  }
  public void setTid(String tid) {
    this.tid = tid;
  }
  public String getPayment_method_type() {
    return payment_method_type;
  }
  public void setPayment_method_type(String payment_method_type) {
    this.payment_method_type = payment_method_type;
  }
  public String getCreated_at() {
    return created_at;
  }
  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }
  public String getApproved_a() {
    return approved_at;
  }
  public void setApproved_a(String approved_at) {
    this.approved_at = approved_at;
  }
}
  


