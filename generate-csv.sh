#!/bin/bash
# Read user
echo -n "mysql user:"
read user
# Read password
echo -n "mysql password:"
read -s pass
echo
# Read database
echo -n "mysql database:"
read db
# Read day date
echo -n "day for the query (ie. 2017-05-01)"
read date
# Read output file
echo -n "output file:"
read output

QUERY="select * from location where timestamp BETWEEN '$date 00:00:00' AND '$date 23:59:59'"
mysql -u$user -p$pass $db -e "$QUERY" -B | sed "s/'/\'/;s/\t/\",\"/g;s/^/\"/;s/$/\"/;s/\n//g" > $output
