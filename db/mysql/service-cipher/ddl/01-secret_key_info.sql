DROP TABLE IF EXISTS SECRET_KEY_INFO;
CREATE TABLE SECRET_KEY_INFO
(
  key_id       VARCHAR(32) PRIMARY KEY COMMENT 'keyid',
  public_key   VARCHAR(392) NOT NULL COMMENT '公钥',
  private_key  VARCHAR(1624) NOT NULL COMMENT '私钥',
  CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  UPDATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8;


