/**********************************/
/* Table Name: 회원 */
/**********************************/
DROP TABLE member;
CREATE TABLE member(
    memberno NUMBER(10) NOT NULL PRIMARY KEY, -- 회원 번호, 레코드를 구분하는 컬럼 
    id VARCHAR(20) NOT NULL UNIQUE, -- 아이디, 중복 안됨, 레코드를 구분 
    passwd VARCHAR(60) NOT NULL, -- 패스워드
    mname VARCHAR(30) NOT NULL, -- 성명, 한글 10자 저장 가능
    email VARCHAR(50) NOT NULL, -- 이메일
    tel VARCHAR(14) NOT NULL, -- 전화번호
    zipcode VARCHAR(5), -- 우편번호, 12345
    address1 VARCHAR(80), -- 주소 1
    address2 VARCHAR(50), -- 주소 2
    mdate DATE NOT NULL, -- 가입일    
    grade NUMBER(2) NOT NULL -- 등급(1 ~ 10: 관리자 / 11~20: 회원 / 30~39: 정지 회원 / 40~49: 탈퇴 회원 / 99: 비회원)
);

COMMENT ON TABLE MEMBER is '회원';
COMMENT ON COLUMN MEMBER.memberno is '회원 번호';
COMMENT ON COLUMN MEMBER.id is '아이디';
COMMENT ON COLUMN MEMBER.passwd is '패스워드';
COMMENT ON COLUMN MEMBER.mname is '성명';
COMMENT ON COLUMN MEMBER.email is '이메일';
COMMENT ON COLUMN MEMBER.tel is '전화번호';
COMMENT ON COLUMN MEMBER.zipcode is '우편번호';
COMMENT ON COLUMN MEMBER.address1 is '주소1';
COMMENT ON COLUMN MEMBER.address2 is '주소2';
COMMENT ON COLUMN MEMBER.mdate is '가입일';
COMMENT ON COLUMN MEMBER.grade is '등급';

DROP SEQUENCE member_seq;
CREATE SEQUENCE member_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- 1. 등록
-- 1) id 중복 확인(null 값을 가지고 있으면 count에서 제외됨)
SELECT COUNT(id) as cnt
FROM member
WHERE id='user1';

 cnt
 ---
   0   ← 중복 되지 않음.
   
-- 개인 회원 테스트 계정
INSERT INTO member(memberno, id, passwd, mname, email, tel, zipcode, address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'user1', '1234', '왕눈이', 'user1@email', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);
 
INSERT INTO member(memberno, id, passwd, mname, email, tel, zipcode, address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'user2', '1234', '아로미', 'user1@email', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);
 
INSERT INTO member(memberno, id, passwd, mname, email, tel, zipcode, address1, address2, mdate, grade)
VALUES (member_seq.nextval, 'user3', '1234', '투투투', 'user1@email', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);

-- 2. 목록
-- 검색을 하지 않는 경우, 전체 목록 출력
SELECT memberno, id, passwd, mname, email, tel, zipcode, address1, address2, mdate, grade
FROM member
ORDER BY memberno ASC;

-- 3. 조회
SELECT memberno, id, passwd, mname, email, tel, zipcode, address1, address2, mdate, grade
FROM member
WHERE memberno = 1;

SELECT memberno, id, passwd, mname, email, tel, zipcode, address1, address2, mdate, grade
FROM member
WHERE id = 'user2';

-- 4. 수정
UPDATE member 
SET mname='유재석', tel='111-1111-1111', zipcode='00000', address1='경기도', address2='파주시'
WHERE memberno=1;

-- 5. 삭제
-- 1) 모두 삭제
DELETE FROM member;
 
-- 2) 특정 회원 삭제
DELETE FROM member
WHERE memberno=15;

-- 6. 패스워드 변경
-- 1) 패스워드 검사
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberno=1 AND passwd='1234';
 
-- 2) 패스워드 수정
UPDATE member
SET passwd='0000'
WHERE memberno=1;

-- 7. 로그인
SELECT COUNT(memberno) as cnt
FROM member
WHERE id='user1' AND passwd='1234';

-- id를 이용한 회원 정보 조회
SELECT memberno, id, passwd, mname, email, tel, zipcode, address1, address2, mdate, grade
FROM member
WHERE id = 'user1';

commit;











