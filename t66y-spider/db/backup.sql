drop table IMAGE_INFO_BAK;
drop table TORRENT_INFO_BAK;
drop table HTML_INFO_BAK;
drop table TOPIC_INFO_BAK;

CREATE TABLE TOPIC_INFO_BAK
SELECT TOPIC_URL, TOPIC_FID, TOPIC_TITLE, TOPIC_STATUS, CREATED_DATE, UPDATED_DATE
FROM TOPIC_INFO
WHERE 1 = 1;

CREATE TABLE IMAGE_INFO_BAK
SELECT TOPIC_URL, IMAGE_URL, IMAGE_STATUS, CREATED_DATE, UPDATED_DATE
FROM IMAGE_INFO
WHERE 1 = 1;

CREATE TABLE TORRENT_INFO_BAK
SELECT TOPIC_URL, TORRENT_URL, TORRENT_HASH, TORRENT_STATUS, CREATED_DATE, UPDATED_DATE
FROM TORRENT_INFO
WHERE 1 = 1;

CREATE TABLE HTML_INFO_BAK
SELECT TOPIC_URL, HTML, CREATED_DATE, UPDATED_DATE
FROM HTML_INFO
WHERE 1 = 1;

