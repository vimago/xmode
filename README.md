# Problem

Use JDBC to query records within a timestamp range.
Records retrieved could be around 50 million.

```
CREATE TABLE location (
  advertiser_id varchar(255) NOT NULL,
  latitude double NOT NULL,
  longitude double NOT NULL,
  horizontal_accuracy double NOT NULL,
  timestamp datetime(6) NOT NULL,
  PRIMARY KEY(advertiser_id),
)ENGINE=InnoDB
```
