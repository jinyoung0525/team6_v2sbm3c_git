/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
		memberno                      		INTEGER(10)		 NOT NULL		 PRIMARY KEY,
		id                            		VARCHAR2(20)		 NOT NULL,
		passwd                        		VARCHAR2(60)		 NOT NULL,
		mname                         		VARCHAR2(30)		 NOT NULL,
		email                         		VARCHAR2(50)		 NOT NULL,
		tel                           		VARCHAR2(14)		 NOT NULL,
		zipcode                       		VARCHAR2(5)		 NULL ,
		address1                      		VARCHAR2(80)		 NULL ,
		address2                      		VARCHAR2(50)		 NULL ,
		mdate                         		DATE		 NOT NULL,
		grade                         		INTEGER(2)		 NOT NULL
);

COMMENT ON TABLE member is '회원';
COMMENT ON COLUMN member.memberno is '회원 번호';
COMMENT ON COLUMN member.id is '아이디';
COMMENT ON COLUMN member.passwd is '패스워드';
COMMENT ON COLUMN member.mname is '성명';
COMMENT ON COLUMN member.email is '이메일';
COMMENT ON COLUMN member.tel is '전화번호';
COMMENT ON COLUMN member.zipcode is '우편번호';
COMMENT ON COLUMN member.address1 is '주소1';
COMMENT ON COLUMN member.address2 is '주소2';
COMMENT ON COLUMN member.mdate is '가입일';
COMMENT ON COLUMN member.grade is '등급';


/**********************************/
/* Table Name: 게시판 */
/**********************************/
CREATE TABLE board(
		bnum                          		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		btitle                        		VARCHAR2(300)		 NOT NULL,
		bcontent                      		CLOB(5000)		 NOT NULL,
		recom                         		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		cnt                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		replycnt                      		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		passwd                        		VARCHAR2(15)		 NOT NULL,
		word                          		VARCHAR2(300)		 NULL ,
		rdate                         		DATE		 NULL ,
		image                         		VARCHAR2(100)		 NULL ,
		imagesaved                    		VARCHAR2(100)		 NULL ,
		imagesize                     		NUMBER(10)		 NULL ,
		memberno                      		INTEGER(10)		 NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE board is '게시판';
COMMENT ON COLUMN board.bnum is '글번호';
COMMENT ON COLUMN board.btitle is '글제목';
COMMENT ON COLUMN board.bcontent is '글내용';
COMMENT ON COLUMN board.recom is '추천수';
COMMENT ON COLUMN board.cnt is '조회수';
COMMENT ON COLUMN board.replycnt is '댓글수';
COMMENT ON COLUMN board.passwd is '패스워드';
COMMENT ON COLUMN board.word is '검색어';
COMMENT ON COLUMN board.rdate is '등록일';
COMMENT ON COLUMN board.image is '이미지';
COMMENT ON COLUMN board.imagesaved is '저장된이미지';
COMMENT ON COLUMN board.imagesize is '이미지크기';
COMMENT ON COLUMN board.memberno is '회원 번호';


/**********************************/
/* Table Name: 관리자 */
/**********************************/
CREATE TABLE admin(
		adminno                       		INTEGER(10)		 NOT NULL		 PRIMARY KEY,
		id                            		VARCHAR2(20)		 NOT NULL,
		name                          		VARCHAR2(300)		 NOT NULL,
		password                      		VARCHAR2(100)		 NOT NULL,
		authority                     		VARCHAR2(20)		 NOT NULL,
		enabled                       		INTEGER(10)		 NOT NULL,
		rdate                         		DATE		 NOT NULL
);

COMMENT ON TABLE admin is '관리자';
COMMENT ON COLUMN admin.adminno is '관리자 번호';
COMMENT ON COLUMN admin.id is '아이디';
COMMENT ON COLUMN admin.name is '성명';
COMMENT ON COLUMN admin.password is '패스워드';
COMMENT ON COLUMN admin.authority is '권한';
COMMENT ON COLUMN admin.enabled is '사용 여부';
COMMENT ON COLUMN admin.rdate is '등록일';


/**********************************/
/* Table Name: 공지사항 */
/**********************************/
CREATE TABLE notice(
		nnum                          		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		ntitle                        		VARCHAR2(300)		 NOT NULL,
		ncontent                      		CLOB(5000)		 NOT NULL,
		cnt                           		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		word                          		VARCHAR2(300)		 NULL ,
		rdate                         		DATE		 NULL ,
		nimage                        		VARCHAR2(100)		 NULL ,
		nimagesaved                   		NUMBER(100)		 NULL ,
		nsize                         		LONG		 NULL ,
		adminno                       		INTEGER(10)		 NULL ,
  FOREIGN KEY (adminno) REFERENCES admin (adminno)
);

COMMENT ON TABLE notice is '공지사항';
COMMENT ON COLUMN notice.nnum is '공지번호';
COMMENT ON COLUMN notice.ntitle is '공지제목';
COMMENT ON COLUMN notice.ncontent is '공지내용';
COMMENT ON COLUMN notice.cnt is '조회수';
COMMENT ON COLUMN notice.word is '검색어';
COMMENT ON COLUMN notice.rdate is '등록일';
COMMENT ON COLUMN notice.nimage is '이미지';
COMMENT ON COLUMN notice.nimagesaved is '저장된이미지';
COMMENT ON COLUMN notice.nsize is '이미지크기';
COMMENT ON COLUMN notice.adminno is '관리자 번호';


/**********************************/
/* Table Name: 결제방법 */
/**********************************/
CREATE TABLE payment(
		paymentno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		payment                       		VARCHAR2(10)		 NOT NULL
);

COMMENT ON TABLE payment is '결제방법';
COMMENT ON COLUMN payment.paymentno is '결제방법 번호';
COMMENT ON COLUMN payment.payment is '결제방법';


/**********************************/
/* Table Name: 이용권종류 */
/**********************************/
CREATE TABLE product(
		productno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(20)		 NOT NULL,
		period                        		NUMBER(10)		 NOT NULL
);

COMMENT ON TABLE product is '이용권종류';
COMMENT ON COLUMN product.productno is '이용권 번호';
COMMENT ON COLUMN product.name is '이용권 이름';
COMMENT ON COLUMN product.period is '이용권 기간';


/**********************************/
/* Table Name: 결제 */
/**********************************/
CREATE TABLE pay(
		payno                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		product_name                  		VARCHAR2(10)		 NOT NULL,
		day                           		DATE		 NOT NULL,
		rdata                         		DATE		 NOT NULL,
		productno                     		NUMBER(10)		 NULL ,
		paymentno                     		NUMBER(10)		 NULL ,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (productno) REFERENCES product (productno),
  FOREIGN KEY (paymentno) REFERENCES payment (paymentno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE pay is '결제';
COMMENT ON COLUMN pay.payno is '결제 번호';
COMMENT ON COLUMN pay.product_name is '보유한이용권';
COMMENT ON COLUMN pay.day is '남은기간';
COMMENT ON COLUMN pay.rdata is '결제일';
COMMENT ON COLUMN pay.productno is '이용권 번호';
COMMENT ON COLUMN pay.paymentno is '결제방법 번호';
COMMENT ON COLUMN pay.memberno is '회원 번호';


/**********************************/
/* Table Name: 아티스트 */
/**********************************/
CREATE TABLE artist(
		artistno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(200)		 NOT NULL,
		fan                           		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		debut                         		NUMBER(5)		 NOT NULL,
		seqno                         		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		genre                         		VARCHAR2(50)		 NOT NULL,
		nation                        		VARCHAR2(50)		 NOT NULL,
		type                          		VARCHAR2(50)		 NOT NULL,
		fname                         		VARCHAR2(100)		 NOT NULL,
		fupname                       		VARCHAR2(100)		 NOT NULL,
		thumb                         		VARCHAR2(200)		 NOT NULL,
		fsize                         		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		word                          		VARCHAR2(300)		 NULL 
);

COMMENT ON TABLE artist is '아티스트';
COMMENT ON COLUMN artist.artistno is '아티스트 번호';
COMMENT ON COLUMN artist.name is '아티스트 명';
COMMENT ON COLUMN artist.fan is '팬';
COMMENT ON COLUMN artist.debut is '데뷔';
COMMENT ON COLUMN artist.seqno is '출력순서';
COMMENT ON COLUMN artist.genre is '장르';
COMMENT ON COLUMN artist.nation is '국가';
COMMENT ON COLUMN artist.type is '유형';
COMMENT ON COLUMN artist.fname is '이미지';
COMMENT ON COLUMN artist.fupname is '업로드이미지';
COMMENT ON COLUMN artist.thumb is '썸네일';
COMMENT ON COLUMN artist.fsize is '파일크기';
COMMENT ON COLUMN artist.word is '검색어';


/**********************************/
/* Table Name: 앨범 */
/**********************************/
CREATE TABLE album(
		albumno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(50)		 NOT NULL,
		kind                          		VARCHAR2(20)		 NOT NULL,
		release                       		VARCHAR2(10)		 NOT NULL,
		genre                         		VARCHAR2(10)		 NOT NULL,
		enter                         		VARCHAR2(50)		 NOT NULL,
		likey                         		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		intro                         		CLOB(1000)		 NOT NULL,
		detail                        		CLOB(4000)		 NOT NULL,
		fname                         		VARCHAR2(100)		 NOT NULL,
		thumb                         		VARCHAR2(200)		 NOT NULL,
		fupname                       		VARCHAR2(100)		 NOT NULL,
		fsize                         		NUMBER(10)		 NOT NULL,
		artistno                      		NUMBER(10)		 NULL ,
		word                          		VARCHAR2(300)		 NULL ,
  FOREIGN KEY (artistno) REFERENCES artist (artistno)
);

COMMENT ON TABLE album is '앨범';
COMMENT ON COLUMN album.albumno is '앨범 번호';
COMMENT ON COLUMN album.title is '앨범 제목';
COMMENT ON COLUMN album.kind is '앨범 종류';
COMMENT ON COLUMN album.release is '발매일';
COMMENT ON COLUMN album.genre is '장르';
COMMENT ON COLUMN album.enter is '기획사';
COMMENT ON COLUMN album.likey is '좋아요';
COMMENT ON COLUMN album.intro is '앨범 소개';
COMMENT ON COLUMN album.detail is '상세 소개';
COMMENT ON COLUMN album.fname is '앨범이미지';
COMMENT ON COLUMN album.thumb is '썸네일';
COMMENT ON COLUMN album.fupname is '이미지업로드';
COMMENT ON COLUMN album.fsize is '파일크기';
COMMENT ON COLUMN album.artistno is '아티스트 번호';
COMMENT ON COLUMN album.word is '검색어';


/**********************************/
/* Table Name: 음악 */
/**********************************/
CREATE TABLE music(
		songno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(100)		 NOT NULL,
		mp3                           		VARCHAR2(4000)		 NOT NULL,
		mp4                           		VARCHAR2(4000)		 NOT NULL,
		seqno                         		NUMBER(10)		 NOT NULL,
		lyrics                        		CLOB(1000)		 NOT NULL,
		playlistno                    		NUMBER(10)		 NOT NULL,
		likey                         		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		albumno                       		NUMBER(10)		 NULL ,
		memberno                      		INTEGER(10)		 NULL ,
		word                          		VARCHAR2(300)		 NULL ,
  FOREIGN KEY (albumno) REFERENCES album (albumno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE music is '음악';
COMMENT ON COLUMN music.songno is '음악 번호';
COMMENT ON COLUMN music.title is '곡 이름';
COMMENT ON COLUMN music.mp3 is '음악파일';
COMMENT ON COLUMN music.mp4 is '음악파일';
COMMENT ON COLUMN music.seqno is '출력순서';
COMMENT ON COLUMN music.lyrics is '가사';
COMMENT ON COLUMN music.playlistno is '플레이리스트 번호';
COMMENT ON COLUMN music.likey is '좋아요';
COMMENT ON COLUMN music.albumno is '앨범 번호';
COMMENT ON COLUMN music.memberno is '회원 번호';
COMMENT ON COLUMN music.word is '검색어';


