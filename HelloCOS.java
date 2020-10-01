import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class HelloCOS {
  
   // JDBC driver name and database URL
   static final String DRIVER_CLASS = "com.ibm.cloud.sql.jdbc.Driver";  
   static final String DB_URL = "jdbc:ibmcloudsql:crn:v1:bluemix:public:sql-query:us-south:a/26247a9e0b3a8c78d5d2867054fd0c6f:8707f98e-a965-47ac-86f1-69ad0c301708::?targetcosurl=cos://us-south/sql-8707f98e-a965-47ac-86f1-69ad0c301708/result/&password=mYoDKUGxDUgUTdH58nKRujwcDA6NM6rEd2IH3dxhzkAh";

   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 1: Register JDBC driver
      Class.forName(DRIVER_CLASS);

      //STEP 2: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT * FROM cos://eu-de/oretestbucket/greetings2.csv STORED AS CSV";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         String greet = rs.getString("GREETING");
         String name = rs.getString("NAME");

         //Display values
         System.out.println(greet + " " + name + "!");
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
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
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
  }//end main    
    
    
}