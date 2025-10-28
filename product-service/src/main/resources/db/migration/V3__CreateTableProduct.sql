CREATE SEQUENCE IF NOT EXISTS service_product_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE service_product
(
    id             BIGINT         NOT NULL,
    account_number VARCHAR(60)    NOT NULL UNIQUE,
    balance        DECIMAL(10, 2) NOT NULL,
    type           varchar(20) default 'ACCOUNT',
    user_id        BIGINT references service_user (id),
    CONSTRAINT pk_service_product PRIMARY KEY (id)
);

INSERT INTO service_product (id, account_number, balance, type, user_id)
VALUES (10, '10-00001', 100000.01, 'ACCOUNT', 10),
       (11, '10-00002', 1000.0, 'CARD', 10),
       (12, '10-00003', -10.0, 'CARD', 10),
       (20, '20-00001', 1000.0, 'CARD', 20),
       (21, '20-00002', -10.0, 'ACCOUNT', 20),
       (22, '20-00003', 1000.0, 'CARD', 20),
       (30, '30-666', 666, 'CARD', 30);
