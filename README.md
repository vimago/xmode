# Problem

Use JDBC to query records within a timestamp range.
Records retrieved could be around 50 million.
Generate CSV file named /tmp/data.csv in the same field
order as the columns listed in the table.

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

# Solution 1: mysql command line

This solution does not involve jdbc but could be handy to test/profile any query.

```
QUERY="select * from location where timestamp BETWEEN '$query_date 00:00:00' AND '$query_date 23:59:59'"
mysql -u$user -p$pass $table -e "$QUERY" -B | sed "s/'/\'/;s/\t/\",\"/g;s/^/\"/;s/$/\"/;s/\n//g" > $output
[generate-csv.sh](generate-csv.sh)

```

# Solution 2

# Solution 3
