/**********************************/
/* Table Name: 아티스트 */
/**********************************/
DROP TABLE artist;
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
		fsize                         		NUMBER(10)		 DEFAULT 0        NOT NULL,
        word                                 VARCHAR2(300)       NULL
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

-- Sequence 생성
DROP SEQUENCE artist_seq;

CREATE SEQUENCE artist_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지  
  
-- Create, 등록
INSERT INTO artist(artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize,word)
VALUES(artist_seq.nextval, '빅뱅', 20, 2006, 1, '랩/힙합','대한민국', '그룹', 'bigbang.jpg', 'bigbang_1.jpg','bigbang_t.jpg',1000,'빅뱅');
INSERT INTO artist(artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize,word)
VALUES(artist_seq.nextval, '버즈', 15, 2003, 2, '락/발라드', '대한민국', '그룹', 'buzz.jpg', 'buzz_1.jpg','buzz_t.jpg',2000,'버즈');
INSERT INTO artist(artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize,word)
VALUES(artist_seq.nextval, 'SG워너비', 10, 2004, 3, '발라드', '대한민국', '그룹', 'sg.jpg', 'sg_1.jpg','sg_t.jpg',1000,'');

COMMIT;

-- List, 목록
SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize,word
FROM artist
ORDER BY artistno ASC;

-- 검색 + 페이징 목록
-- step 1
SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize
FROM artist
WHERE word LIKE '%빅뱅%' OR name LIKE '%빅뱅%' OR genre LIKE '%랩/힙합%' OR nation LIKE '%대한민국%' OR type LIKE '%그룹%'
ORDER BY artistno ASC;

-- step 2
SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize, rownum as r
FROM (
          SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize
          FROM artist
          WHERE word LIKE '%빅뱅%' OR name LIKE '%빅뱅%' OR genre LIKE '%랩/힙합%' OR nation LIKE '%대한민국%' OR type LIKE '%그룹%'
          ORDER BY artistno ASC
);

-- step 3, 1 page
SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize, r
FROM (
           SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize, rownum as r
           FROM (
                     SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize
                     FROM artist
                     WHERE word LIKE '%빅뱅%' OR name LIKE '%빅뱅%' OR genre LIKE '%랩/힙합%' OR nation LIKE '%대한민국%' OR type LIKE '%그룹%'
                     ORDER BY artistno ASC
           )          
)
WHERE r >= 1 AND r <= 3;

-- step 3, 2 page
SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize, r
FROM (
           SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize, rownum as r
           FROM (
                     SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize
                     FROM artist
                     WHERE word LIKE '%빅뱅%' OR name LIKE '%빅뱅%' OR genre LIKE '%랩/힙합%' OR nation LIKE '%대한민국%' OR type LIKE '%그룹%'
                     ORDER BY artistno ASC
           )          
)
WHERE r >= 11 AND r <= 20;

-- Read, 조회
SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize,word
FROM artist
WHERE artistno = 1;

-- Update, 수정
UPDATE artist
SET name='샤이니', fan=10, seqno=1, debut=2008, genre='댄스', nation='대한민국', 
      type='그룹', fname='shinee.jpg', fupname='shinee_1.jpg', thumb='shinee_t.jpg', fsize=3000, word='샤이니'
WHERE artistno=1;

UPDATE artist
SET name='빅뱅', fan=40, seqno=1, debut=2006, genre='랩/힙합', nation='대한민국', 
      type='그룹', fname='bigbang.jpg', fupname='bigbang_1.jpg', thumb='bigbang_t.jpg', fsize=1000, word='빅뱅'
WHERE artistno=1;

COMMIT;

-- Delete, 삭제
DELETE FROM artist
WHERE artistno=3;

--INSERT INTO artist(artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize)
--VALUES(artist_seq.nextval, 'SG워너비', 10, 2004, 3, '발라드', '대한민국', '그룹', 'sg.jpg', 'sg_1.jpg','sg_t.jpg',1000);

COMMIT;

-- 레코드 갯수
SELECT COUNT(*) 
FROM artist;

-- 팬 맺기
UPDATE artist
SET fan = fan + 1
WHERE artistno = 1;
