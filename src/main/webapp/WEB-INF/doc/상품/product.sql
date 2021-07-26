
DROP table pay;
/**********************************/
/* Table Name: 결제 */
/**********************************/
CREATE TABLE pay(
		pay_no                        		NUMBER(10)		 NOT NULL PRIMARY KEY,
		pay_name                      		VARCHAR2(100)		 NOT NULL,
		pay_count                     		NUMBER(20)		 NULL ,
		pay_day                       		NUMBER(20)		 NOT NULL,
		rdata                         		DATE		 NOT NULL,
		payment                       		VARCHAR2(100)		 NOT NULL,
		price                         		NUMBER(10)		 NOT NULL,
		tid                           		VARCHAR2(100)		 NOT NULL,
		payment_method_type           		VARCHAR2(100)		 NOT NULL,
		created_at                    		DATE		 NOT NULL,
		approved_at                   		DATE		 NOT NULL,
        memberno                        NUMBER(10) NOT NULL,
        FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE pay is '결제';
COMMENT ON COLUMN pay.pay_no is '결제번호';
COMMENT ON COLUMN pay.pay_name is '이용권이름';
COMMENT ON COLUMN pay.pay_count is '듣기횟수';
COMMENT ON COLUMN pay.pay_day is '남은기간';
COMMENT ON COLUMN pay.rdata is '결제일';
COMMENT ON COLUMN pay.payment is '결제방법';
COMMENT ON COLUMN pay.price is '결제금액';
COMMENT ON COLUMN pay.tid is '결제 고유 번호';
COMMENT ON COLUMN pay.payment_method_type is '결제 수단';
COMMENT ON COLUMN pay.created_at is '결제 요청 시각';
COMMENT ON COLUMN pay.approved_at is '결제승인시각';
COMMENT ON COLUMN pay.memberno is '회원 번호';

commit;


drop SEQUENCE pay_seq;
CREATE SEQUENCE pay_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

/**********************************/
/* Table Name: 이용권종류 */
/**********************************/
drop table product;
CREATE TABLE product(
		product_no                    		NUMBER(10)		 NOT NULL  PRIMARY KEY,
		product_name                  		VARCHAR2(100)		 NOT NULL,
		product_count                 		NUMBER(10)		 NULL ,
		product_day                   		NUMBER(10)		 NOT NULL,
        product_cont                   	VARCHAR2(100)		 NOT NULL,
        rdata                         		DATE		 NOT NULL,
        product_price                        	NUMBER(10)		 NOT NULL,
        file1                         		VARCHAR2(100)		 NOT NULL
);

COMMENT ON TABLE product is '이용권종류';
COMMENT ON COLUMN product.product_no is '이용권 번호';
COMMENT ON COLUMN product.product_name is '이용권 이름';
COMMENT ON COLUMN product.product_count is '듣기 횟수';
COMMENT ON COLUMN product.product_day is '이용기간';
COMMENT ON COLUMN product.product_cont is '이용권설명';
COMMENT ON COLUMN product.rdata is '등록일';
COMMENT ON COLUMN product.product_price is '가격';
COMMENT ON COLUMN product.file1 is '이용권사진';




--ALTER TABLE product RENAME COLUMN product_pric TO product_price

--컬럼 추가 1
--ALTER TABLE product ADD  file1  VARCHAR2(100);

drop SEQUENCE product_seq;
CREATE SEQUENCE product_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
  
  
  --이용권생성--
INSERT INTO product(product_no, product_name, product_count, product_day, product_cont, rdata, product_price, file1)
VALUES( product_seq.nextval, '스트리밍 30일', '-1', '30', '스트리밍(30일)', sysdate, 3900, 'test1.jpg'); 

INSERT INTO product(product_no, product_name, product_count, product_day, product_cont, rdata, product_price, file1)
VALUES( product_seq.nextval, '스트리밍 100곡', '100', '30', '스트리밍100곡(30일)', sysdate, 900, 'test2.jpg'); 

INSERT INTO product(product_no, product_name, product_count, product_day, product_cont, rdata, product_price, file1)
VALUES( product_seq.nextval, '스트리밍 100일', '-1', '100', '스트리밍(100일)', sysdate, 7900, 'test3.jpg'); 

     SELECT product_no, product_name, product_count, product_day, rdata , product_PRICe, file1
    FROM product
    ORDER BY product_no ASC;
    
    commit;
    --사용자 이용권등록(결제완료시 자동등록)
   -- INSERT INTO product(product_no, product_name, product_count, product_day, product_cont, rdata, product_price, file1)
--VALUES( product_seq.nextval, '이용권1', '999', '365', '999번 스트리밍(1년)', sysdate, 3900, 'test.jpg'); 

--INSERT INTO pay(pay_no, pay_name, pay_count, pay_day, rdata, payment, price, tid, payment_method_type, created_at, approved_at, memberno)
   -- VALUES(product_seq.nextval, '이용권1', 99, 99, sysdate, 'testpay',  3200, 'test', 'test', sysdate, sysdate,1);
    






