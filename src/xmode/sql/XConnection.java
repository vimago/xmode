package xmode.sql;

import java.sql.*;

public class XConnection {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

   public Connection conn;

   public XConnection(String url, String user, String pass) {
     try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(url, user, pass);
     } catch(SQLException se){
        //Handle errors for JDBC
        se.printStackTrace();
     } catch(Exception e){
        //Handle errors for Class.forName
        e.printStackTrace();
     }
   }

   public XResult executeQuery(String sql) throws SQLException {
     System.out.println("Creating statement...");
     return new XResult(conn.createStatement(), sql);
   }

   public void close() {
      try {
        conn.close();
      }catch(SQLException se){
        //Handle errors for JDBC
        se.printStackTrace();
      }catch(Exception e){
        //Handle errors for Class.forName
        e.printStackTrace();
      }finally{
        //finally block used to close resources
        try{
          if(conn!=null)
             conn.close();
        }catch(SQLException se){
          se.printStackTrace();
        }
      }
   }
}
