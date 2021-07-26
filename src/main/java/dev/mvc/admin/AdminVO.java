package dev.mvc.admin;
 
public class AdminVO {
  /*
    adminno NUMBER(10) NOT NULL PRIMARY KEY,
    id VARCHAR(20) NOT NULL,
    name VARCHAR(300) NOT NULL,
    email VARCHAR(50) NOT NULL, -- 이메일
    password VARCHAR(100) NOT NULL,
    authority VARCHAR(20) NOT NULL, -- 권한, ROLE_ADMIN 지정됨 ★
    enabled NUMBER(10) NOT NULL, -- 사용 여부, 1: 활성, 0: 비활성 지정됨
    rdate DATE NOT NULL,
    grade NUMBER(2) NOT NULL -- 등급(1 ~ 10: 관리자 / 11~20: 회원 / 30~39: 정지 회원 / 40~49: 탈퇴 회원 / 99: 비회원)
  */
 
  /** 관리자 번호 */
  private int adminno;
  /** 아이디 */
  private String id = "";
  /** 관리자 성명 */
  private String name = "";
  /** 이메일 */
  private String email = "";
  /** 비밀번호 */
  private String password = "";
  /** 권한 */
  private String authority = "";
  /** 사용 여부 */
  private int enabled=0;
  /** 가입일 */
  private String rdate = "";
  /** 등급 */
  private int grade = 0;
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getAuthority() {
    return authority;
  }
  public void setAuthority(String authority) {
    this.authority = authority;
  }
  public int getEnabled() {
    return enabled;
  }
  public void setEnabled(int enabled) {
    this.enabled = enabled;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getGrade() {
    return grade;
  }
  public void setGrade(int grade) {
    this.grade = grade;
  }
  

  
  
  
  
  
  
 
 
}




