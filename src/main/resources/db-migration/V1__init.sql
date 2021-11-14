CREATE SCHEMA IF NOT EXISTS masters;
CREATE TABLE IF NOT EXISTS masters.accounts (
	user_id serial PRIMARY KEY,
	username VARCHAR ( 50 ) ,
	password VARCHAR ( 50 ) ,
	email VARCHAR ( 255 ) ,
	created_on TIMESTAMP NOT NULL,
    last_login TIMESTAMP
);
CREATE TABLE IF NOT EXISTS masters.author (
  id             INT          NOT NULL PRIMARY KEY,
  first_name     VARCHAR(50),
  last_name      VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS masters.book (
  id             INT          NOT NULL PRIMARY KEY,
  title          VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS masters.author_book (
  author_id      INT          NOT NULL,
  book_id        INT          NOT NULL,

  PRIMARY KEY (author_id, book_id),
  CONSTRAINT fk_ab_author     FOREIGN KEY (author_id)  REFERENCES masters.author (id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_ab_book       FOREIGN KEY (book_id)    REFERENCES masters.book   (id)
);

-- INSERT INTO masters.author VALUES
--   (1, 'Kathy', 'Sierra'),
--   (2, 'Bert', 'Bates'),
--   (3, 'Bryan', 'Basham');
--
-- INSERT INTO masters.book VALUES
--   (1, 'Head First Java'),
--   (2, 'Head First Servlets and JSP'),
--   (3, 'OCA/OCP Java SE 7 Programmer');
--
-- INSERT INTO masters.author_book VALUES (1, 1), (1, 3), (2, 1);
-- INSERT INTO masters.accounts (username,"password",email,created_on,last_login) VALUES
-- ('sang','1234','sang@gmail.com','2021-11-02 10:51:12.903','2021-11-02 10:51:12.903')
-- ,('trong','4455','trong@gmail.com','2021-11-02 10:51:33.145','2021-11-02 10:51:33.145');