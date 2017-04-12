DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq_for_user RESTART WITH 100000;
ALTER SEQUENCE global_seq_for_meal RESTART WITH 1;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (dateTime, description, calories, id_user)
VALUES ('2017-04-11 10:02:00', 'Breakfast', 800, 100000);

INSERT INTO meals (dateTime, description, calories, id_user)
VALUES ('2017-04-11 14:20:00', 'Lunch', 1100, 100000);

INSERT INTO meals (dateTime, description, calories, id_user)
VALUES ('2017-04-11 21:20:26', 'Dinner', 1000, 100001);
