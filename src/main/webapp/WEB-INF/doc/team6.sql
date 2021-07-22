/**********************************/
/* Table Name: 관리자 */
/**********************************/
CREATE TABLE admin(
		adminno INTEGER(10) NOT NULL PRIMARY KEY,
		id VARCHAR(20) NOT NULL,
		name VARCHAR(300) NOT NULL,
		password VARCHAR(100) NOT NULL,
		authority VARCHAR(20) NOT NULL,
		enabled INTEGER(10) NOT NULL,
		rdate DATETIME NOT NULL
);

/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
		memberno INTEGER(10) NOT NULL PRIMARY KEY,
		id VARCHAR(20) NOT NULL,
		passwd VARCHAR(60) NOT NULL,
		mname VARCHAR(30) NOT NULL,
		email VARCHAR(50) NOT NULL,
		tel VARCHAR(14) NOT NULL,
		zipcode VARCHAR(5),
		address1 VARCHAR(80),
		address2 VARCHAR(50),
		mdate DATETIME NOT NULL,
		grade INTEGER(2) NOT NULL
);

/**********************************/
/* Table Name: 게시판 */
/**********************************/
CREATE TABLE board(
		bnum NUMERIC(7) NOT NULL PRIMARY KEY,
		btitle VARCHAR(300) NOT NULL,
		bcontent VARCHAR(5000) NOT NULL,
		recom NUMERIC(7) NOT NULL,
		cnt NUMERIC(7) NOT NULL,
		replycnt NUMERIC(7) NOT NULL,
		passwd VARCHAR(15) NOT NULL,
		word VARCHAR(300),
		rdate DATE,
		image VARCHAR(100),
		adminno NUMERIC(10),
		memberno NUMERIC(10),
  FOREIGN KEY (adminno) REFERENCES admin (adminno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

/**********************************/
/* Table Name: 공지사항 */
/**********************************/
CREATE TABLE notice(
		nnum NUMERIC(7) NOT NULL PRIMARY KEY,
		ntitle VARCHAR(300) NOT NULL,
		ncontent VARCHAR(5000) NOT NULL,
		cnt NUMERIC(7) NOT NULL,
		word VARCHAR(300),
		rdate DATE,
		image VARCHAR(100),
		adminno NUMERIC(10),
  FOREIGN KEY (adminno) REFERENCES admin (adminno)
);

/**********************************/
/* Table Name: 결제방법 */
/**********************************/
CREATE TABLE payment(
		paymentno NUMERIC(10) NOT NULL PRIMARY KEY,
		payment VARCHAR(10) NOT NULL
);

/**********************************/
/* Table Name: 이용권종류 */
/**********************************/
CREATE TABLE product(
		productno NUMERIC(10) NOT NULL PRIMARY KEY,
		name VARCHAR(20) NOT NULL,
		period NUMERIC(10) NOT NULL
);

/**********************************/
/* Table Name: 결제 */
/**********************************/
CREATE TABLE pay(
		payno NUMERIC(10) NOT NULL PRIMARY KEY,
		product_name VARCHAR(10) NOT NULL,
		day DATE NOT NULL,
		rdata DATE NOT NULL,
		productno NUMERIC(10),
		paymentno NUMERIC(10),
		memberno NUMERIC(10),
  FOREIGN KEY (productno) REFERENCES product (productno),
  FOREIGN KEY (paymentno) REFERENCES payment (paymentno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

/**********************************/
/* Table Name: 아티스트 */
/**********************************/
CREATE TABLE artist(
		artistno NUMBER(10) NOT NULL PRIMARY KEY,
		name VARCHAR2(200) NOT NULL,
		fan NUMBER(10) DEFAULT 0 NOT NULL,
		debut NUMBER(5) NOT NULL,
		seqno NUMBER(7) DEFAULT 0 NOT NULL,
		genre VARCHAR2(50) NOT NULL,
		nation VARCHAR2(50) NOT NULL,
		type VARCHAR2(50) NOT NULL,
		fname VARCHAR2(100) NOT NULL,
		fupname VARCHAR2(100) NOT NULL,
		thumb VARCHAR2(200) NOT NULL,
		fsize NUMBER(10) NOT NULL
);

/**********************************/
/* Table Name: 앨범 */
/**********************************/
CREATE TABLE album(
		albumno NUMBER(10) NOT NULL PRIMARY KEY,
		title VARCHAR2(50) NOT NULL,
		kind VARCHAR2(20) NOT NULL,
		release VARCHAR2(10) NOT NULL,
		genre VARCHAR2(10) NOT NULL,
		enter VARCHAR2(10) NOT NULL,
		likey NUMBER(10) DEFAULT 0 NOT NULL,
		intro CLOB(1000) NOT NULL,
		detail CLOB(4000) NOT NULL,
		fname VARCHAR2(100) NOT NULL,
		thumb VARCHAR2(200) NOT NULL,
		fupname VARCHAR2(100) NOT NULL,
		fsize NUMBER(10) NOT NULL,
		artistno NUMBER(10),
  FOREIGN KEY (artistno) REFERENCES artist (artistno)
);

/**********************************/
/* Table Name: 음악 */
/**********************************/
CREATE TABLE music(
		songno NUMBER(10) NOT NULL PRIMARY KEY,
		title VARCHAR2(100) NOT NULL,
		mp3 VARCHAR2(1000),
		seqno NUMBER(10) NOT NULL,
		lyrics CLOB(1000) NOT NULL,
		aritistno NUMBER(10) NOT NULL,
		playlistno NUMBER(10) NOT NULL,
		like NUMBER(10) DEFAULT 0 NOT NULL,
		albumno NUMBER(10),
		memberno NUMERIC(10),
  FOREIGN KEY (albumno) REFERENCES album (albumno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

