CREATE SEQUENCE IF NOT EXISTS service_user_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE service_user
(
    id       BIGINT       NOT NULL,
    username VARCHAR(255) NOT NULL,
    age      INTEGER,
    CONSTRAINT pk_service_user PRIMARY KEY (id)
);

--DROP TABLE "user" CASCADE;