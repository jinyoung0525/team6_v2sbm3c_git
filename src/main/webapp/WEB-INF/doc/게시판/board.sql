
/**********************************/
/* Table Name: �Խ��� */
/**********************************/
DROP TABLE board;

CREATE TABLE board(
    bnum NUMBER(7) NOT NULL PRIMARY KEY,
    btitle VARCHAR2(300) NOT NULL,
    bcontent CLOB NOT NULL,
    recom NUMBER(7) DEFAULT 0 NOT NULL,
    cnt NUMBER(7) DEFAULT 0 NOT NULL,
    replycnt NUMBER(7) DEFAULT 0 NOT NULL,
    passwd VARCHAR2(15) NOT NULL,
    word VARCHAR2(300) NULL,
    rdate DATE NOT NULL,
    image VARCHAR2(100) NULL,
    imagesaved VARCHAR2(100) NULL,
    size NUMBER(10) NULL,
    MEMBERNO NUMBER(10) NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES member (MEMBERNO)
);


COMMENT ON TABLE board is '�Խ���';
COMMENT ON COLUMN board.bnum is '�۹�ȣ';
COMMENT ON COLUMN board.btitle is '������';
COMMENT ON COLUMN board.bcontent is '�۳���';
COMMENT ON COLUMN board.recom is '��õ��';
COMMENT ON COLUMN board.cnt is '��ȸ��';
COMMENT ON COLUMN board.replycnt is '��ۼ�';
COMMENT ON COLUMN board.passwd is '�н�����';
COMMENT ON COLUMN board.word is '�˻���';
COMMENT ON COLUMN board.rdate is '�����';
COMMENT ON COLUMN board.image is '�̹���';
COMMENT ON COLUMN board.imagesaved is '������̹���';
COMMENT ON COLUMN board.size is '�̹���ũ��';
COMMENT ON COLUMN board.MEMBERNO is 'ȸ�� ��ȣ';


DROP SEQUENCE board_seq;

CREATE SEQUENCE board_seq
  START WITH 1                -- ���� ��ȣ
  INCREMENT BY 1            -- ������
  MAXVALUE 9999999999  -- �ִ밪: 9999999999 --> NUMBER(10) ����
  CACHE 2                        -- 2���� �޸𸮿����� ���
  NOCYCLE;                      -- �ٽ� 1���� �����Ǵ� ���� ����
  
  
  
-- ���
INSERT INTO board(bnum, btitle, bcontent, passwd, word, rdate, image, imagesaved, size, MEMBERNO)
VALUES(board_seq, '�Խñ�1', 'ù��° �Խñ��Դϴ�.', '1234', 'ù��, ù��°', sysdate, 'first.jpg', 'first_1.jpg', 1000, 1);

-- ���
SELECT bnum, btitle, rdate, cnt, MEMBERNO
FROM board
ORDER BY bnum DESC;

-- ��ȸ
SELECT bnum, btitle, bcontent, passwd, recom, cnt, replycnt, word, rdate, image, imagesaved, size, MEMBERNO
FROM board
WHERE bnum=1;



