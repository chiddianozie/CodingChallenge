package gradleProjectCreditSussieTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class insertIntoTable
{
public static void insertValues (ArrayList<String> events) { 
	      Connection con = null; 
	      Statement stmt = null; 
	      int result = 0; 
	      try { 
//	    	 Class.forName("jdbc.JDBCDriver");
	         con = DriverManager.getConnection( 
	            "jdbc:hsqldb:hsql://localhost/logDetailsDB", "SA", ""); 
	         stmt = con.createStatement(); 
//	          parse the arrayList from parseEventLongs here
	         String id = events.get(0);
	         Integer timeLapse = Integer.parseInt(events.get(3));
	         String type = events.get(1);
	         String host = events.get(2);
	         boolean alert = false;
	         if (timeLapse > 4)
	         {
	        	 alert = true;
	         }
	         
	         result = stmt.executeUpdate("INSERT INTO eventLogTable   VALUES  ('"
	         		+ id+"',"
	        		+timeLapse+",'"
	         		+type+"','"
	        		+host+"',"
	         		+alert +")"); 

	         con.commit(); 
	      }
	      catch (Exception e) 
	      { 
	         e.printStackTrace(System.out); 
	      } 
	      System.out.println(result+" rows effected"); 
	    //   System.out.println("Rows inserted successfully"); 
	   } 
}