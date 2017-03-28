package smartTravelApp.controller.utils.algorithms.nearestNeighbor;

import smartTravelApp.controller.utils.algorithms.TSPAlgorithm;
import java.util.HashMap;
import smartTravelApp.controller.utils.GoogleMapsDistanceMatrixClient;
import smartTravelApp.model.Location;
import smartTravelApp.model.LocationsContainer;

/*
 * Written by Rafael Lopez <lopez.rafael08@gmail.com>, 2016
 */

public class NearestNeighbor extends TSPAlgorithm
{    
    @Override
    public LocationsContainer applyTSPAlgorithm(LocationsContainer locations, int start)
    {
        GoogleMapsDistanceMatrixClient request = new GoogleMapsDistanceMatrixClient(); 
        long[][] distances = request.getDistanceMatrix(locations);
        
        LocationsContainer solution = new LocationsContainer();
        long distance = 0;
        int indexCurrentLocation = 0;
        
        for (int i = 1; i < distances.length; i++)
        {    
            if (i == 1)
            {
                solution.add(locations.get(0));
                indexCurrentLocation = start;
            }
            
            HashMap results = findNearestNeighbor(distances, indexCurrentLocation, solution);
            distance += (Long)results.get("distance");
            indexCurrentLocation = (Integer)results.get("index");
            solution.add(locations.get(indexCurrentLocation));       
        }
        System.out.println("*****Neareast Neighbor Algorithm******");
        System.out.println("Total distance: " + distance);
        return solution;
    }
    
    private HashMap findNearestNeighbor(long[][] data, int currentNode, LocationsContainer locationsVisited)
    {
        int indexMin=0;
        long currentValue;
        long tempMin = 0;
        for (int i = 0; i < data.length; i++)
        {
            if ( currentNode != i )
            {
                if( isValidNode(locationsVisited, i) )
                {
                    currentValue = data[currentNode][i];
                    if (tempMin != 0)
                    {
                        if ( currentValue < tempMin )
                        {
                            tempMin = currentValue;
                            indexMin = i;
                        }
                    }
                    else
                    {
                        tempMin = currentValue;
                        indexMin = i;
                    }
                }
            }
        }
        HashMap result = new HashMap();
        result.put("distance", tempMin);
        result.put("index", indexMin);
        
        return result;
    }
    
    private boolean isValidNode(LocationsContainer locationsVisited, int index)
    {
        boolean alreadyVisited = true;
        for (Location location : locationsVisited)
        {
            if(location.getLocationId() == index)
            {
                alreadyVisited = false;
                break;
            }
        }
        return alreadyVisited;
    }
}