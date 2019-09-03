package gradleProjectCreditSussieTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class createNewTable
{
public static void newTable() 
   {
     
	Connection con = null;
     // 1. Create a connection to the database 
     
      try 
      {
//    	 Class.forName("jdbc.JDBCDriver");
         con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/logDetailsDB", "SA", "");
         if (con!= null)
         {
            System.out.println("Connection created successfully");  
         }
         else
         {
            System.out.println("Problem with creating connection");
         }
      }  
      catch (Exception e) 
      {
         e.printStackTrace(System.out);
      }
            
      Statement stmt = null;
      int result = 0;
      
      try 
      {

         stmt = con.createStatement();
      
         // creates new table if it does not exits already
         result = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS eventLogTable ("
          		+ "id VARCHAR(50) NOT NULL,"
          		+ "event_duration INTEGER ,"
          		+ "type VARCHAR(50),"
          		+ "host VARCHAR(50),"
          		+ "alert BOOLEAN DEFAULT FALSE,"
          		+ "PRIMARY KEY (id));");
         
         
      }  
      catch (Exception e) 
      {
         e.printStackTrace(System.out);
      }
      if (result == 0)
      {
    	  System.out.println("Table created successfully");
      }
      else
      {
    	  System.out.println("Table creation failed");
      }
       
      
   }
}