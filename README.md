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

# Approach

There must be more than one way to approach this.

### Solution 1: mysql

This solution does not involve jdbc but could be handy to test/profile any query.

[generate-csv.sh](generate-csv.sh)
```
QUERY="select * from location where timestamp BETWEEN '$query_date 00:00:00' AND '$query_date 23:59:59'"
mysql -u$user -p$pass $table -e "$QUERY" -B | sed "s/'/\'/;s/\t/\",\"/g;s/^/\"/;s/$/\"/;s/\n//g" > $output

```

### Solution 2: java

Here is a quick java app with some abstractions that can still be simplified
and generalized more. This could be handy to have in place abstractions that
can be customized and enhanced for any other queries/needs related to
db queries and csv files.

[GenerateCsvFile.java](src/GenerageCsvFile.java)
```
java GenerateCsvFile 2017-05-01 tmp/simon.csv
```

### Solution 3: java + mysql

There seems to be this other way to generate csv files directly from the
mysql statement:

 ```

  SELECT advertiser_id, latitude, longitude, timestamp FROM location
  where timestamp BETWEEN '2017-05-01 00:00:00' AND '2017-05-01 23:59:59'
  INTO OUTFILE '/tmp/data.csv'
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'

 ```

This could also be an option depending on the database security permissions (secure-file-priv), etc.

# Conclusion

I was able to generate the csv files with all 3 solutions and with a sample of 5 million records.
Had times of less than 20 seconds for queries that generated around 2 million csv lines.
This could use some more profiling to deal with the real database/huge-table to:
1. improve query time
2. reduce memory usage

# Considerations

* Adding an index on the timestamp field

https://dev.mysql.com/doc/refman/5.7/en/innodb-index-types.html
```
CREATE INDEX timestamp_idx ON location(timestamp) USING BTREE;
```
This did not make a difference on my sample data. But somehow I feel that I am still
missing something here.

* Using forward only cursor

http://media.datadirect.com/download/docs/connectsqlxml/jdbcug/jdbcdesi.htm

* etc, I am sure there is more ways to make this run faster and with less memory.
