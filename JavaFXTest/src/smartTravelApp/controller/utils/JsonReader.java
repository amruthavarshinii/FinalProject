/*
 *Example http://www.mkyong.com/java/json-simple-example-read-and-write-json/
 */
package smartTravelApp.controller.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
/**
 *
 * @author Rafael.Lopez
 */
public class JsonReader 
{

    /**
     * Returns the distance between the origin and each destination sent in a 
     * Google Maps Distance Matrix API request
     * @param jsonResponse JSON response from the Google Maps Distance Matrix API service
     */
    public static void processJSONResponse(String jsonResponse)
    {
        JSONParser parser = new JSONParser();

        try 
        {
            Object obj = parser.parse(jsonResponse);
            JSONObject jsonObject = (JSONObject) obj;           
            JSONArray rows = (JSONArray) jsonObject.get("rows");
            Iterator<JSONObject> iterator = rows.iterator();
            while (iterator.hasNext()) 
            {
                JSONObject row = iterator.next();
                
                JSONArray elements = (JSONArray) row.get("elements"); 
                
                Iterator<JSONObject> parameters = elements.iterator();
                while (parameters.hasNext()) 
                {
                    JSONObject parameter = parameters.next();
                    JSONObject distanceObject = (JSONObject) parameter.get("distance");
                    long distance = (Long) distanceObject.get("value");
                    System.out.println("Distance: " + distance/1000 + "km");
                } 
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
