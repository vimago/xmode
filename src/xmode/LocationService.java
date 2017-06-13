package xmode;

import xmode.sql.XConnection;
import xmode.sql.XResult;
import xmode.csv.CsvWritter;
import java.sql.*;

public class LocationService {

  static final String URL = "jdbc:mysql://localhost:3306/xmode?useSSL=false";
  static final String USER = "simon";
  static final String PASS = "simon";

  static final String INSERT_SQL =
      "insert into location(advertiser_id, latitude, longitude, horizontal_accuracy, timestamp) " +
      "values(?, ?, ?, ?, ?)";

  public static void generateCsvFile(String query, String filename)
        throws Exception {
      XConnection conn = new XConnection(URL, USER, PASS);
      long startTime = System.nanoTime();
      XResult rs = conn.executeQuery(query);

      long endTime1 = System.nanoTime();
      long duration1 = (endTime1 - startTime)/1000000000;
      System.out.println("Query Time:" + duration1 + "secs");

      CsvWritter w = new CsvWritter(filename);

      while(rs.rs.next()) {
        w.row(new String[]{
          rs.rs.getString("advertiser_id"),
          rs.rs.getString("latitude"),
          rs.rs.getString("longitude"),
          rs.rs.getString("timestamp")
        });
      }

      rs.close();
      conn.close();
      w.close();
      long endTime = System.nanoTime();
      long duration = (endTime - startTime)/1000000000;
      System.out.println("Time:" + duration + "secs");
  }

  public static void insertTestData(
        String advertiserPrefix, String time, int records) throws Exception {
    XConnection xconn = new XConnection(URL, USER, PASS);
    Connection conn = xconn.conn;

    PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
    conn.setAutoCommit(false);
    for(int i=1; i<= records;i++){
      pstmt.setString(1, advertiserPrefix + i);
      pstmt.setDouble(2, 3);
      pstmt.setDouble(3, 3);
      pstmt.setDouble(4, 3);
      pstmt.setString(5, time);
      pstmt.addBatch();
    }
    int[] result = pstmt.executeBatch();
    System.out.println("Rows inserted: "+ result.length);
    conn.commit();
    conn.close();
  }
}
