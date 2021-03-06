CREATE DATABASE FV_FIVEWARE;

USE FV_FIVEWARE;

CREATE TABLE FVC_CATEGORIA
(

NU_ID			INTEGER	PRIMARY KEY	AUTO_INCREMENT,
DS_DESCRICAO	VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE FVL_LIVRO
(

NU_ID			BIGINT			PRIMARY KEY	AUTO_INCREMENT,
NM_NOME			VARCHAR(500)	NOT NULL	UNIQUE,
NM_EDITORA		VARCHAR(500)	NOT NULL,
NU_EDICAO		SMALLINT,
NU_PAGINAS		SMALLINT		NOT NULL,
NU_PESO			FLOAT,
NU_ISBN			BIGINT			NOT NULL,
ID_CATEGORIA	INTEGER			NOT NULL,
DS_IDIOMA		VARCHAR(5)		NOT NULL,
FL_EBOOK		BIT				DEFAULT 0,
DT_LANCAMENTO	DATETIME
);

ALTER TABLE FVL_LIVRO
ADD CONSTRAINT FOREIGN KEY FK_CAT (ID_CATEGORIA) REFERENCES FVC_CATEGORIA (NU_ID);