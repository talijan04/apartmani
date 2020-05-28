CREATE TABLE apartman(
id INT NOT NULL PRIMARY KEY,
naziv VARCHAR(100) NOT NULL,
cena float NULL
);

CREATE TABLE users(
  id INT NOT NULL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    active boolean NOT NULL,
    roles VARCHAR(200) NOT NULL
);

CREATE TABLE rezervacije(
    id SERIAL NOT NULL PRIMARY KEY,
    apartmanId INT,
    ime VARCHAR(100) NOT NULL,
    prezime VARCHAR(100) NOT NULL,
    brLk VARCHAR(10),
    email VARCHAR(100),
    dateFrom DATE,
    dateTo DATE
);