package gradleProjectCreditSussieTest;
import java.io.IOException;
/**
 * @author Chidubem Iddianozie
 *
 */
public class driverApp 
{
/**
 * This is the driver class for the solution
 * - there are four classes,described as follows:
 * 	- readFile: this processes the contents of the text and writes to the database
 * 	- insertIntoTable: writes the contents of the parsed file intoo database, it is called by readFile.
 * 	- createNewTable: creates a table in the database if it does not exist already.
 * 	- selectFromTable: selects all the contents of the tables and prints in descending order
 * 
 * DriverApp checks that the parsed filepath exists and will calls the modules in sequence.
 * 
 * 
 * @param args
 * @throws IOException
 */
   public static void main(String[] args) throws IOException 
   {
	  if ( args.length > 1 || args.length == 0)
	   {
		  System.out.println("You have to parse a file path");
       }
	   else
	   {
		   final String filePath = args[0];
        //    System.out.println(filePath);
           // creates new table if it does not exist already
           createNewTable.newTable();
		   readFile.parseFileContents(filePath);
		   selectFromTable.makeSelectionInDescendingOrder();
		   
	   }

	   
   }
}