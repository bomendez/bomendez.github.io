-- Drop all tables.

DROP TABLE IF EXISTS day_rating;
DROP TABLE IF EXISTS dates;
DROP TABLE IF EXISTS user_has_activity;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS activity_at_place;
DROP TABLE IF EXISTS place;
DROP TABLE IF EXISTS calories;
DROP TABLE IF EXISTS steps;
DROP TABLE IF EXISTS distance;
DROP TABLE IF EXISTS activity;
DROP TABLE IF EXISTS activity_types;

-- Create 'activity_type_lookup' table.

CREATE TABLE activity_types (
    activity_name character varying(32) PRIMARY KEY
);

-- Insert activity types into 'activity_type_lookup' table.

INSERT INTO activity_types (activity_name) VALUES ('walking');
INSERT INTO activity_types (activity_name) VALUES ('running');
INSERT INTO activity_types (activity_name) VALUES ('cycling');
INSERT INTO activity_types (activity_name) VALUES ('cross_country_skiing');
INSERT INTO activity_types (activity_name) VALUES ('kayaking');


-- Create 'activity' table.

CREATE TABLE activity
(
  activity_id serial NOT NULL UNIQUE PRIMARY KEY,
  activity_type character varying(32) NOT NULL,
  CONSTRAINT activity_type_fk
    FOREIGN KEY (activity_type)
    REFERENCES activity_types(activity_name) 
    ON DELETE CASCADE
);

-- Create 'steps' table.c

CREATE TABLE steps
(
  activity_id serial NOT NULL UNIQUE PRIMARY KEY,
  step_count integer NOT NULL,
  CONSTRAINT activity_id_fk
    FOREIGN KEY (activity_id)
    REFERENCES activity(activity_id) 
    ON DELETE CASCADE
);

-- Create 'calories' table.

CREATE TABLE calories
(
  activity_id serial NOT NULL UNIQUE PRIMARY KEY,
  calorie_count integer NOT NULL,
  CONSTRAINT activity_id_fk
    FOREIGN KEY (activity_id)
    REFERENCES activity(activity_id) 
    ON DELETE CASCADE
);

-- Create 'distance' table.

CREATE TABLE distance
(
  activity_id serial NOT NULL UNIQUE PRIMARY KEY,
  distance_count integer NOT NULL,
  CONSTRAINT activity_id_fk
    FOREIGN KEY (activity_id)
    REFERENCES activity(activity_id) 
    ON DELETE CASCADE
);

-- Create 'place' table.

CREATE TABLE place
(
  place_id serial NOT NULL UNIQUE PRIMARY KEY,
  place_name text,
  latitude numeric,
  longitude numeric
);

-- Create 'activity_at_place' table.

CREATE TABLE activity_at_place
(
  activity_id serial NOT NULL UNIQUE,
  place_id serial NOT NULL UNIQUE,
  CONSTRAINT activity_id_fk
    FOREIGN KEY (activity_id)
    REFERENCES activity(activity_id) 
    ON DELETE CASCADE,
  CONSTRAINT place_id_fk
    FOREIGN KEY (place_id)
    REFERENCES place(place_id) 
    ON DELETE CASCADE
);

-- Create 'user' table.

CREATE TABLE users
(
  user_id serial NOT NULL UNIQUE PRIMARY KEY,
  user_name text NOT NULL UNIQUE
);

-- Create 'user_has_activity' table.

CREATE TABLE user_has_activity
(
  activity_id serial NOT NULL UNIQUE,
  user_id serial NOT NULL,
  CONSTRAINT activity_id_fk
    FOREIGN KEY (activity_id)
    REFERENCES activity(activity_id) 
    ON DELETE CASCADE,
  CONSTRAINT user_id_fk
    FOREIGN KEY (user_id)
    REFERENCES users(user_id) 
    ON DELETE CASCADE
);

-- Create 'dates' table.

CREATE TABLE dates
(
  activity_id serial NOT NULL UNIQUE PRIMARY KEY,
  start_time TIMESTAMP WITH TIME ZONE NOT NULL,
  end_time TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT activity_id_fk
    FOREIGN KEY (activity_id)
    REFERENCES activity(activity_id) 
    ON DELETE CASCADE
);

-- Create 'day_rating' table.

CREATE TABLE day_rating
(
  day date NOT NULL UNIQUE PRIMARY KEY,
  rating bool NOT NULL
);