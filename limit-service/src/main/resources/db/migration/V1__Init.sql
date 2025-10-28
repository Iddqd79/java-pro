CREATE TABLE operation_limit
(
    user_id BIGINT         NOT NULL,
    "limit" DECIMAL(10, 2) NOT NULL DEFAULT 100000.00,
    CONSTRAINT pk_operation_limit PRIMARY KEY (user_id)
);

CREATE SEQUENCE IF NOT EXISTS operations_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE operations
(
    id      BIGINT         NOT NULL,
    status  varchar(20)             default 'HOLD',
    amount  DECIMAL(10, 2) NOT NULL DEFAULT 100000.00,
    user_id BIGINT references operation_limit (user_id),
    CONSTRAINT pk_operations_id PRIMARY KEY (id)
);

--DROP TABLE "user" CASCADE;