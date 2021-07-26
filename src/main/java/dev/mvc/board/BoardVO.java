package dev.mvc.board;
import org.springframework.web.multipart.MultipartFile;
  /*
   * bnum                              NUMBER(7)    NOT NULL    PRIMARY KEY,
    btitle                            VARCHAR2(300)    NOT NULL,
    bcontent                          CLOB     NOT NULL,
    recom                             NUMBER(7)    DEFAULT 0     NOT NULL,
    cnt                               NUMBER(7)    DEFAULT 0     NOT NULL,
    replycnt                          NUMBER(7)    DEFAULT 0     NOT NULL,
    passwd                            VARCHAR2(15)     NOT NULL,
    word                              VARCHAR2(300)    NULL ,
    rdate                             DATE     NULL ,
    image                             VARCHAR2(100)    NULL ,
    imagesaved                        VARCHAR2(100)    NULL ,
    imagesize                         NUMBER(10)     NULL ,
    memberno                          NUMBER(10)     NULL ,
  */

public class BoardVO {

  private int bnum;
  private String btitle="";
  private String bcontent="";
  private int recom=0;
  private int cnt=0;
  private int replycnt=0;
  private String passwd = "";
  private String word="";
  private String rdate="";
  private String image="";
  private String imagesaved="";


  private long imagesize;
  private int memberno;
  
  public int getBnum() {
    return bnum;
  }
  public void setBnum(int bnum) {
    this.bnum = bnum;
  }
  public String getBtitle() {
    return btitle;
  }
  public void setBtitle(String btitle) {
    this.btitle = btitle;
  }
  public String getBcontent() {
    return bcontent;
  }
  public void setBcontent(String bcontent) {
    this.bcontent = bcontent;
  }
  public int getRecom() {
    return recom;
  }
  public void setRecom(int recom) {
    this.recom = recom;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public int getReplycnt() {
    return replycnt;
  }
  public void setReplycnt(int replycnt) {
    this.replycnt = replycnt;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
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
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }
  public String getImagesaved() {
    return imagesaved;
  }
  public void setImagesaved(String imagesaved) {
    this.imagesaved = imagesaved;
  }

  public long getImagesize() {
    return imagesize;
  }
  public void setImagesize(long imagesize) {
    this.imagesize = imagesize;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  
  /** 
  이미지 MultipartFile 
  <input type='file' class="form-control" name='image1MF' id='image1MF' 
                   value='' placeholder="파일 선택">
  */
  private MultipartFile image1MF;

  public MultipartFile getImage1MF() {
    return image1MF;
  }
  public void setImage1MF(MultipartFile image1mf) {
    image1MF = image1mf;
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
