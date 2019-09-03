package gradleProjectCreditSussieTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class selectFromTable
{
	public static void makeSelectionInDescendingOrder() 
   {
      Connection con = null;
      Statement stmt = null;
      ResultSet result = null;
      
      try 
      {

         con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/logDetailsDB", "SA", "");
         stmt = con.createStatement();
         result = stmt.executeQuery(
          "SELECT id, event_duration, type, host, alert FROM eventLogTable   ORDER BY event_duration DESC");
         
         while(result.next())
         {
            System.out.println(result.getString("id")+" | "+
              result.getInt("event_duration")+" | "+
               result.getString("type")+" | "+
               result.getString("host")+" | "+
               result.getBoolean("alert"));
         }
      } 
      catch (Exception e) 
      {
         e.printStackTrace(System.out);
      }
   }
}	