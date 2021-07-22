package dev.mvc.artist;

import org.springframework.web.multipart.MultipartFile;

/*
 * artistno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    name                              VARCHAR2(200)    NOT NULL,
    fan                               NUMBER(10)     DEFAULT 0     NOT NULL,
    debut                             NUMBER(5)    NOT NULL,
    seqno                             NUMBER(7)    DEFAULT 0     NOT NULL,
    genre                             VARCHAR2(50)     NOT NULL,
    nation                            VARCHAR2(50)     NOT NULL,
    type                              VARCHAR2(50)     NOT NULL,
    fname                             VARCHAR2(100)    NOT NULL,
    fupname                           VARCHAR2(100)    NOT NULL,
    thumb                             VARCHAR2(200)    NOT NULL,
    fsize                             NUMBER(10)     NOT NULL,
    word                               VARCHAR2(300)      NULL
 */
public class ArtistVO {
  /** 아티스트 번호*/
  private int artistno;
  /** 아티스트 이름*/
  private String name;
  /** 아티스트 팬맺기 수 */
  private int fan;
  /** 아티스트 데뷔 년도*/
  private int debut;
  /** 아티스트 등록 순서*/
  private int seqno;
  /** 아티스트 장르*/
  private String genre = "";
  /** 아티스트 국적*/
  private String nation = "";
  /** 아티스트 활동 유형(그룹, 솔로)*/
  private String type = "";
  /** 아티스트 이미지*/
  private String fname = "";
  /** 아티스트 업로드 이미지*/
  private String fupname = "";
  /** 아티스트 썸네일*/
  private String thumb = "";
  /** 아티스트 파일크기*/
  private long fsize;
  /** 아티스트 검색어*/
  private String word = "";
  
 private MultipartFile file1MF;
  
  /**
   * 파일 크기 단위 출력
   */
  private String fsize_label;
  
  
  /*
   * public ArtistVO() {
   * 
   * }
   * 
   * 
   * public ArtistVO(int artistno, String name, int fan, int debut, int seqno,
   * String genre, String nation, String type, String fname, String fupname,
   * String thumb, int fsize) { this.artistno = artistno; this.name = name;
   * this.fan = fan; this.debut = debut; this.seqno = seqno; this.genre = genre;
   * this.nation = nation; this.type = type; this.fname = fname; this.fupname =
   * fupname; this.thumb = thumb; this.fsize = fsize; }
   */


  public int getArtistno() {
    return artistno;
  }


  public void setArtistno(int artistno) {
    this.artistno = artistno;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public int getFan() {
    return fan;
  }


  public void setFan(int fan) {
    this.fan = fan;
  }


  public int getDebut() {
    return debut;
  }


  public void setDebut(int debut) {
    this.debut = debut;
  }


  public int getSeqno() {
    return seqno;
  }


  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }


  public String getGenre() {
    return genre;
  }


  public void setGenre(String genre) {
    this.genre = genre;
  }


  public String getNation() {
    return nation;
  }


  public void setNation(String nation) {
    this.nation = nation;
  }


  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public String getFname() {
    return fname;
  }


  public void setFname(String fname) {
    this.fname = fname;
  }


  public String getFupname() {
    return fupname;
  }


  public void setFupname(String fupname) {
    this.fupname = fupname;
  }


  public String getThumb() {
    return thumb;
  }


  public void setThumb(String thumb) {
    this.thumb = thumb;
  }


  public long getFsize() {
    return fsize;
  }


  public void setFsize(long fsize) {
    this.fsize = fsize;
  }
  
  
  public MultipartFile getFile1MF() {
    return file1MF;
  }


  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }


  public String getFsize_label() {
    return fsize_label;
  }


  public void setFsize_label(String fsize_label) {
    this.fsize_label = fsize_label;
  }


  public String getWord() {
    return word;
  }


  public void setWord(String word) {
    this.word = word;
  }

  

  

  
}
