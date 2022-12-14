-- This Schema is primarely used in the beginning to create the Tables in the Linked Database.
-- An empty file can be linked to it, In Windows you need to delcare his extension to sql, or sqlite and then by running these Querie
-- Tables with entries can be created.

-- Here SQL is used as schema description for DDBB as well as to build the entity classes in kotlin
-- This is describing the DDL definition of Database Schema for SQLite file

CREATE TABLE if not exists BOOK (
 id INTEGER PRIMARY KEY AUTOINCREMENT,
TITLE TEXT NOT NULL,
AUTHOR TEXT NOT NULL,
YEAR_OF_PUBLICATION INTEGER NOT NULL,
PUBLISHER TEXT NOT NULL,
SUBJECT TEXT,
AUTHOR_ID INTEGER,
PUBLISHER_ID INTEGER,
FOREIGN KEY(AUTHOR_ID) REFERENCES AUTHOR(id),
FOREIGN KEY(PUBLISHER_ID) REFERENCES PUBLISHER(id)
);

allBooks:
SELECT *
FROM BOOK;

CREATE TABLE if not exists AUTHOR (
 id INTEGER PRIMARY KEY AUTOINCREMENT,
 FIRSTNAME TEXT NOT NULL,
 SURNAME TEXT NOT NULL
);


CREATE TABLE if not exists PUBLISHER (
 id INTEGER PRIMARY KEY AUTOINCREMENT,
 NAME TEXT NOT NULL
 );

SELECT * FROM BOOK
WHERE TITLE like "Il%"

SELECT * FROM BOOK WHERE UPPER(TITLE) LIKE ?

SELECT * FROM BOOK
WHERE TITLE LIKE "%Il%";



--drop table LECTURER
--drop table FACULTY