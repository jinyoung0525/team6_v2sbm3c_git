
/**********************************/
/* Table Name: 공지사항 */
/**********************************/
DROP TABLE notice;
CREATE TABLE notice(
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
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
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
COMMENT ON COLUMN notice.ADMINNO is '관리자 번호';


DROP SEQUENCE notice_seq;

CREATE SEQUENCE notice_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
  
  
  
-- 등록
INSERT INTO notice(nnum, ntitle, ncontent, cnt, word, rdate, nimage, nimagesaved, nsize)
VALUES(notice_seq.nextval,'공지1', '첫번째 공지입니다.', 0, '공지1, 첫공지', sysdate, 'notice1.jpg', 'notice1_1.jpg', 1000);

-- 목록
SELECT nnum, ntitle, rdate, cnt, ADMINNO
FROM notice
ORDER BY nnum DESC;

-- 조회
SELECT nnum, ntitle, ncontent, cnt, word, rdate, nimage, nimagesaved, nsize
FROM notice
WHERE nnum=2;




