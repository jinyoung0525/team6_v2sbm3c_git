package dev.mvc.notice;

import org.springframework.web.multipart.MultipartFile;

/*
    nnum NUMBER(7) NOT NULL PRIMARY KEY,
    ntitle VARCHAR2(300) NOT NULL,
    ncontent CLOB NOT NULL,
    cnt NUMBER(7) DEFAULT 0 NOT NULL,
    word VARCHAR2(300),
    rdate DATE,
    nimage VARCHAR2(100),
    nimagesaved VARCHAR2(100),
    nsize LONG,
    ADMINNO NUMBER(10),
  FOREIGN KEY (adminno) REFERENCES ADMIN (adminno)
 */
public class NoticeVO {
  private int nnum;
  private String ntitle="";
  private String ncontent="";
  private int cnt=0;
  private String word="";
  private String rdate="";
  private String nimage="";
  private String nimagesaved="";
  private long nsize;
  private int adminno;
  
  public int getNnum() {
    return nnum;
  }
  public void setNnum(int nnum) {
    this.nnum = nnum;
  }
  public String getNtitle() {
    return ntitle;
  }
  public void setNtitle(String ntitle) {
    this.ntitle = ntitle;
  }
  public String getNcontent() {
    return ncontent;
  }
  public void setNcontent(String ncontent) {
    this.ncontent = ncontent;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getNimage() {
    return nimage;
  }
  public void setNimage(String nimage) {
    this.nimage = nimage;
  }
  public String getNimagesaved() {
    return nimagesaved;
  }
  public void setNimagesaved(String nimagesaved) {
    this.nimagesaved = nimagesaved;
  }
  public long getNsize() {
    return nsize;
  }
  public void setNsize(long nsize) {
    this.nsize = nsize;
  }
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    adminno = adminno;
  }
  

  /** 
  이미지 MultipartFile 
  <input type='file' class="form-control" name='nimage1MF' id='nimage1MF' 
                   value='' placeholder="파일 선택">
  */
  private MultipartFile nimage1MF;

  public MultipartFile getNimage1MF() {
    return nimage1MF;
  }
  public void setNimage1MF(MultipartFile nimage1mf) {
    nimage1MF = nimage1mf;
  }

  /**
   * 파일 크기 단위 출력
   */
  private String size_label;
  
  public String getSize_label() {
    return size_label;
  }
  public void setSize_label(String size_label) {
    this.size_label = size_label;
  } 
  
  
}
