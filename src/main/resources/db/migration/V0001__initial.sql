DROP TABLE IF EXISTS author;

CREATE TABLE author (
  id            INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  firstName     VARCHAR(50),
  lastName      VARCHAR(50)  NOT NULL,
  birthday      DATE,
  distinguished BIT NOT NULL
);

INSERT INTO author(firstName, lastName, birthday, distinguished) VALUES
  ('Kathy', 'Sierra', CURRENT_DATE, 0),
  ('Bert', 'Bates', CURRENT_DATE, 1),
  ('Bryan', 'Basham', CURRENT_DATE, 0);