/**********************************/
/* Table Name: 관리자 */
/**********************************/
CREATE TABLE admin(
    adminno INTEGER(10) NOT NULL PRIMARY KEY,
    id VARCHAR(20) NOT NULL,
    name VARCHAR(300) NOT NULL,
    password VARCHAR(100) NOT NULL,
    authority VARCHAR(20) NOT NULL, -- 권한, ROLE_ADMIN, ROLE_USER 지정됨 ★
    enabled NUMBER(10) NOT NULL, -- 사용 여부, 1: 활성, 0: 비활성 지정됨
    rdate DATE NOT NULL
);

COMMENT ON TABLE admin is '관리자';
COMMENT ON COLUMN admin.adminno is '관리자 번호';
COMMENT ON COLUMN admin.id is '아이디';
COMMENT ON COLUMN admin.name is '성명';
COMMENT ON COLUMN admin.email is '이메일, 증복 안됨, 레코드를 구분';
COMMENT ON COLUMN admin.password is '패스워드';
COMMENT ON COLUMN admin.authority is '권한, ROLE_ADMIN, ROLE_USER 지정됨';
COMMENT ON COLUMN admin.enabled is '사용 여부, 1: 활성, 0: 비활성 지정됨';
COMMENT ON COLUMN admin.rdate is '등록일';

DROP SEQUENCE admin_seq;
CREATE SEQUENCE admin_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

-- 1. 등록
-- 1234 암호화: $2a$10$AVq05lsMMJbO7jBJMUCjo.VAQlWRnSLt5VUhhR5.EHPoS5CvYNB5W
INSERT INTO admin(adminno, id, name, email, password, authority, enabled, rdate)
VALUES(admin_seq.nextval, 'admin1', '김태리', 'admin1@email', '$2a$10$AVq05lsMMJbO7jBJMUCjo.VAQlWRnSLt5VUhhR5.EHPoS5CvYNB5W', 'ROLE_USER', 1, sysdate);
INSERT INTO admin(adminno, id, name, email, password, authority, enabled, rdate)
VALUES(admin_seq.nextval, 'admin2', '이병헌', 'admin2@email', '$2a$10$AVq05lsMMJbO7jBJMUCjo.VAQlWRnSLt5VUhhR5.EHPoS5CvYNB5W', 'ROLE_USER', 1, sysdate);
INSERT INTO admin(adminno, id, name, email, password, authority, enabled, rdate)
VALUES(admin_seq.nextval, 'admin3', '변요한', 'admin3@email', '$2a$10$AVq05lsMMJbO7jBJMUCjo.VAQlWRnSLt5VUhhR5.EHPoS5CvYNB5W', 'ROLE_USER', 1, sysdate);

-- 2. 목록
SELECT adminno, id, name, email, password, authority, enabled, rdate
FROM admin
ORDER BY adminno ASC;

-- 3. 조회
SELECT adminno, id, name, email, password, authority, enabled, rdate
FROM admin
WHERE adminno = 1;

-- Spring security는 기본적으로 사용자 이름(id)을 username, 패스워드를 password 컬럼을 이용함
-- 로그인처리
SELECT id as username, password, enabled
FROM admin
WHERE id = 'admin1';

-- 권한 로딩
SELECT id as username, authority
FROM admin
WHERE id = 'admin1';

-- 4. 삭제
DELETE FROM admin;

COMMIT;
  













