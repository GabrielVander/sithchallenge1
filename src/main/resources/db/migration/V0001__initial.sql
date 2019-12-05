DROP TABLE IF EXISTS author;

CREATE TABLE author (
  id            INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  firstName     VARCHAR(50),
  lastName      VARCHAR(50)  NOT NULL,
  birthday      DATE,
  distinguished BIT NOT NULL
);

CREATE TABLE book (
    id              INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title           VARCHAR(50),
    publisherName   VARCHAR(50),
    publishDate     DATE,
    language        VARCHAR(50),
    CHECK (UPPER(language) IN ('ENGLISH', 'PORTUGUESE', 'ITALIAN', 'RUSSIAN'))
);

CREATE TABLE book_author (
  authorId   INT NOT NULL,
  bookId     INT NOT NULL,
  PRIMARY KEY (authorId, bookId)
);

ALTER TABLE book_author ADD CONSTRAINT FK_author FOREIGN KEY (authorId) REFERENCES author(id);
ALTER TABLE book_author ADD CONSTRAINT FK_book FOREIGN KEY (authorId) REFERENCES author(id);

INSERT INTO author(firstName, lastName, birthday, distinguished) VALUES
  ('Kathy', 'Sierra', CURRENT_DATE, 0),
  ('Bert', 'Bates', CURRENT_DATE, 1),
  ('Bryan', 'Basham', CURRENT_DATE, 0);

INSERT INTO book(title, publisherName, publishDate, language) VALUES
('Kathy', 'Sierra', CURRENT_DATE, 'Italian'),
('Bert', 'Bates', CURRENT_DATE, 'portuguese'),
('Bryan', 'Basham', CURRENT_DATE, 'English');

INSERT INTO book_author(authorId, bookId) VALUES
(1, 1),
(2, 2),
(3, 3);