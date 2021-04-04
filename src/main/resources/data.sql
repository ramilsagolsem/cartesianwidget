DROP TABLE IF EXISTS CARTISIANWIDGET;

CREATE TABLE CARTISIANWIDGET (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  xcoordinate INT NOT NULL,
  ycoordinate INT NOT NULL,
  zcoordinate INT NOT NULL,
  width INT NOT NULL,
  height INT NOT NULL,
  lastmodified VARCHAR(50) NOT NULL
);

INSERT INTO CARTISIANWIDGET (xcoordinate, ycoordinate, zcoordinate, width, height, lastmodified) VALUES
(10, 10, 2, 5,10,'2021-04-04 08:26:14'),
(10, 20, 1, 5,10,'2021-04-04 08:26:14'),
(10, 30, 3, 5,10,'2021-04-04 08:26:14'),
(10, 40, 5, 5,10,'2021-04-04 08:26:14'),
(10, 50, 4, 5,10,'2021-04-04 08:26:14');