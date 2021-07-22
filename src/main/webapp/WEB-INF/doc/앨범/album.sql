/**********************************/
/* Table Name: 앨범 */
/**********************************/
DROP TABLE album;
CREATE TABLE album(
		albumno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(50)		 NOT NULL,
		kind                          		VARCHAR2(20)		 NOT NULL,
		release                       		VARCHAR2(10)		 NOT NULL,
		genre                         		VARCHAR2(10)		 NOT NULL,
		enter                         		VARCHAR2(50)		 NOT NULL,
		likey                         		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		intro                         		CLOB 		 NOT NULL,
		detail                        		CLOB 		 NOT NULL,
		fname                         		VARCHAR2(100)		 NOT NULL,
		thumb                         		VARCHAR2(200)		 NOT NULL,
		fupname                       		VARCHAR2(100)		 NOT NULL,
		fsize                         		NUMBER(10)		 NOT NULL,	
        artistno                      		NUMBER(10)		 NULL, 
        word                                 VARCHAR2(300)       NULL,
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

DROP SEQUENCE album_seq;

CREATE SEQUENCE album_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지  
 
-- 등록 
SET DEFINE OFF;

INSERT INTO album(albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno, word)
VALUES(album_seq.nextval, 'M', '싱글', '2015.05.01', '랩/힙합', '(주)YG엔터테인먼트', 0, '1157일만의 완전체 컴백 MADE 시리즈와 함께 돌아온 BIGBANG', 
'2012년 ALIVE 발매 후 1157일이 지난 2015년 5월1일, MADE의 첫번째 시리즈 [M] 과 함께 빅뱅이 우리 곁에 돌아온다.', 'M.jpg', 'M_t.jpg', 'M_1.jpg', 1000, 1,'M');

INSERT INTO album(albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno, word)
VALUES(album_seq.nextval, 'Perfect', '정규', '2006.04.24', '록/메탈', '예전미디어', 0, '2006 MelOn AWARD 후보작 !! 이 세상 최고의 밴드 버즈, 팬들 곁으로 돌아오다!', 
'전작인 2집 앨범 ‘BUZZ effect’ 에서는 느낄 수 없었던 성숙미와 농밀해진 기량으로 버즈만의 개성을 간직한 [Perfect] 앨범.', 'perfect.jpg', 'perfect_t.jpg', 'perfect_1.jpg', 1000, 2,'perfect');

INSERT INTO album(albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno,word)
VALUES(album_seq.nextval, 'The 3rd Masterpiece', '정규', '2006.04.08', 'R&B/Soul', '(주)다날엔터테인먼트', 0, '1집, 2집에서 큰 성공을 거둔 SG 워너비의 3집 앨범', 
'2006년 단일 음반으로는 가장 많은 판매량을 기록했으며 골든디스크 시상식에서 디지털음원대상을 수상한 앨범이다.', 'sg3rd.jpg', 'sg3rd_t.jpg', 'sg3rd_1.jpg', 1000, 3,'3rd');

COMMIT;

-- 목록
SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno, word
FROM album
ORDER BY albumno ASC;

-- 검색 + 페이징 목록
-- step 1
SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno
FROM album
WHERE word LIKE '%M%' OR title LIKE '%M%' OR kind LIKE '%싱글%' OR release LIKE '%2006%' 
            OR genre LIKE '%랩/힙합%' OR enter LIKE '%YG%' OR intro LIKE '%빅뱅%' OR detail LIKE '%빅뱅%'
ORDER BY albumno ASC;

-- step 2
SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno, rownum as r
FROM (
          SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno
          FROM album
          WHERE word LIKE '%M%' OR title LIKE '%M%' OR kind LIKE '%싱글%' OR release LIKE '%2006%' 
                     OR genre LIKE '%랩/힙합%' OR enter LIKE '%YG%' OR intro LIKE '%빅뱅%' OR detail LIKE '%빅뱅%'
          ORDER BY albumno ASC
);

-- step 3, 1 page
SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno, r
FROM (
           SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno, rownum as r
           FROM (
                     SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno
                     FROM album
                      WHERE word LIKE '%M%' OR title LIKE '%M%' OR kind LIKE '%싱글%' OR release LIKE '%2006%' 
                                OR genre LIKE '%랩/힙합%' OR enter LIKE '%YG%' OR intro LIKE '%빅뱅%' OR detail LIKE '%빅뱅%'
                      ORDER BY albumno ASC
           )          
)
WHERE r >= 1 AND r <= 3;

-- step 3, 2 page
SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno, r
FROM (
           SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno, rownum as r
           FROM (
                     SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno
                     FROM album
                      WHERE word LIKE '%M%' OR title LIKE '%M%' OR kind LIKE '%싱글%' OR release LIKE '%2006%' 
                                OR genre LIKE '%랩/힙합%' OR enter LIKE '%YG%' OR intro LIKE '%빅뱅%' OR detail LIKE '%빅뱅%'
                      ORDER BY albumno ASC
           )          
)
WHERE r >= 11 AND r <= 20;

-- artistno 별 목록
SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize, artistno
FROM album
WHERE artistno = 1
ORDER BY albumno ASC;

-- Categrp + Cate join, 연결 목록
SELECT artistno, name, fan, debut, seqno, genre, nation, type, fname, fupname, thumb, fsize
FROM artist
ORDER BY artistno ASC;

-- 부모 테이블의 PK 컬럼, 자식 테이블의 FK 컬럼의 값이 같으면 하나의 레코드로 결합
-- 결합시 자식 테이블의 레코드 갯수 만큼 결합(join)이 발생함.
-- as로 컬럼 별명을 선언하면 실제 컬럼명은 사용 못함.
SELECT r.artistno as r_artistno, r.name as r_name,
           c.albumno, c.artistno, c.title, c.kind, c.release, c.genre, c.enter, c.likey, c.intro, c.detail, c.fname, c.thumb, c.fupname, c.fsize
FROM artist r, album c
WHERE r.artistno = c.artistno
ORDER BY artistno ASC, albumno ASC;

-- 조회
SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno
FROM album
WHERE albumno=1;

-- 수정
UPDATE album
SET artistno=1, title='A', kind='싱글', release='2015.06.01', genre='랩/힙합', enter='(주)YG엔터테인먼트', 
intro='BIGBANG의 경쟁자 BIGBANG이 돌아온다. M을 대적할 또 다른 BIGBANG, A',
detail='2015년 6월 1일. 전편만 한 후속편은 없다는 말을 무색하게 하듯 M의 열기를 이어받아 더욱 뜨거워진 MADE SERIES의 두 번째 프로젝트 A가 공개된다.',
fname='a.jpg', thumb='a_t.jpg', fupname='a_1.jpg', fsize=3000
WHERE albumno = 3;

UPDATE album
SET artistno=3, title='The 3rd Masterpiece', kind='정규', release='2006.04.08', genre='R&B/Soul', enter='(주)다날엔터테인먼트', 
intro='1집, 2집에서 큰 성공을 거둔 SG 워너비의 3집 앨범',
detail='2006년 단일 음반으로는 가장 많은 판매량을 기록했으며 골든디스크 시상식에서 디지털음원대상을 수상한 앨범이다.',
fname='sg3rd.jpg', thumb='sg3rd_t.jpg', fupname='sg3rd_1.jpg', fsize=3000
WHERE albumno = 3;

SELECT * FROM album;

commit;

-- 삭제
DELETE album
WHERE albumno = 3;

SELECT * FROM cate;

commit;

-- 갯수
SELECT COUNT(*) as cnt 
FROM album;

-- 특정 그룹에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM album
WHERE artistno=1;

-- 특정 그룹에 속한 레코드 모두 삭제
DELETE FROM album
WHERE artistno=1;

-- 추천수
UPDATE album
SET likey = likey + 1
WHERE albumno = 1
