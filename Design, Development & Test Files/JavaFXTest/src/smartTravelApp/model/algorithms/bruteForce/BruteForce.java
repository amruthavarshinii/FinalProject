/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.model.algorithms.bruteForce;

import java.util.ArrayList;
import java.util.HashMap;
import smartTravelApp.controller.utils.GoogleMapsDistanceMatrixClient;
import smartTravelApp.model.LocationsContainer;
import smartTravelApp.model.algorithms.TSPAlgorithm;

/**
 *
 * @author Rafael.Lopez
 */
public class BruteForce extends TSPAlgorithm
{
    private long shortestDistance = 0;
    LocationsContainer shortestTour = new LocationsContainer();
    
    @Override
    protected HashMap applyTSPAlgorithm(LocationsContainer locations, int start) 
    {        
        //Find all locations
        ArrayList<Integer> locationIds = new ArrayList<>();
        for (int i = 0; i < locations.size(); ++i)
        {
            locationIds.add(locations.get(i).getLocationId());
        }
        ArrayList<Integer> route = new ArrayList<>();
        findAllRoutes(route, locationIds, locations);
             
        HashMap result = new HashMap();
        result.put("tour", shortestTour);
        result.put("distance", shortestDistance);
        
        return result;
    }
    
    //Code for this method grabbed from 
    //http://stackoverflow.com/questions/11703827/brute-force-algorithm-for-the-traveling-salesman-problem-in-java
    public void findAllRoutes (ArrayList<Integer> route, ArrayList<Integer> locationsNotInRoute, 
                               LocationsContainer locations)
    {
        if(!locationsNotInRoute.isEmpty())
        {
            for(int i = 0; i < locationsNotInRoute.size(); i++)
            {
                Integer justRemoved = locationsNotInRoute.remove(0);
                ArrayList<Integer> newRoute =
                    (ArrayList<Integer>) route.clone();
                newRoute.add(justRemoved);

                findAllRoutes(newRoute, locationsNotInRoute, locations);
                locationsNotInRoute.add(justRemoved);
            }
        }
        else
        {
            processRoute(route, locations);
        }

    }

    private void processRoute(ArrayList<Integer> route, LocationsContainer locations) 
    {
        int offset = 1;
        GoogleMapsDistanceMatrixClient client = new GoogleMapsDistanceMatrixClient();
        long distance = 0;
        for (int i = offset; i < locations.size(); i++)
        {
            distance += client.getDistanceBetweenTwoLocations(locations.get(route.get(i-1)), locations.get(route.get(i)));
        }        
        System.out.println(route.toString() + " = Distance: " + distance);
        
        if (shortestDistance == 0)
        {
            shortestDistance = distance;
            buildTour(route, locations);
        }
        else if (distance < shortestDistance)
        {
            shortestDistance = distance;
            buildTour(route, locations);
        }
    }
    
    private void buildTour(ArrayList<Integer> route, LocationsContainer locations)
    {
        LocationsContainer newTour = new LocationsContainer();
        for (int i : route)
        {
            newTour.add(locations.get(i));
        }
        shortestTour = newTour;
    }
}
