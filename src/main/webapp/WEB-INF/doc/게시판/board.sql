
/**********************************/
/* Table Name: 게시판 */
/**********************************/
DROP TABLE board;

CREATE TABLE board(
    bnum                              NUMBER(7)    NOT NULL    PRIMARY KEY,
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



DROP SEQUENCE board_seq;

CREATE SEQUENCE board_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
  
  

-- 등록
INSERT INTO board(bnum, btitle, bcontent, recom, cnt, replycnt, passwd, word, rdate, image, imagesaved, imagesize)
VALUES(board_seq.nextval,'게시글1', '첫번째 게시글입니다.', 0, 0, 0, '1234', '첫글, 첫번째', sysdate, 'board1.jpg', 'board1_1.jpg', 10);


-- 목록
SELECT bnum, btitle, bcontent, recom, cnt, replycnt,  rdate, memberno
FROM board
ORDER BY bnum DESC;

-- 조회
SELECT bnum, btitle, bcontent, recom, cnt, replycnt, word, rdate, image, imagesaved, imagesize
FROM board
WHERE bnum=1;

-- 수정
UPDATE board
SET btitle='게시글1 수정', bcontent='게시글1 수정합니다.', word='첫글, 첫번째, 수정', rdate=sysdate, image='board1_u.jpg', imagesaved='board1_u_1.jpg', imagesize=5000
WHERE bnum=1;

-- 삭제
DELETE FROM board
WHERE  bnum=1;


commit;


