CREATE DATABASE IF NOT EXISTS xmode;

USE xmode;

CREATE TABLE IF NOT EXISTS location (
  advertiser_id varchar(255) NOT NULL,
  latitude double NOT NULL,
  longitude double NOT NULL,
  horizontal_accuracy double NOT NULL,
  timestamp datetime(6) NOT NULL,
  PRIMARY KEY(advertiser_id)
) ENGINE=InnoDB;


-- INSERT INTO location (advertiser_id, latitude, longitude, horizontal_accuracy, timestamp) VALUES
-- ('aaa', 1, 1, 1, '2017-04-05 18:19:03'),
-- ('bbb', 2, 2, 2, '2017-05-05 18:19:03'),
-- ('ccc', 3, 3, 3, '2017-06-05 18:19:03')

-- drop index idx_time on location;
-- CREATE INDEX idx_time ON location(timestamp) USING BTREE;
