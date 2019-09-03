package gradleProjectCreditSussieTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;

public class readFile
{
public static void parseFileContents(String fileToRead) throws IOException
    {    	
		// to hold the contents of the parsed file - list of lists
		ArrayList<ArrayList<String>> parsedLogDetails = new ArrayList<>();
		
		// to hold the list of event ids - list
        List<String> listOfEventIds = new ArrayList<String>(); 
		// Read text file
        try (Stream <String> stream = Files.lines(Paths.get(fileToRead))) 
        {
			// list of all the json objects
        	List <String> listOfJsonObjects = stream.collect(Collectors.toList());
        	for (String jsonObjects : listOfJsonObjects) 
        	{
				// process each json object
        		JSONObject obj =  new JSONObject(jsonObjects);
        		
				String id = obj.getString("id");
				// store list of event ids to use later
        		listOfEventIds.add(id);
				
				// get each element in the json object
        		String state = obj.getString("state");
        		String time = String.valueOf(obj.get("timestamp"));
        		String type = "NA";
				String host = "NA";
				
				// to handle for cases where type and host does not exist
        		if (obj.has("type")) 
        		{

        		 	type = obj.getString("type");
        		}
        		
        		if (obj.has("host")) 
        		{
        		 	host = obj.getString("host");
				}	
				
				// populate parsedLogDetails with a list of parsed contents for each json object held in currentEventList
        		ArrayList<String> currentEventList = new ArrayList<>();
        		currentEventList.add(id);
        		currentEventList.add(state);
        		currentEventList.add(type);
        		currentEventList.add(host);
				currentEventList.add(time);
				
				// append currentList to the list of lists
        		parsedLogDetails.add(currentEventList);
			} 
			
			// get uniqueIds
			Set<String> uniqueEventIds = new HashSet<String>(listOfEventIds);
			
			// this gets the corresponding events START and FINISH for each ID and writes to the database
        	for (String uniqueEventId : uniqueEventIds) 
         	{

        		List <Integer> correspondingEventsIndexes = new ArrayList<Integer>(); 
        		// ==== Get all matching events for a particular ID
        		for (ArrayList <String> eventLog :parsedLogDetails )
        		{
        			if (eventLog.contains(uniqueEventId))
        			{
        				correspondingEventsIndexes.add(parsedLogDetails.indexOf(eventLog));
        			} 			
        		}
        		if (correspondingEventsIndexes.size() == 2)
        		{
            		ArrayList<String> processedEvents = new ArrayList<>();
            		// The ID
            		processedEvents.add(parsedLogDetails.get(correspondingEventsIndexes.get(0)).get(0));
            		// The Type
            		processedEvents.add(parsedLogDetails.get(correspondingEventsIndexes.get(0)).get(2));
            		// The Host
            		processedEvents.add(parsedLogDetails.get(correspondingEventsIndexes.get(0)).get(3));
            		// Compute the time lapse
            		Long timeOne = Long.parseLong(parsedLogDetails.get(correspondingEventsIndexes.get(0)).get(4));
					Long timeTwo = Long.parseLong(parsedLogDetails.get(correspondingEventsIndexes.get(1)).get(4));
					
					// ensure there are no negative values using ABS
            		Long timeLapse = Math.abs(timeOne-timeTwo);
            		processedEvents.add(Long.toString(timeLapse));
            		// System.out.println(processedEvents);
				   
					// write to the database
					insertIntoTable.insertValues(processedEvents);
        		}
        		else
        		{
        			System.out.println("Each event must have two logs");
        			break;
        		}
        		
         	}
       
        }
    
    }
}

	