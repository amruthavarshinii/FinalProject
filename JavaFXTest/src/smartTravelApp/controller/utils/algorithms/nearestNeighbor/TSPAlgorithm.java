/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.controller.utils.algorithms.nearestNeighbor;

import smartTravelApp.controller.utils.GoogleMapsDistanceMatrixClient;
import smartTravelApp.model.Location;
import smartTravelApp.model.LocationsContainer;

/**
 *
 * @author Rafael.Lopez
 */
public abstract class TSPAlgorithm 
{

    /**
     * A method that takes in a list of locations as part of a tour 
     * and returns an optimal distance-effective path.
     * @param locations Locations to be processed
     * @param startingNode Starting node for the tour
     * @return
     */
    public final String processInstance(LocationsContainer locations, int startingNode)
    {
        long[][] distances = getSymmetricMatrix(locations);
        Integer[] solution = processTour(distances, startingNode);
        return formatMessage(solution, locations);
    }
    
    final long[][] getSymmetricMatrix(LocationsContainer locations){
        String places[] = new String[locations.size()];
        
        for (int index = 0; index < locations.size(); index++)
        {
            Location location = (Location) locations.get(index);
            String coordinates = "" +location.getLatitude() + "," + location.getLongitude();
            places[index] = coordinates;
        }

        GoogleMapsDistanceMatrixClient request = new GoogleMapsDistanceMatrixClient(); 
        return request.getDistanceMatrix(places);
    }
    
    final String formatMessage(Integer[] solution, LocationsContainer locations){
        String message = "Path:\n";
        for (int i = 0; i < solution.length; i++)
        {
            message = message.concat(i + "- " +locations.getLocation(solution[i]).getPlaceDescription() +"\n");
        }
        return message;
    }
    
    abstract Integer[] processTour(long[][] data, int start);
}
