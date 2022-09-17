CREATE TABLE roleEntity
(
    `role_id` 		INT UNSIGNED 	NOT NULL 	AUTO_INCREMENT 	COMMENT '역할ID',
    `role_name` 	VARCHAR(15) 	NOT NULL 					COMMENT '역할이름',
    PRIMARY KEY (role_id)
) COMMENT '역할';


CREATE TABLE accountEntity
(
    `account_id` 	VARCHAR(20) 	NOT NULL 		COMMENT '계정ID',
    `password` 	    VARCHAR(20) 	NOT NULL 		COMMENT '비밀번호',
    `role_id` 		INT UNSIGNED 	NOT NULL 		COMMENT '역할ID',
    PRIMARY KEY (account_id),
    CONSTRAINT FK_account_role FOREIGN KEY (role_id) REFERENCES roleEntity(role_id) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '계정';


CREATE TABLE licenseEntity
(
    `license_key`       CHAR(19) 		NOT NULL 								    COMMENT '라이선스키',
    `create_at`  	    TIMESTAMP 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP 	COMMENT '생성일시',
    `update_at` 	    TIMESTAMP 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP 	COMMENT '수정일시',
    `account_id` 	    VARCHAR(20) 	NOT NULL 								    COMMENT '계정ID',
    PRIMARY KEY (license_key),
    CONSTRAINT FK_license_account FOREIGN KEY (account_id) REFERENCES accountEntity(account_id) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '라이선스';


CREATE TABLE productEntity
(
    `product_id` 		INT UNSIGNED 	NOT NULL 		AUTO_INCREMENT 			    COMMENT '제품ID',
    `product_name` 	    VARCHAR(45) 	NOT NULL 								    COMMENT '제품이름',
    `create_at`  		TIMESTAMP 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP 	COMMENT '생성일시',
    `account_id` 		VARCHAR(20) 	NOT NULL 								    COMMENT '계정ID',
    PRIMARY KEY (product_id),
    CONSTRAINT FK_product_account FOREIGN KEY (account_id) REFERENCES accountEntity(account_id) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '제품';


CREATE TABLE auth
(
    `auth_id` 		INT UNSIGNED 	NOT NULL 		AUTO_INCREMENT 			    COMMENT '인증ID',
    `device` 		BINARY(16)      NOT NULL 								    COMMENT '기기일련번호',
    `is_activated` 	TINYINT(1) 		NOT NULL 		DEFAULT TRUE                COMMENT '활성여부',
    `create_at`  	TIMESTAMP 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP 	COMMENT '생성일시',
    `update_at` 	TIMESTAMP 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP 	COMMENT '수정일시',
    `license_key` 	CHAR(19) 		NOT NULL 								    COMMENT '라이선스키',
    `product_id` 	INT UNSIGNED 	NOT NULL 								    COMMENT '제품ID',
    PRIMARY KEY (auth_id),
    CONSTRAINT FK_auth_license FOREIGN KEY (license_key) REFERENCES licenseEntity(license_key) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_auth_product FOREIGN KEY (product_id) REFERENCES productEntity(product_id) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '인증';


CREATE TABLE license_product
(
    `license_product_id` 		INT UNSIGNED    NOT NULL 		AUTO_INCREMENT 			    COMMENT '라이선스_제품ID',
    `num_of_auth_available` 	INT UNSIGNED 	NOT NULL 		DEFAULT 1 					COMMENT '제품별 인증 가능 횟수',
    `is_activated` 			    TINYINT(1) 		NOT NULL 		DEFAULT TRUE 				COMMENT '활성여부',
    `create_at`  			    TIMESTAMP 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP 	COMMENT '생성일시',
    `update_at` 			    TIMESTAMP 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP 	COMMENT '수정일시',
    `expire_at` 			    TIMESTAMP 		NOT NULL 		DEFAULT CURRENT_TIMESTAMP 	COMMENT '만료일시',
    `license_key` 			    CHAR(19) 		NOT NULL 								    COMMENT '라이선스키',
    `product_id` 			    INT UNSIGNED 	NOT NULL 								    COMMENT '제품ID',
    PRIMARY KEY (license_product_id),
    CONSTRAINT FK_license_product_product FOREIGN KEY (product_id) REFERENCES productEntity(product_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_license_product_license FOREIGN KEY (license_key) REFERENCES licenseEntity(license_key) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '라이선스_제품_관계';