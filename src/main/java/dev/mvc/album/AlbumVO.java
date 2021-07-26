package dev.mvc.album;

import org.springframework.web.multipart.MultipartFile;

/*
 * albumno                          NUMBER(10)     NOT NULL    PRIMARY KEY,
    title                             VARCHAR2(50)     NOT NULL,
    kind                              VARCHAR2(20)     NOT NULL,
    release                           VARCHAR2(10)     NOT NULL,
    genre                             VARCHAR2(10)     NOT NULL,
    enter                             VARCHAR2(50)     NOT NULL,
    likey                             NUMBER(10)     DEFAULT 0     NOT NULL,
    intro                             CLOB     NOT NULL,
    detail                            CLOB     NOT NULL,
    fname                             VARCHAR2(100)    NOT NULL,
    thumb                             VARCHAR2(200)    NOT NULL,
    fupname                           VARCHAR2(100)    NOT NULL,
    fsize                             NUMBER(10)     NOT NULL,  
    artistno                          NUMBER(10)     NULL, 
    word                                 VARCHAR2(300)       NULL,
  FOREIGN KEY (artistno) REFERENCES artist (artistno)
 */
public class AlbumVO {
  /** 앨범 번호 */
  private int r_artistno;
  /** 앨범 번호 */
  private String r_name;
  
  
  /** 앨범 번호 */
  private int albumno;
  /** 앨범 제목 */
  private String title;
  /** 앨범 종류 */
  private String kind;
  /** 발매일 */
  private String release;
  /** 장르 */
  private String genre;
  /** 기획사 */
  private String enter;
  /** 좋아요 */
  private int likey;
  /** 앨범 소개 */
  private String intro = "";
  /** 상세 소개 */
  private String detail = "";
  /** 앨범 이미지 */
  private String fname = "";
  /** 썸네일 */
  private String thumb = "";
  /** 이미지 업로드 */
  private String fupname = "";
  /** 파일크기 */
  private long fsize;
  /** 아티스트 번호 */
  private int artistno;
  /** 검색어 */
  private String word;
  
  private MultipartFile fnameMF;
  
  private String fsize_label;
  
  
  
  public int getR_artistno() {
    return r_artistno;
  }
  public void setR_artistno(int r_artistno) {
    this.r_artistno = r_artistno;
  }
  public String getR_name() {
    return r_name;
  }
  public void setR_name(String r_name) {
    this.r_name = r_name;
  }
  public int getAlbumno() {
    return albumno;
  }
  public void setAlbumno(int albumno) {
    this.albumno = albumno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getKind() {
    return kind;
  }
  public void setKind(String kind) {
    this.kind = kind;
  }
  public String getRelease() {
    return release;
  }
  public void setRelease(String release) {
    this.release = release;
  }
  public String getGenre() {
    return genre;
  }
  public void setGenre(String genre) {
    this.genre = genre;
  }
  public String getEnter() {
    return enter;
  }
  public void setEnter(String enter) {
    this.enter = enter;
  }
  public int getLikey() {
    return likey;
  }
  public void setLikey(int likey) {
    this.likey = likey;
  }
  public String getIntro() {
    return intro;
  }
  public void setIntro(String intro) {
    this.intro = intro;
  }
  public String getDetail() {
    return detail;
  }
  public void setDetail(String detail) {
    this.detail = detail;
  }
  public String getFname() {
    return fname;
  }
  public void setFname(String fname) {
    this.fname = fname;
  }
  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  public String getFupname() {
    return fupname;
  }
  public void setFupname(String fupname) {
    this.fupname = fupname;
  }
  public long getFsize() {
    return fsize;
  }
  public void setFsize(long fsize) {
    this.fsize = fsize;
  }
  public int getArtistno() {
    return artistno;
  }
  public void setArtistno(int artistno) {
    this.artistno = artistno;
  }
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  public MultipartFile getFnameMF() {
    return fnameMF;
  }
  public void setFnameMF(MultipartFile fnameMF) {
    this.fnameMF = fnameMF;
  }
  public String getFsize_label() {
    return fsize_label;
  }
  public void setFsize_label(String fsize_label) {
    this.fsize_label = fsize_label;
  }
  
  @Override
  public String toString() {
    return "[r_artistno=" + r_artistno + ", r_name=" + r_name + ", albumno=" + albumno + ", title=" + title
        + ", kind=" + kind + ", release=" + release + ", genre=" + genre + ", enter=" + enter + ", likey=" + likey
        + ", intro=" + intro + ", detail=" + detail + ", fname=" + fname + ", thumb=" + thumb + ", fupname=" + fupname
        + ", fsize=" + fsize + ", artistno=" + artistno + ", word=" + word + ", fnameMF=" + fnameMF + ", fsize_label="
        + fsize_label + "]";
  }
  
  
}
