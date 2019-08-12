CREATE DATABASE TASK;

USE TASK;

CREATE TABLE CURRENCY
(ID SMALLINT UNSIGNED AUTO_INCREMENT,
CURRENCY VARCHAR (3),
DESCRIPTION VARCHAR (100),
CONSTRAINT pk_CURRENCY PRIMARY KEY (id)
);

CREATE TABLE CURRENCY_RATES
(ID SMALLINT UNSIGNED AUTO_INCREMENT,
CURRENCY VARCHAR (3),
RATE FLOAT (20, 4),
ID_CURRENCY SMALLINT UNSIGNED,
DATE DATE,
CONSTRAINT pk_CURRENCY_RATES PRIMARY KEY (ID),
FOREIGN KEY (ID_CURRENCY) 
REFERENCES CURRENCY (ID)
);

ALTER TABLE CURRENCY_RATES
ADD INDEX CR_date_IDX (DATE);