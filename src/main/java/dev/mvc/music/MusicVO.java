package dev.mvc.music;

import org.springframework.web.multipart.MultipartFile;

/*
 *  songno                  NUMBER(10) NOT NULL PRIMARY KEY,
    title                       VARCHAR2(100) NOT NULL,
    mp3                      VARCHAR(4000) NULL,
    mp4                      VARCHAR(4000) NULL,
        size3                      NUMBER(10) NULL,
        size4                      NUMBER(10) NULL,
    seqno                    NUMBER(10) NOT NULL,
    lyrics                      CLOB  NOT NULL,
    likey                      NUMBER(10) DEFAULT 0 NOT NULL,
    albumno                 NUMBER(10) NULL,
    memberno               NUMBER(10) NULL,
        word                      VARCHAR2(300)       NULL,                      
  FOREIGN KEY (albumno) REFERENCES album (albumno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
 */
public class MusicVO {
  private int r_albumno;
  
  private String r_title;

  /** 음악 번호 */
  private int songno;
  /** 곡 이름 */
  private String title;
  /** 음악 파일 */
  private String mp3="";
  /** 뮤비 파일 */
  private String mp4="";
  /** 음악 파일크기 */
  private long size3;
  /** 뮤비 파일크기 */
  private long size4;
  /** 출력 순서 */
  private int seqno;
  /** 가사 */
  private String lyrics;
  /** 좋아요 */
  private int likey;
  /** 앨범 번호 */
  private int albumno;
  /** 회원 번호 */
  private int memberno;
  /** 검색어 */
  private String word;
  
  private MultipartFile mp3MF;
  
  private String size3_label;
  
  private MultipartFile mp4MF;
  
  private String size4_label;

  public int getSongno() {
    return songno;
  }

  public void setSongno(int songno) {
    this.songno = songno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMp3() {
    return mp3;
  }

  public void setMp3(String mp3) {
    this.mp3 = mp3;
  }

  public String getMp4() {
    return mp4;
  }

  public void setMp4(String mp4) {
    this.mp4 = mp4;
  }

  public long getSize3() {
    return size3;
  }

  public void setSize3(long size3) {
    this.size3 = size3;
  }

  public long getSize4() {
    return size4;
  }

  public void setSize4(long size4) {
    this.size4 = size4;
  }

  public int getSeqno() {
    return seqno;
  }

  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }

  public String getLyrics() {
    return lyrics;
  }

  public void setLyrics(String lyrics) {
    this.lyrics = lyrics;
  }

  public int getLikey() {
    return likey;
  }

  public void setLikey(int likey) {
    this.likey = likey;
  }

  public int getAlbumno() {
    return albumno;
  }

  public void setAlbumno(int albumno) {
    this.albumno = albumno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public MultipartFile getMp3MF() {
    return mp3MF;
  }

  public void setMp3MF(MultipartFile mp3mf) {
    mp3MF = mp3mf;
  }

  public String getSize3_label() {
    return size3_label;
  }

  public void setSize3_label(String size3_label) {
    this.size3_label = size3_label;
  }

  public MultipartFile getMp4MF() {
    return mp4MF;
  }

  public void setMp4MF(MultipartFile mp4mf) {
    mp4MF = mp4mf;
  }

  public String getSize4_label() {
    return size4_label;
  }

  public void setSize4_label(String size4_label) {
    this.size4_label = size4_label;
  }
  
  public int getR_albumno() {
    return r_albumno;
  }

  public void setR_albumno(int r_albumno) {
    this.r_albumno = r_albumno;
  }

  public String getR_title() {
    return r_title;
  }

  public void setR_title(String r_title) {
    this.r_title = r_title;
  }

  @Override
  public String toString() {
    return "[r_albumno=" + r_albumno + ", r_title=" + r_title + ", songno=" + songno + ", title=" + title
        + ", mp3=" + mp3 + ", mp4=" + mp4 + ", size3=" + size3 + ", size4=" + size4 + ", seqno=" + seqno + ", lyrics="
        + lyrics + ", likey=" + likey + ", albumno=" + albumno + ", memberno=" + memberno + ", word=" + word
        + ", mp3MF=" + mp3MF + ", size3_label=" + size3_label + ", mp4MF=" + mp4MF + ", size4_label=" + size4_label
        + "]";
  }
  
  
  
  
}
