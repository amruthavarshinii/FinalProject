/*
 * Code from 
 * https://teamtreehouse.com/community/how-to-use-the-google-map-matrix-api-for-calculating-the-distance-between-two-points-in-java-a-big-treat-is-promise
 * Limits https://developers.google.com/maps/documentation/distance-matrix/usage-limits 
*/
package smartTravelApp.controller.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/**
 *
 * @author Rafael.Lopez
 */
public class GoogleMapsDistanceMatrixClient 
{

    private static final String API_KEY = "AIzaSyA-ORl8X50QuLmTyhP4izLzCyWcza_eDGM";

    OkHttpClient client = new OkHttpClient();

    private long [] sendRequest(String url, int distanceArraySize) throws IOException 
    {
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        long [] distanceArray = JsonReader.processJSONResponse(response.body().string(), distanceArraySize);
        System.out.println(response.body().string());
        return distanceArray;
    }

    /**
     * Returns a symmetric matrix with all the distances between each place
     * @param places An array containing all places to be visited
     * @return Returns a symmetric matrix containing the distances for each 
     * pair of nodes
     */
    public long[][] getDistanceMatrix(String [] places) 
    {
        long[][] distancesMatrix = new long[places.length][places.length];
        try
        {
            GoogleMapsDistanceMatrixClient request = new GoogleMapsDistanceMatrixClient();

            Integer destinationsToBeProcessed = places.length - 1;

            //Distances are calculated for each origin using the rest of the locations
            for (int originIndex = 0; originIndex < places.length; originIndex++)
            {
                String urlRequest = buildRequestURL(places, originIndex, destinationsToBeProcessed); 
                System.out.println(urlRequest);
                long [] distanceArray = request.sendRequest(urlRequest, places.length); 
                distancesMatrix[originIndex] = buildRowForSymmetricMatrix(distanceArray, originIndex);
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return distancesMatrix;
    }
    
    private long[] buildRowForSymmetricMatrix(long[] distanceArray, int originIndex)
    {
        long[] row = new long[distanceArray.length];
        int distanceArrayIndex = 0;
        for (int i = 0; i < row.length; i++)
        {
            if(i == originIndex)
            {
               row[i] = 0; 
            }
            else
            {
                row[i] = distanceArray[distanceArrayIndex];
                distanceArrayIndex++;
            }
        }
        return row;
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
