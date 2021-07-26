
/**********************************/
/* Table Name: �������� */
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
    adminno INTEGER(10),
  FOREIGN KEY (adminno) REFERENCES admin (adminno)
);

COMMENT ON TABLE notice is '��������';
COMMENT ON COLUMN notice.nnum is '������ȣ';
COMMENT ON COLUMN notice.ntitle is '��������';
COMMENT ON COLUMN notice.ncontent is '��������';
COMMENT ON COLUMN notice.cnt is '��ȸ��';
COMMENT ON COLUMN notice.word is '�˻���';
COMMENT ON COLUMN notice.rdate is '�����';
COMMENT ON COLUMN notice.nimage is '�̹���';
COMMENT ON COLUMN notice.nimagesaved is '������̹���';
COMMENT ON COLUMN notice.nsize is '�̹���ũ��';
COMMENT ON COLUMN notice.adminno is '������ ��ȣ';


DROP SEQUENCE notice_seq;

CREATE SEQUENCE notice_seq
  START WITH 1                -- ���� ��ȣ
  INCREMENT BY 1            -- ������
  MAXVALUE 9999999999  -- �ִ밪: 9999999999 --> NUMBER(10) ����
  CACHE 2                        -- 2���� �޸𸮿����� ���
  NOCYCLE;                      -- �ٽ� 1���� �����Ǵ� ���� ����
  
  
  
-- ���
INSERT INTO notice(nnum, ntitle, ncontent, cnt, word, rdate, nimage, nimagesaved, nsize)
VALUES(notice_seq.nextval,'����1', 'ù��° �����Դϴ�.', 0, '����1, ù����', sysdate, 'notice1.jpg', 'notice1_1.jpg', 1000);


-- ���
SELECT nnum, ntitle, rdate, cnt, adminno
FROM notice
ORDER BY nnum DESC;

-- ��ȸ
SELECT nnum, ntitle, ncontent, cnt, word, rdate, nimage, nimagesaved, nsize
FROM notice
WHERE nnum=3;

-- ����
UPDATE notice
SET ntitle='����1 ����', ncontent='ù��° ���� �����Դϴ�.', word='����1, ù��������', rdate=sysdate, nimage='notice1_u.jpg', nimagesaved='notice_u_1.jpg', nsize=5000
WHERE nnum=3;

-- ����
DELETE FROM notice
WHERE nnum=3;


commit;



