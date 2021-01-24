DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL
);
INSERT INTO user (user_name) VALUES
  ('User One'),
  ('User Two'),
  ('User Three');