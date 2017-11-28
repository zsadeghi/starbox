CREATE TABLE ingredient
(
  id   INT AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE location
(
  id      INT AUTO_INCREMENT
    PRIMARY KEY,
  address VARCHAR(255) NOT NULL,
  name    VARCHAR(255) NOT NULL,
  phone   VARCHAR(255) NOT NULL
);

CREATE TABLE location_offering
(
  offering_price_id INT NOT NULL,
  location_id       INT NOT NULL,
  PRIMARY KEY (location_id, offering_price_id),
  CONSTRAINT FKgck6qc8jshhrpbmysco2u2jye
  FOREIGN KEY (location_id) REFERENCES location (id)
);

CREATE TABLE offering
(
  id               INT AUTO_INCREMENT
    PRIMARY KEY,
  description      VARCHAR(255) NOT NULL,
  name             VARCHAR(255) NOT NULL,
  offering_type_id INT          NOT NULL
);

CREATE TABLE offering_ingredient
(
  offering_id   INT NOT NULL,
  ingredient_id INT NOT NULL,
  PRIMARY KEY (ingredient_id, offering_id),
  CONSTRAINT FK5mmdaqwma005b4usnci3lg8ie
  FOREIGN KEY (offering_id) REFERENCES offering (id),
  CONSTRAINT FKohsr3bkdtgtbx9vljcgv4v0vo
  FOREIGN KEY (ingredient_id) REFERENCES ingredient (id)
);

CREATE TABLE offering_price
(
  id               INT AUTO_INCREMENT
    PRIMARY KEY,
  price            FLOAT NOT NULL,
  offering_id      INT   NULL,
  offering_size_id INT   NULL,
  CONSTRAINT FK11xqncsu82d8jglieakr2dfs2
  FOREIGN KEY (offering_id) REFERENCES offering (id)
);

ALTER TABLE location_offering
  ADD CONSTRAINT FK6fs43whdkg75j7ysq6cpycskp
FOREIGN KEY (offering_price_id) REFERENCES offering_price (id);

CREATE TABLE offering_size
(
  id   INT AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  oz   INT          NOT NULL
);

ALTER TABLE offering_price
  ADD CONSTRAINT FKlx8ltrpr7su95s8usw8m9ep7f
FOREIGN KEY (offering_size_id) REFERENCES offering_size (id);

CREATE TABLE offering_tag
(
  tag_id      INT NOT NULL,
  offering_id INT NOT NULL,
  PRIMARY KEY (offering_id, tag_id),
  CONSTRAINT FKr756lnm3t7waj988ffa9nfu5x
  FOREIGN KEY (offering_id) REFERENCES offering (id)
);

CREATE TABLE offering_type
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  description VARCHAR(255) NOT NULL,
  name        VARCHAR(255) NOT NULL
);

ALTER TABLE offering
  ADD CONSTRAINT FKa9luqt4lhtd7jo8qc5kd9sbem
FOREIGN KEY (offering_type_id) REFERENCES offering_type (id);

CREATE TABLE tag
(
  id   INT AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

ALTER TABLE offering_tag
  ADD CONSTRAINT FK2gjfveqjhdg24ir9dc6lud95y
FOREIGN KEY (tag_id) REFERENCES tag (id);

