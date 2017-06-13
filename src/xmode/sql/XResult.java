package xmode.sql;

import java.sql.*;

public class XResult {
  private Statement stmt;
  public ResultSet rs;

  public XResult(Statement stmt, String sql) throws SQLException {
      stmt = stmt;
      rs = stmt.executeQuery(sql);
  }

  public boolean next() throws SQLException {
    return rs.next();
  }

  public void close() {
    try {
       rs.close();
       if(stmt!=null){stmt.close();}
     }catch(SQLException se){
       //Handle errors for JDBC
       se.printStackTrace();
     }catch(Exception e){
       //Handle errors for Class.forName
       e.printStackTrace();
     }finally{
       //finally block used to close resources
       try{
         if(stmt!=null)
            stmt.close();
       }catch(SQLException se2){
       }// nothing we can do
     }
  }

}
