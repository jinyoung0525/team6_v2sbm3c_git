/**********************************/
/* Table Name: 음악 */
/**********************************/
DROP TABLE music;
CREATE TABLE music(
		songno                  NUMBER(10) NOT NULL PRIMARY KEY,
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
);

COMMENT ON TABLE music is '음악';
COMMENT ON COLUMN music.songno is '음악 번호';
COMMENT ON COLUMN music.title is '곡 이름';
COMMENT ON COLUMN music.mp3 is '음악파일';
COMMENT ON COLUMN music.mp4 is '뮤비파일';
COMMENT ON COLUMN music.size3 is '음악파일크기';
COMMENT ON COLUMN music.size4 is '뮤비파일크기';
COMMENT ON COLUMN music.seqno is '출력순서';
COMMENT ON COLUMN music.lyrics is '가사';
COMMENT ON COLUMN music.likey is '좋아요';
COMMENT ON COLUMN music.albumno is '앨범 번호';
COMMENT ON COLUMN music.memberno is '회원 번호';
COMMENT ON COLUMN music.word is '검색어';

DROP SEQUENCE music_seq;

CREATE SEQUENCE music_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지  
  
-- Create, 등록
INSERT INTO music(songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno, word)
VALUES(music_seq.nextval, 'LOSER', 'LOSER.mp3', 'LOSER.mp4', 1, 1, 1,
'LOSER 외톨이 센 척하는 겁쟁이 못된 양아치 거울 속에 넌 JUST A LOSER 외톨이 상처뿐인 머저리 더러운 쓰레기  거울 속에 난 IM A',
0, 11, 1, 'loser');

INSERT INTO music(songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno, word)
VALUES(music_seq.nextval, 'BAE BAE', 'BAE BAE.mp3', 'BAE BAE.mp4', 1, 1, 2,
'찹쌀떡 찹쌀떡 궁합이 우리 우리 궁합이 찹쌀떡 찹쌀떡 궁합이 우리 우리 궁합이 접기',
0, 11, 1, 'bae bae');

commit;

-- 목록
SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno, word
FROM music
ORDER BY songno ASC;

-- 검색 + 페이징 목록
-- step 1
SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno
FROM music
WHERE word LIKE '%LOSER%' OR title LIKE '%LOSER%' OR lyrics LIKE '%외톨이%'
ORDER BY songno ASC;

-- step 2
SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno, rownum as r
FROM (
          SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno
          FROM music
          WHERE word LIKE '%LOSER%' OR title LIKE '%LOSER%' OR lyrics LIKE '%외톨이%'
          ORDER BY songno ASC
);

-- step 3, 1 page
SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno, r
FROM (
           SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno, rownum as r
           FROM (
                     SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno
                     FROM music
                      WHERE word LIKE '%LOSER%' OR title LIKE '%LOSER%' OR lyrics LIKE '%외톨이%'
                      ORDER BY songno ASC
           )          
)
WHERE r >= 1 AND r <= 3;

-- step 3, 2 page
SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno, r
FROM (
           SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno, rownum as r
           FROM (
                     SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno
                     FROM music
                      WHERE word LIKE '%LOSER%' OR title LIKE '%LOSER%' OR lyrics LIKE '%외톨이%'
                      ORDER BY songno ASC
           )          
)
WHERE r >= 11 AND r <= 20;

-- albumno 별 목록
SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno
FROM music
WHERE albumno = 1
ORDER BY songno ASC;

-- Categrp + Cate join, 연결 목록
SELECT albumno, title, kind, release, genre, enter, likey, intro, detail, fname, thumb, fupname, fsize,artistno, word
FROM album
ORDER BY albumno ASC;

-- 부모 테이블의 PK 컬럼, 자식 테이블의 FK 컬럼의 값이 같으면 하나의 레코드로 결합
-- 결합시 자식 테이블의 레코드 갯수 만큼 결합(join)이 발생함.
-- as로 컬럼 별명을 선언하면 실제 컬럼명은 사용 못함.
SELECT r.albumno as r_albumno, r.title as r_title,
           c.songno, c.title, c.mp3, c.mp4, c.size3, c.size4, c.seqno, c.lyrics, c.likey, c.albumno, c.memberno
FROM album r, music c
WHERE r.albumno = c.albumno
ORDER BY albumno ASC, songno ASC;

-- 조회
SELECT songno, title, mp3, mp4, size3, size4, seqno, lyrics, likey, albumno, memberno
FROM music
WHERE songno=1;

-- 수정
UPDATE music
SET songno=1, title='뱅뱅뱅', mp3='bang.mp3', mp4='bang.mp4', size3=1, size4=1, lyrics='뱅뱅뱅', word='뱅뱅뱅'
WHERE songno = 1;

UPDATE music
SET songno=1, title='LOSER', mp3='LOSER.mp3', mp4='LOSER.mp4', size3=1, size4=1, lyrics='LOSER 외톨이 센 척하는 겁쟁이 못된 양아치 거울 속에 넌 JUST A LOSER 외톨이 상처뿐인 머저리 더러운 쓰레기  거울 속에 난 IM A', word='loser'
WHERE songno = 1;

SELECT * FROM music;

commit;

-- 삭제
DELETE music
WHERE songno = 2;

SELECT * FROM music;

commit;

-- 갯수
SELECT COUNT(*) as cnt 
FROM music;

-- 특정 그룹에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM music
WHERE albumno=1;

-- 특정 그룹에 속한 레코드 모두 삭제
DELETE FROM music
WHERE albumno=1;

-- 추천수
UPDATE music
SET likey = likey + 1
WHERE songno = 1




