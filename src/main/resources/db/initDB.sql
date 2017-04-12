DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq_for_user START 100000;
CREATE SEQUENCE global_seq_for_meal START 1;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq_for_user'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOL DEFAULT TRUE,
  calories_per_day INTEGER DEFAULT 2000 NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meals
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq_for_meal'),
  dateTime   TIMESTAMP DEFAULT now(),
  description      VARCHAR NOT NULL,
  calories         INTEGER,
  id_user INTEGER NOT NULL,
  FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE
);
