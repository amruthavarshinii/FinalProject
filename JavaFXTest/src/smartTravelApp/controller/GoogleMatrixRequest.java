/*
 * Code from 
 * https://teamtreehouse.com/community/how-to-use-the-google-map-matrix-api-for-calculating-the-distance-between-two-points-in-java-a-big-treat-is-promise
 * Limits https://developers.google.com/maps/documentation/distance-matrix/usage-limits 
*/
package smartTravelApp.controller;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import smartTravelApp.controller.utils.JsonReader;

/**
 *
 * @author Rafael.Lopez
 */
public class GoogleMatrixRequest 
{

    private static final String API_KEY = "AIzaSyA-ORl8X50QuLmTyhP4izLzCyWcza_eDGM";

    OkHttpClient client = new OkHttpClient();

    private String sendRequest(String url) throws IOException 
    {
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        JsonReader.processJSONResponse(response.body().string());
        return response.body().string();
    }

    public void getDistanceMatrix(String [] places) 
    {
        try
        {
            GoogleMatrixRequest request = new GoogleMatrixRequest();

            Integer destinationsToBeProcessed = places.length - 1;

            //Distances are calculated for each origin using the rest of the locations
            for (int originIndex = 0; originIndex < places.length; originIndex++)
            {
                String urlRequest = buildRequestURL(places, originIndex, destinationsToBeProcessed); 
                System.out.println(urlRequest);
                String response = request.sendRequest(urlRequest);
                System.out.println(response);
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    private String buildRequestURL(String[] places, int originIndex, Integer destinationsToBeProcessed)
    {
        int destinationsProcessed = 0;
        String place = places[originIndex];
        String origins = "origins=" + place;
        String destinations = "destinations=";
        for (int destinationIndex = 0; destinationIndex < places.length; destinationIndex++)
        {
            if (originIndex != destinationIndex)
            {
                destinations = destinations.concat(places[destinationIndex] + "%7c");
                destinationsProcessed ++;
                if (destinationsProcessed == destinationsToBeProcessed)
                {
                   destinations = destinations.substring(0, destinations.length() - 3); 
                }
            }
        } 
        return "https://maps.googleapis.com/maps/api/distancematrix/json?" + origins + "&" + 
                destinations + "&mode=walking&language=en-EN&key=" + API_KEY;           
    }
}
